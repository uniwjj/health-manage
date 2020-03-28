package com.beidou.hm.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StepStatConstant {

  @Getter
  @AllArgsConstructor
  public enum StatTypeEnum {

    NORMAL(0, "正常"),

    MENSTRUATION(1, "亲戚拜访"),

    REST(2, "休息");

    private Integer type;

    private String desc;

    public static StatTypeEnum valueOfType(Integer type) {
      for (StatTypeEnum statTypeEnum : StatTypeEnum.values()) {
        if (statTypeEnum.getType().equals(type)) {
          return statTypeEnum;
        }
      }
      throw new RuntimeException(type + " can not be converted to StepStatConstant.TypeEnum");
    }

    public boolean equalWith(Integer type) {
      return this.getType().equals(type);
    }

  }

  @Getter
  @AllArgsConstructor
  public enum StatusEnum {

    UNSETTLED(0, "未结算"),

    SETTLED(1, "已结算");

    private Integer status;

    private String desc;

    public static StatusEnum valueOfStatus(Integer status) {
      for (StatusEnum statusEnum : StatusEnum.values()) {
        if (statusEnum.getStatus().equals(status)) {
          return statusEnum;
        }
      }
      throw new RuntimeException(status + " can not be converted to StepStatConstant.StatusEnum");
    }

    public boolean equalWith(Integer status) {
      return this.getStatus().equals(status);
    }

  }

}
