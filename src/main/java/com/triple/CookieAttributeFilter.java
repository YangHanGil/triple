package com.triple;
import javax.servlet.annotation.WebFilter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
//import java.util.logging.Filter;
//import java.util.logging.LogRecord;

@WebFilter
public class CookieAttributeFilter implements Filter {

//    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        chain.doFilter(request, response);
//        log.info("CookieAttributeFilter");
        addSameSite(httpServletResponse , "None");
    }

    private void addSameSite(HttpServletResponse response, String sameSite)
        {
            Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
            boolean firstHeader = true;
            for (String header : headers)
            { // there can be multiple Set-Cookie attributes
                if (firstHeader) {
                    response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=" + sameSite));
                    System.out.println(sameSite);
                    firstHeader = false;
                    continue;
                }
                response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s;  %s", header, "SameSite=" + sameSite));
            }
        }
}
