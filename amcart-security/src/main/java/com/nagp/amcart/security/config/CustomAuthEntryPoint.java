package com.nagp.amcart.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagp.amcart.security.dto.AmCartErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

import static com.nagp.amcart.security.constant.SecurityConstant.INVALID_TOKEN;
import static com.nagp.amcart.security.constant.SecurityConstant.INVALID_TOKEN_ERROR_MESSAGE;

public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        AmCartErrorResponse amCartErrorResponse = new AmCartErrorResponse(INVALID_TOKEN,
                INVALID_TOKEN_ERROR_MESSAGE, new Date(), null);
        var mapper = new ObjectMapper();

        response.getWriter().write(mapper.writeValueAsString(amCartErrorResponse));
    }
}