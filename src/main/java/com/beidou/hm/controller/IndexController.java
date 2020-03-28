package com.beidou.hm.controller;

import com.beidou.hm.dao.domain.StepStat;
import com.beidou.hm.service.StepStatService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
public class IndexController {

  @Autowired
  private StepStatService stepStatService;

  @GetMapping("/index")
  public String listLastMonth(Model model) {
    List<StepStat> statList = stepStatService.listLastMonth();
    model.addAttribute("statList", statList);
    model.addAttribute("totalStep", stepStatService.sumOfSteps());
    model.addAttribute("totalProfit", stepStatService.sumOfProfit());

    List<StepStat> subList = statList.subList(0, Math.min(7, statList.size()));
    int weekStep = subList.stream().mapToInt(StepStat::getStepAmount).sum();
    int weekProfit = subList.stream().mapToInt(StepStat::getStepProfit).sum();
    model.addAttribute("weekStep", weekStep);
    model.addAttribute("weekProfit", weekProfit);

    return "index";
  }

}
