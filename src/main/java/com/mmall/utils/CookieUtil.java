package com.mmall.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ${cookie工具类}
 *
 * @author yangningkai
 * @create 2019-04-26 上午6:46
 **/
@Slf4j
public class CookieUtil {
    private final static String COOKIE_DOMAIN = ".mymmall.com";
    private final static String COOKIE_NAME = "mymall_login_token";

    /**
     * 写入cookie
     * @param response
     * @param token
     */
    public static void writeLoginToken(HttpServletResponse response, String token){
        Cookie ck = new Cookie(COOKIE_NAME,token);
        ck.setDomain(COOKIE_DOMAIN);
        ck.setPath("/");
        ck.setHttpOnly(true);
        ck.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
        response.addCookie(ck);
    }

    /**
     * 读取cookie
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cks = request.getCookies();
        if(cks != null){
            for(Cookie ck : cks){
                log.info("read cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                if(StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    log.info("return cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     */
    public static void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cks = request.getCookies();
        if(cks != null){
            for(Cookie ck : cks){
                if(StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);
                    log.info("del cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
