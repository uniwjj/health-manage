package com.beidou.hm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfig {

  @ExceptionHandler(RuntimeException.class)
  public String runtimeException(RuntimeException e) {
    log.error("known error", e);
    return "forward:/error/known?msg=" + e.getMessage();
  }

  @ExceptionHandler(Exception.class)
  public String exception(Exception e) {
    log.error("unknown error", e);
    return "forward:/error/unknown";
  }

}
