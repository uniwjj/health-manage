package com.beidou.hm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/index", "/step/record").hasRole("USER")
        .antMatchers("/step/settle").hasRole("ADMIN")
        .and().formLogin()
        .loginPage("/auth/login-page")
        .loginProcessingUrl("/auth/login-process")
        .permitAll()
        .and().exceptionHandling().accessDeniedPage("/error")
        .and().csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER")
        .and()
        .withUser("jessie").password(passwordEncoder().encode("jessie")).roles("USER");
  }

  @Bean
  public static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
