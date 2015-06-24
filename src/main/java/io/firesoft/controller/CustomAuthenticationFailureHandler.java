package io.firesoft.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;





public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
      super.onAuthenticationFailure(request, response, exception);
      if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
          request.setCharacterEncoding("utf8");
          response.setCharacterEncoding("utf8");
          response.setContentType("application/json"); 
          PrintWriter out = response.getWriter(); 
          response.setStatus(HttpServletResponse.SC_OK);
          out.print("{\"success\" : false, \"message\" : \"authentication-failure\"}");
      } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
          return;
      }
    }
}