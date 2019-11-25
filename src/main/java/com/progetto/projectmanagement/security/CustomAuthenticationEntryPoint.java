package com.progetto.projectmanagement.security;

import io.jsonwebtoken.MalformedJwtException;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


@Component
class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // 401
        System.out.println("Authentication Failed "+getErrorModel(response.getStatus()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(getErrorModel(response.getStatus()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        // 403
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(getErrorModel(response.getStatus()));
    }

    @ExceptionHandler (value = {MalformedJwtException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         MalformedJwtException exception) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getOutputStream().println(getErrorModel(response.getStatus()));
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         Exception exception) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getOutputStream().println(getErrorModel(response.getStatus()));
    }

    public String getErrorModel(int statusRec){
        /*
         Here we make string in json wise because we can not pass the object of model to getOutputStream().println
         */
        String status = "status";
        String recordCount = "recordCount";
        String content = "content";
        String contents = "contents";
        String error = "error";
        String result = "result";
        String systemError = "systemError";
        String strParam = "";

        strParam += "{\"" + status + "\":\"" + statusRec+ "\"";
        strParam += ",\"" + recordCount + "\":\"" +0+ "\"";
        strParam += ",\"" + contents + "\":\"" + "null"+"\"";
        strParam += ",\"" + content + "\":\"" + "null"+"\"";
        strParam += ",\"" + error + "\":\"" + requirementsProperties.tokenNotValid()+ "\"";
        strParam += ",\"" + result + "\":\""+"fail"+"\"";
        strParam += ",\"" + systemError + "\":\""+requirementsProperties.tokenNotValid()+"\""+"}";

        return strParam;
    }
}