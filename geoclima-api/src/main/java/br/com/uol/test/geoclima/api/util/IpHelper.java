package br.com.uol.test.geoclima.api.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Caroline Lopes on 09/08/18.
 */
public class IpHelper {

    public static String getIpFrom(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
