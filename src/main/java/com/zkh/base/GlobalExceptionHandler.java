package com.zkh.base;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常
 */
@ControllerAdvice(basePackages = "com.travel.controller")
@Slf4j
public class GlobalExceptionHandler {


   @ExceptionHandler(MethodArgumentNotValidException.class)
   public Map<String, Object> handlerServiceException(MethodArgumentNotValidException  e){
      BindingResult exceptions = e.getBindingResult();
      String msg = "服务器异常，请联系管理员";
      if (exceptions.hasErrors()) {
         msg = exceptions.getFieldError().getDefaultMessage();
      }
      Map<String, Object> errorResultMap = new HashMap<String, Object>();
      errorResultMap.put("code", "500");
      errorResultMap.put("msg", msg);
      return errorResultMap;
   }

   @ExceptionHandler(ServiceException.class)
   @ResponseBody
   public Map<String, Object> ServiceException(ServiceException e) {
      String msg = e.getMessage();
      if (msg == null || msg.equals("")) {
         msg = "服务器异常，请联系管理员";
      }
      Map<String, Object> errorResultMap = new HashMap<String, Object>();
      errorResultMap.put("code", "500");
      errorResultMap.put("msg", msg);
      log.info("发生错误： "+msg);
      return errorResultMap;
   }


   @ResponseBody
   @ExceptionHandler(Exception.class)
   public Map<String, Object> handleException(Exception e) {
      String msg = e.getMessage();
      if (msg == null || msg.equals("")) {
         msg = "服务器异常，请联系管理员";
      }
      Map<String, Object> errorResultMap = new HashMap<String, Object>();
      errorResultMap.put("code", "500");
      errorResultMap.put("msg", msg);
      log.info("发生错误： "+msg);
      return errorResultMap;
   }
}