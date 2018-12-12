package com.example.util.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 长链接转短链接的工具类
 * @author yuanjie
 * @date 2018/9/19 9:40
 */
public class ShortNetAddressUtil {

    public static CloseableHttpClient httpClient;
    static {
        httpClient = HttpClients.createDefault();
    }

    /**
     * 生成短链接信息
     * @return
     */
    public static String generateShortUrl(String url) {
        try {
            HttpPost httpPost = new HttpPost("http://suo.im/api.php");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("format", "utf-8"));
            params.add(new BasicNameValuePair("url", url));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static void main(String[] args) {
        String url = generateShortUrl(
                "https://www.baidu.com/s?wd=%E7%A0%81%E4%BA%91&rsv_spt=1&rsv_iqid=0x964c1c3500030761&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=48021271_11_hao_pg&rsv_enter=1&oq=spring%2520data%2520jpa%25E8%2587%25AA%25E5%25AE%259A%25E4%25B9%2589%25E6%2596%25B9%25E6%25B3%2595%25E8%25A7%2584%25E8%258C%2583&rsv_t=d5f86AreI8IAKjbxCKuUMFkUO6EKFeso61joDwkdQQrpLo%2BgDuuyujzyCafz%2B5gC8IMw8TR3iRoe&rsv_pq=f35da7870001b1ba&inputT=7063&rsv_sug3=74&rsv_sug1=50&rsv_sug7=100&bs=spring%20data%20jpa%E8%87%AA%E5%AE%9A%E4%B9%89%E6%96%B9%E6%B3%95%E8%A7%84%E8%8C%83");
        System.out.println(url);
    }

}
