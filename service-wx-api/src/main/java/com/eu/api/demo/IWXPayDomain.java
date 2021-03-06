package com.eu.api.demo;

/**
 * 域名管理，实现贮备域名自动切换
 * @author yuanjie
 * @date 2019/1/10 19:41
 */
public abstract interface IWXPayDomain {

    /**
     * 上报域名网络状况
     * @param domain    域名
     * @param elapsedTimeMillis 耗时
     * @param ex    网络请求中出现的异常
     *              null 表示没有异常
     *              ConnectTimeoutException 表示简历网络连接异常
     *              UnkownHostException 表示dns解析异常
     */
    abstract void report(final String domain, long elapsedTimeMillis, final Exception ex);

    abstract DomainInfo getDomain(final WXPayConfig config);

    static class DomainInfo {
        // 域名
        public String domain;
        // 该域名是否为主域名。
        public boolean primaryDomain;

        public DomainInfo(String domain, boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        @Override
        public String toString() {
            return "DomainInfo{" +
                    "domain='" + domain + '\'' +
                    ", primaryDomain=" + primaryDomain +
                    '}';
        }
    }
}
