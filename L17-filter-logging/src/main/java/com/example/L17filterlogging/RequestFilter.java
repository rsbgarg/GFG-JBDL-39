package com.example.L17filterlogging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestFilter extends HttpFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        //MDC
        MDC.put("requestId", request.getHeader("requestId"));
        MDC.put("userId", request.getHeader("userId"));

        LOGGER.info("Going to process request {}", request.getRequestURI());
        long start = System.currentTimeMillis();
        filterChain.doFilter(request,response);
        long end = System.currentTimeMillis();
        LOGGER.info("Total time take by {}  is {} ms" , request.getRequestURI(), end-start);
        MDC.clear();
    }

}
