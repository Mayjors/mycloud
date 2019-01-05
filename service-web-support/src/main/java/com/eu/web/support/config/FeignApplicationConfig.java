package com.eu.web.support.config;

import com.alibaba.fastjson.JSON;
import com.eu.util.exception.BusinessRuntimeException;
import com.eu.util.result.ResultMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @author yuanjie
 * @date 2019/1/4 16:25
 */
@Slf4j
@Configuration
public class FeignApplicationConfig {

    /**
     * 采用jackson2 json库来反序列化http返回消息，因为Spring默认使用jackson2来操作http responsebody的序列化
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现
     */
    @Resource
    private ObjectMapper mapper;

    @Bean
    public ErrorDecoder errorDecodr() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                log.info("methodKey:{}", methodKey);
                log.info("response:{}", response.toString());
                log.info("response body:{}", response.body());
                InputStream is = null;
                try {
                    is = response.body().asInputStream();
                    ResultMessage m = mapper.readValue(is, ResultMessage.class);
//                    ResultMessage m = JSON.parseObject(is, ResultMessage.class);
                    log.info("parse result ResultMessage:{}", JSON.toJSONString(m));
                    return new BusinessRuntimeException(m.getCode(), m.getMessage(), m.getArgs());
                } catch (IOException e) {
                    log.info("处理Feign的异常信息失败！", e);
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return new BusinessRuntimeException("err100004", "未知原因的错误!");
            }
        };
    }

    @Deprecated
    public ErrorDecoder errorDecoder2() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                log.info("methodKey:{}", methodKey);
                log.info("response:{}", response.toString());
                log.info("response body: {}", response.body());
                BufferedReader br = null;
                try {
                    InputStream is = response.body().asInputStream();
//                    byte[] bi = new byte[1024];
//                    is.read(bi);    // 先把数据read到bi里面去，后面就获取不到数据了，InputStream内部应该是有游标之类的标记
//                    log.info("raw bi: {}", bi);
                    // 第一种方式
                    br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    // 第二种方式
                    Reader reader = response.body().asReader();
                    br = new BufferedReader(reader);
                    String line = "";
                    String content = "";
                    while ((line = br.readLine()) != null) {
                        content += line;
                    }
                    log.info("before exception: {}", content);
                    log.info("UTF-8 byte[]: {}", content.getBytes("UTF-8"));
                    ResultMessage m = JSON.parseObject(content, ResultMessage.class);
                    log.info("after exception :{}", content);
                    log.info("parse result ResultMessage:{}", JSON.toJSONString(m));
                    return new BusinessRuntimeException(m.getCode(), m.getMessage(), m.getArgs());
                } catch (IOException e) {
                    log.info("处理Feign的异常信息失败！", e);
                } finally {
                    if(br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                        }
                    }
                }
                return new BusinessRuntimeException("err100004","未知原因的错误！");
            }
        };
    }

}
