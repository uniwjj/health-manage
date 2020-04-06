package com.beidou.hm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 认证控制器
 *
 * @author ginger
 * @create 2020-04-05 11:41
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

  @GetMapping("/login-page")
  public String loginPage(
      @RequestParam(value = "error", required = false) String error,
      Model model) {
    if (error != null) {
      model.addAttribute("error", error);
    }
    return "login";
  }

}

