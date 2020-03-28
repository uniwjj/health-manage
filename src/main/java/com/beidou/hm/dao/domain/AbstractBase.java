package com.beidou.hm.dao.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractBase {

  /**
   * 自增id
   */
  protected Long id;

  /**
   * 创建时间
   */
  protected Date createTime;

  /**
   * 更新时间
   */
  protected Date updateTime;

}
