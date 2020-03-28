package com.beidou.hm.dao.domain;

import com.beidou.hm.common.constant.StepStatConstant.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class StepStat extends AbstractBase {

  /**
   * 统计时间
   */
  private String statDay;

  /**
   * 步数总量
   */
  private Integer stepAmount;

  /**
   * 统计类型
   * @see com.beidou.hm.common.constant.StepStatConstant.StatTypeEnum
   */
  private Integer statType;

  /**
   * 步数转化的利润
   */
  private Integer stepProfit;

  /**
   * 当前状态
   * @see com.beidou.hm.common.constant.StepStatConstant.StatusEnum
   */
  private Integer status;

  public StepStat(String statDay, Integer stepAmount, Integer statType, Integer stepProfit) {
    this.statDay = statDay;
    this.stepAmount = stepAmount;
    this.statType = statType;
    this.stepProfit = stepProfit;
    this.status = StatusEnum.UNSETTLED.getStatus();
  }
}
