package com.eu.servicehadoop.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@ConditionalOnBean(FileSystem.class)
public class HadoopTemplate {
    @Autowired
    private FileSystem fileSystem;

    @Value("${hadoop.name-node}")
    private String nameNode;

    @Value("${hadoop.namespace:/}")
    private String nameSpace;

    @PostConstruct
    public void init() {
        existDir(nameSpace, true);
    }

    public void uploadFile(String srcFile) {
        copyFileToHDFS(false, true, srcFile, nameNode);
    }

    public void uploadFile(String srcFile,String destPath){
        copyFileToHDFS(false,true,srcFile,destPath);
    }

    public void delFile(String fileName) {
        rmdir(nameSpace, fileName);
    }

    public void delDir(String path){
        nameSpace = nameSpace + "/" +path;
        rmdir(path,null) ;
    }

    public void download(String fileName,String savePath){
        getFile(nameSpace+"/"+fileName,savePath);
    }

    /**
     * 创建目录
     * @param filePath
     * @param create
     * @return
     */
    public boolean existDir(String filePath, boolean create) {
        boolean flag = false;
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath不能为空!");
        }
        try {
            Path path = new Path(filePath);
            if (create) {
                if (!fileSystem.exists(path)) {
                    fileSystem.mkdirs(path);
                }
            }
            if (fileSystem.isDirectory(path)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return flag;
    }

    /**
     * 文件上传到HDFS
     * @param delSrc        指是否删除源文件，true删除，默认为false
     * @param overwrite
     * @param srcFile       源文件，上传文件路径
     * @param destPath      hdfs的目的路径
     */
    public void copyFileToHDFS(boolean delSrc, boolean overwrite, String srcFile, String destPath) {
        // 源文件路径时Linux下的路径，如果在Windows下测试，需要改写为windows下的路径，比如D://hadoop/djt/weibo.txt
        Path srcPath = new Path(srcFile);

        // 目的路径
        if (StringUtils.isNotBlank(nameNode)) {
            destPath = nameNode + destPath;
        }
        Path dstPath = new Path(destPath);
        // 实现文件上传
        try {
            // 获取FileSystem对象
            fileSystem.copyFromLocalFile(srcPath, dstPath);
            fileSystem.copyFromLocalFile(delSrc, overwrite, srcPath, dstPath);
            // 释放资源
            fileSystem.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 删除文件或文件目录
     * @param path
     * @param fileName
     */
    public void rmdir(String path, String fileName) {
        try {
            // 返回FileSystem对象
            if (StringUtils.isNotBlank(nameNode)) {
                path = nameNode + path;
            }
            if (StringUtils.isNotBlank(fileName)) {
                path = path + "/" + fileName;
            }
            // 删除文件或文件目录 delete(Path f) 此方法已经弃用
            fileSystem.delete(new Path(path), true);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 从HDFS下载文件
     * @param hdfsFile
     * @param destPath  文件下载后，存放地址
     */
    public void getFile(String hdfsFile, String destPath) {
        // 源文件路径
        if (StringUtils.isNotBlank(nameNode)) {
            hdfsFile = nameNode + hdfsFile;
        }
        Path hdfsPath = new Path(hdfsFile);
        Path dstPath = new Path(destPath);
        try {
            // 下载hdfs上的文件
            fileSystem.copyFromLocalFile(hdfsPath, dstPath);
            // 释放资源
//            fileSystem.clone();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
