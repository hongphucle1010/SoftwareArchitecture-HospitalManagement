package com.hcmutnightowls.authenticationservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class OAuth2AuditFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        try {
            String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Trong môi trường production, không nên log toàn bộ token vì lý do bảo mật
                // Nhưng trong development, có thể hữu ích để gỡ lỗi
                log.debug("OAuth2 Bearer token present in request to: {}", httpRequest.getRequestURI());
            }
            
            chain.doFilter(request, response);
            
            int status = httpResponse.getStatus();
            if (status == 401 || status == 403) {
                log.warn("OAuth2 Authorization failed for request to: {} with status: {}", 
                         httpRequest.getRequestURI(), status);
            }
        } catch (Exception e) {
            log.error("Error in OAuth2 filter: " + e.getMessage(), e);
            throw e;
        }
    }
}