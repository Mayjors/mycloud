package com.eu.api.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * 配置微信支付信息
 * @author yuanjie
 * @date 2019/1/10 19:18
 */
public class OurWxPayConfig implements WXPayConfig {

    /**
     * 将证书地址解析成byte
     * 加载证书 -- 需要到微信商户平台进行下载
     */
    private byte[] certData;

    public OurWxPayConfig() throws IOException {
//        InputStream certPath = Thread.currentThread().getContextClassLoader().getResourceAsStream("cert/wxpay/apiclient_cert.p12");
//        this.certData = IOUtils.toByteArray(certStream);
        String certPath = "cert/wxpay/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    /**
     * 设置我们的appid
     * 用户名
     * 秘钥
     * @return
     */
    @Override
    public String getAppID() {
        return "wx7494893843843c";
    }

    @Override
    public String getMchID() {
        return "4672984344";
    }

    @Override
    public String getKey() {
        return "qbH5l4N3468798dfgK";
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
