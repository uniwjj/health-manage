package com.beidou.hm.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoDto {

  /**
   * 账户
   */
  private String account;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 用户名
   */
  private String username;

  public UserInfoDto(String account, String email, String username) {
    this.account = account;
    this.email = email;
    this.username = username;
  }

}
