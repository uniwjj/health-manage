package com.beidou.hm.controller;

import com.beidou.hm.service.StepStatService;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/step")
public class StepStatController {

  @Autowired
  private StepStatService stepStatService;

  @RequestMapping("/record")
  public String recordStep(
      @RequestParam(defaultValue = "0") int stepAccount,
      @RequestParam(required = false) String statDay) {
    log.info("record step, statDay={}, step={}", statDay, stepAccount);
    stepStatService.addStepStat(statDay, stepAccount);
    return "redirect:/index";
  }

  @RequestMapping("/settle")
  public String settleStep(@RequestParam(required = false) String statDay,
      @RequestParam Integer statType) {
    log.info("settle step, statDay={}, statType={}", statDay, statType);
    stepStatService.settleStep(statDay, statType);
    return "redirect:/index";
  }

  @Scheduled(cron = "0 10 0 * * ?")
  public void recordToday() {
    String today = LocalDate.now().toString();
    log.info("flush current day step");
    stepStatService.addStepStat(today, 0);
  }

}
