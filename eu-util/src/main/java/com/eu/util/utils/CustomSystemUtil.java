package com.eu.util.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.*;
import java.util.Enumeration;

/**
 * 系统工具类，用于获取系统相关信息
 * @author yuanjie
 * @date 2018/12/13 11:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomSystemUtil {
    /**
     * 内网IP
     */
    private static String INTRANET_IP = getIntranetIp();

    /**
     * 外网IP
     */
    private static String INIERNET_IP = getInternetIp();

    /**
     * 获取内网IP
     */
    private static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 获取外网IP
     */
    private static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }
            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
