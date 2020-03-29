package com.beidou.hm.service.impl;

import com.beidou.hm.common.constant.StepStatConstant.StatTypeEnum;
import com.beidou.hm.common.constant.StepStatConstant.StatusEnum;
import com.beidou.hm.dao.domain.StepStat;
import com.beidou.hm.dao.mapper.StepStatMapper;
import com.beidou.hm.service.StepStatService;
import com.google.common.base.Preconditions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StepStatServiceImpl implements StepStatService {

  @Autowired
  private StepStatMapper stepStatMapper;

  @Override
  public boolean addStepStat(String statDay, int stepAccount) {
    Preconditions.checkArgument(stepAccount >= 0);
    if (StringUtils.isBlank(statDay)) {
      statDay = LocalDate.now().toString();
    }
    checkStatDay(statDay);
    List<StepStat> stepStats = stepStatMapper.listByStatDay(statDay, statDay);
    if (CollectionUtils.isNotEmpty(stepStats) && stepStats.get(0).getStatus() == 1) {
      return false;
    }
    int stepProfit = 0;
    int account =  stepAccount / 1000;
    if (account <= 5) {
      stepProfit = -(5 - account) * 3 - 8;
    } else if (account <= 8) {
      stepProfit = -(8 - account) * 2 - 2;
    } else if (account < 10) {
      stepProfit = -(10 - account);
    } else if (account <= 15) {
      stepProfit = 10 + (account - 10) * 2;
    } else if (account <= 20) {
      stepProfit = 20 + (20 - account);
    } else {
      stepProfit = 25 - (account - 20);
    }
    StepStat stepStat = new StepStat(statDay, stepAccount, StatTypeEnum.NORMAL.getType(), stepProfit);
    return 0 != stepStatMapper.add(stepStat);
  }

  @Override
  public boolean settleStep(String statDay, Integer statType) {
    if (StringUtils.isBlank(statDay)) {
      statDay = LocalDate.now().toString();
    }
    StepStat stat = stepStatMapper.getByStatDay(statDay);
    if (stat == null || StatusEnum.SETTLED.equalWith(stat.getStatus())) {
      throw new RuntimeException("无运动信息或者已结算");
    }
    stat.setStatType(statType);
    stat.setStatus(StatusEnum.SETTLED.getStatus());
    if (!StatTypeEnum.NORMAL.equalWith(statType)) {
      stat.setStepProfit(0);
    }
    return 0 != stepStatMapper.update(stat);
  }

  @Override
  public List<StepStat> listLastMonth() {
    String startDay = LocalDate.now().minusDays(30).toString();
    return stepStatMapper.listByStatDay(startDay, null);
  }

  @Override
  public Long sumOfSteps() {
    return stepStatMapper.sumOfSteps();
  }

  @Override
  public Integer sumOfProfit() {
    return stepStatMapper.sumOfProfit();
  }

  private void checkStatDay(String statDay) {
    Preconditions.checkArgument(StringUtils.isNotBlank(statDay));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date date = sdf.parse(statDay);
      Preconditions.checkArgument(System.currentTimeMillis() >= date.getTime());
    } catch (ParseException e) {
      throw new RuntimeException("invalid stat day");
    }
  }

}
