/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author PC
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final boolean USE_AUTHENTICATION = true;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (!USE_AUTHENTICATION)
            return true;
        
        String requestUri = request.getRequestURI();
        
        if (requestUri.contains("/visio/api")) {
            return true;
        }
        
        if (!"/visio/user/login".equals(requestUri)) {
            boolean authenticated = request.getSession().getAttribute("user") != null;
            if (!authenticated) {
                response.sendRedirect("/visio/user/login");
            }
            return authenticated;
        }
        else {
            request.getSession().setAttribute("user", null);
        }

        return true;
    }
}
