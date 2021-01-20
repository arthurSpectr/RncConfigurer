package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
@RestController
public class CustomErrorController implements ErrorController {

  private static final String PATH = "/error";

  @Value("${debug}")
  private boolean debug;

  @Autowired
  private ErrorAttributes errorAttributes;

//  @RequestMapping(path = PATH)
//  public Object error(HttpServletRequest request, WebRequest webRequest, HttpServletResponse response) {
//    int statusCode = response.getStatus();
//
//    return new ErrorJson(statusCode, getErrorAttributes(request, webRequest, debug));
//  }

  @Override
  public String getErrorPath() {
    return PATH;
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request, WebRequest webRequest, boolean includeStackTrace) {
    new ServletRequestAttributes(request);

    return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
  }
}
*/
