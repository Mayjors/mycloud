package com.eu.servicehadoop.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "hadoop.name-node")
public class HadoopConfig {

    @Value("${hadoop.name-node}")
    private String nameNode;

    @Bean("fileSystem")
    public FileSystem createFs() {
        // 读取配置文件
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", "hdfs://47.107.170.10:9000");
        configuration.set("dfs.replication", "1");
        // 指定访问hdfs的客户端身份
        FileSystem fs = null;
        // 返回指定的文件系统，如果在本地测试，需要使用此种方法获取文件系统
        try {
            URI uri = new URI(nameNode.trim());
            fs = FileSystem.get(uri, configuration);
        } catch (Exception e) {
            log.error("", e);
        }
        return fs;
    }

}
