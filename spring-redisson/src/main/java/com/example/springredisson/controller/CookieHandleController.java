package com.example.springredisson.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author liluming
 * @className: CookieHandleController
 * @description:
 * @date 2022/4/8 9:27 上午
 */
@RestController
public class CookieHandleController {
    @RequestMapping(value = "/helloworld")
    public String helloworld() {
        return "hello world!!!";
    }

    @RequestMapping(value = "/crossSiteCookie", method= RequestMethod.GET, produces = "application/javascript;charset=UTF-8")
    @ResponseBody
    public String crossSiteCookie(
            HttpServletRequest request,
            HttpServletResponse response,
            // String callbackQuery
            @RequestParam(name = "callbackQuery", required = false) String callbackQuery
            // @RequestParam("userId") String userId, // 这些都是普通参数，根据具体业务设计即可
            // @RequestParam("targetDomain") String targetDomain,
            // @RequestParam("random") String random
    ) {
        addOneCookie(response, "username", "haoyu", "atguat.com.cn");
        addOneCookie(response, "username", "haoyu", "gome-local.atguat.com.cn");
        return callbackQuery + "('success')";
    }

    /**
     * 往响应头添加一个Cookie，要传三个参数： Cookie键，Cookie值，Cookie所在域名
     */
    public static void addOneCookie(HttpServletResponse httpServletResponse, String cookieKey, String cookieValue, String cookieDomain) {
        // Cookie过期时间
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 6); // 6小时后过期
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        String responseHeaderString = cookieKey + "=" + cookieValue + ";";
        // httpServletResponse.addHeader("Set-Cookie",responseHeaderString + "Path=/; Domain=" + cookieDomain + "; Expires=" + sdf.format(date) + "; SameSite=None; Secure=false"); // Chrome浏览器跨域写Cookie需要带的属性
        httpServletResponse.addHeader("Set-Cookie",responseHeaderString + "Path=/; Domain=" + cookieDomain + "; Expires=" + sdf.format(date) + "; SameSite=None; "); // Chrome浏览器跨域写Cookie需要带的属性
    }
}
