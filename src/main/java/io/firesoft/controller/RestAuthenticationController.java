package io.firesoft.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import flexjson.JSONSerializer;

@Controller
@RequestMapping(value = "/rest/security")
public class RestAuthenticationController {

    public HttpHeaders getJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<String> apiLoginPage() {
        return new ResponseEntity<String>(getJsonHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value="/authentication-failure", method = RequestMethod.GET)
    public ResponseEntity<String> apiAuthenticationFailure() {
        // return HttpStatus.OK to let your front-end know the request completed (no 401, it will cause you to go back to login again, loops, not good)
        // include some message code to indicate unsuccessful login
        return new ResponseEntity<String>("{\"success\" : false, \"message\" : \"authentication-failure\"}", getJsonHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/default-target", method = RequestMethod.GET)
    public ResponseEntity<String> apiDefaultTarget() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // exclude/include whatever fields you need
        String userJson = new JSONSerializer().exclude("*.class", "*.password").serialize(authentication);
        return new ResponseEntity<String>(userJson, getJsonHeaders(), HttpStatus.OK);
    }
}