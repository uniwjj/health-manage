<#include "./layout/header.ftl"/>

<div>
本活动主要为了女主人穿上美丽的婚纱，保持健康的身体而用，奖罚只是❤️的鞭策！
</br></br>

<form name="record" action="${request.contextPath}/step/record" method="get">
  当前步数: <input type="text" name="stepAccount">&nbsp;&nbsp;<input type="submit">
</form>

<form name="record" action="${request.contextPath}/step/settle" method="get">
  结算类型：
  <select name="statType">
    <option value="0">正常</option>
    <option value="1">亲戚拜访</option>
    <option value="2">休息</option>
  </select>
  &nbsp;&nbsp;
  <input type="submit" value="结算">
</form>
</br>

哇塞，宝宝一共走了<span style="color:red;font-weight:bold">${totalStep!}</span>步，
<#if (totalProfit > 0)>
  赚了<span style="color:red;font-weight:bold">${totalProfit!}</span>块钱，好棒棒！
<#else>
  亏了<span style="color:red;font-weight:bold">${totalProfit!}</span>块钱，还需要努力呀！
</#if>
</br>

最近一周一共走了<span style="color:red;font-weight:bold">${weekStep!}</span>步，
<#if (weekProfit > 0)>
  赚了<span style="color:red;font-weight:bold">${weekProfit!}</span>块钱，好棒棒！
<#else>
  亏了<span style="color:red;font-weight:bold">${weekProfit!}</span>块钱，还需要努力呀！
</#if>
</br>
</br>

最近一月的情况统计：
</br>
<#assign colNum = 4>
<table border='1' align="left">
  <tr>
    <td align='center' width='150'>日期</td>
    <td align='center' width='150'>步数</td>
    <td align='center' width='150'>类型</td>
    <td align='center' width='150'>收益</td>
    <td align='center' width='150'>状态</td>
  </tr>
    <#list statList as row>
      <tr>
        <td align='center' width='150'>${row.statDay!}</td>
        <td align='center' width='150'>${row.stepAmount!}</td>
        <#if (row.statType = 0)>
          <td align='center' width='150'>正常</td>
        <#elseif (row.statType = 1)>
          <td align='center' width='150'>亲戚拜访</td>
        <#else>
          <td align='center' width='150'>休息</td>
        </#if>
        <td align='center' width='150'>${row.stepProfit!}</td>
        <#if (row.status = 0)>
          <td align='center' width='150'>未结算</td>
        <#else>
          <td align='center' width='150'>已结算</td>
        </#if>
      </tr>
    </#list>
</table>
</div>

<#include "./layout/footer.ftl"/>
