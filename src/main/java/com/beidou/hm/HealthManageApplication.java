package com.beidou.hm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HealthManageApplication {

  public static void main(String[] args) {
    SpringApplication.run(HealthManageApplication.class, args);
  }

}
