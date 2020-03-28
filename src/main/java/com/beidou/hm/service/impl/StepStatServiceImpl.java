package com.beidou.hm.service.impl;

import com.beidou.hm.common.constant.StepStatConstant.StatTypeEnum;
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
      stepProfit = 5 + account - 10;
    } else {
      stepProfit = 10 + (account - 15) * 2;
    }
    StepStat stepStat = new StepStat(statDay, stepAccount, StatTypeEnum.NORMAL.getType(), stepProfit);
    return 0 != stepStatMapper.add(stepStat);
  }

  @Override
  public boolean settleStep(String statDay) {
    if (StringUtils.isBlank(statDay)) {
      statDay = LocalDate.now().toString();
    }
    return 0 != stepStatMapper.updateStatus(statDay, 1);
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
