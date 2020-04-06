package com.beidou.hm.config.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "github")
public class GitHubConfig {

  /**
   * uri
   */
  private String serverUri;

  /**
   * 客户端id
   */
  private String clientId;

  /**
   * 客户端秘钥
   */
  private String clientSecret;

  /**
   * path
   */
  private GitHubPath  path;

  /**
   * API
   */
  private GitHubApi api;


  @Data
  public static class GitHubPath {

    /**
     * 授权地址
     */
    private String auth;

    /**
     * 获取access_token
     */
    private String token;

  }

  @Data
  public static class GitHubApi {

    /**
     * uri
     */
    private String serverUri;

    /**
     * path
     */
    private ApiPath path;


    @Data
    public static class ApiPath {

      /**
       * 用户信息
       */
      private String userInfo;

    }

  }

}
