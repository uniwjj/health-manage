package com.beidou.hm.service;

import com.beidou.hm.dao.domain.StepStat;
import java.util.List;

public interface StepStatService {

  boolean addStepStat(String statDay, int stepAccount);

  boolean settleStep(String statDay);

  List<StepStat> listLastMonth();

  Long sumOfSteps();

  Integer sumOfProfit();
}
