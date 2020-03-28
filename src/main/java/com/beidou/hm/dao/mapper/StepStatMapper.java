package com.beidou.hm.dao.mapper;

import com.beidou.hm.dao.domain.StepStat;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StepStatMapper {

  String TABLE_NAME = " hm_step_stat ";

  String ALL_COLUMN = " id, create_time AS createTime, update_time AS updateTime, "
      + "stat_day AS statDay, step_amount AS stepAmount, stat_type AS statType, "
      + "step_profit AS stepProfit, status ";

  @Insert("INSERT INTO " + TABLE_NAME + " (stat_day, step_amount, stat_type, step_profit, status) "
      + "VALUES(#{stepStat.statDay}, #{stepStat.stepAmount}, #{stepStat.statType}, "
      + "#{stepStat.stepProfit}, #{stepStat.status}) ON DUPLICATE KEY UPDATE "
      + "step_amount = #{stepStat.stepAmount}, stat_type = #{stepStat.statType}, "
      + "step_profit = #{stepStat.stepProfit}, status = #{stepStat.status}")
  @Options(useGeneratedKeys = true, keyProperty = "stepStat.id", keyColumn = "id")
  int add(@Param("stepStat") StepStat stepStat);

  @Update("UPDATE " + TABLE_NAME + " SET status = #{status} WHERE stat_day = #{statDay}")
  int updateStatus(@Param("statDay") String statDay, @Param("status") Integer status);

  @Select("<script>"
      + "SELECT " + ALL_COLUMN + " FROM " + TABLE_NAME + " "
      + "WHERE 1 = 1 "
      + "<if test='startDay != null'> AND stat_day <![CDATA[>=]]> #{startDay} </if>"
      + "<if test='endDay != null'> AND stat_day <![CDATA[<=]]> #{endDay} </if>"
      + "ORDER BY stat_day DESC "
      + "</script>")
  List<StepStat> listByStatDay(@Param("startDay") String startDay, @Param("endDay") String endDay);

  @Select("SELECT SUM(step_amount) FROM " + TABLE_NAME)
  long sumOfSteps();

  @Select("SELECT SUM(step_profit) FROM " + TABLE_NAME)
  int sumOfProfit();

}
