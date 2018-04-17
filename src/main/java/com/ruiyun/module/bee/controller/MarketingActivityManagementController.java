/**
 * 
 */
package com.ruiyun.module.bee.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseController;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.module.bee.entity.AttenDetailEntity;
import com.ruiyun.module.bee.entity.BeeCommonEntity;
import com.ruiyun.module.bee.entity.BeeTaskLogAndTeamEntity;
import com.ruiyun.module.bee.entity.ExtenEntity;
import com.ruiyun.module.bee.entity.ReportEntity;
import com.ruiyun.module.bee.entity.SignEntity;
import com.ruiyun.module.bee.entity.SubscriptionEntity;
import com.ruiyun.module.bee.entity.TokerEntity;
import com.ruiyun.module.bee.entity.VisitEntity;
import com.ruiyun.module.bee.service.MarketingActivityManagementService;
import com.ruiyun.utils.ExcelException;
import com.ruiyun.utils.ExcelUtil;

/**
 * @className:MarketingActivityManagementController
 * @author:   wurong
 * @date:     2017年6月23日 下午4:26:21
 * @version:
 */

@Controller
@RequestMapping("/beeactivity/marketactivity")
public class MarketingActivityManagementController extends BaseController{

	@Autowired
	private MarketingActivityManagementService marketingActivityManagementService;
	
	@RequestMapping("/marketactivityindex")
	public ModelAndView marketActivity(ModelAndView mv){
		mv.setViewName("module/bee/marketactivity/marketactivityindex");
		return mv;
	}
	
	/**
	 * 获取列表数据
	 * @param pageParams
	 * @return
	 */
	@RequestMapping("/getgriddatabee")
	@ResponseBody
	public JQGridResultEntity<BeeTaskLogAndTeamEntity> getGridData(JQGridPageParams pageParams,String taskName,String companyName, String startDate,String endDate,
			String projectInfoName){
		return marketingActivityManagementService.getGridData(pageParams,taskName,companyName,startDate,endDate,projectInfoName);
	}
	
	
	/**
	 * 数据查看
	 * @param mv
	 * @return
	 */
	@RequestMapping("/getgriddataLook")
	public ModelAndView maketActivityLook(Integer beeTeamId,BeeCommonEntity entity,Integer beeTaskId,ModelAndView mv,String personnelName){
		Map<String, Object> map = marketingActivityManagementService.extension(entity,personnelName);
		String titleDate =  marketingActivityManagementService.getAttTaskDate(beeTeamId,beeTaskId);
		List<Map<String,Object>> log = marketingActivityManagementService.getLogList(beeTaskId);
		mv.addObject("map", map);
		mv.addObject("beeTeamId", beeTeamId);
		mv.addObject("beeTaskId", beeTaskId);
		mv.addObject("titleDate", titleDate);
		mv.addObject("log",log);
		mv.setViewName("module/bee/marketactivity/marketactivitylook");
		return mv;
	}
	
	/**
	 * 考勤列表
	 * @param pageParams
	 * @param beeTeamId
	 * @return
	 */
	@RequestMapping("/getgriddataAttendance")
	@ResponseBody
	public JQGridResultEntity<HashMap<String, Object>> getAttendance(JQGridPageParams pageParams,Integer beeTeamId,String personnelName,Integer beeTaskId){
		
		return marketingActivityManagementService.getAttendace(pageParams, beeTeamId,personnelName,beeTaskId);
	}
	
	/**
	 * 拓客列表
	 * @param pageParams
	 * @param beeTeamId
	 * @param beeTaskId
	 * @return
	 */
	@RequestMapping("/getgriddataLookExten")
	@ResponseBody
	public JQGridResultEntity<ExtenEntity> getExtension(JQGridPageParams pageParams,BeeCommonEntity entity,String epersonnelName){
		
		return marketingActivityManagementService.getBB(pageParams,entity,epersonnelName);
	}
	
	/**
	 * 导出
	 * @return
	 */
	@RequestMapping("extenDetailData")
	@ResponseBody
	public BaseResult extenDetailData(Integer beeTaskId,Integer beeTeamId,BeeCommonEntity entity,String epersonnelName){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.extenDetailData(beeTaskId,beeTeamId,entity,epersonnelName),
					marketingActivityManagementService.extenDetailTitle(), "拓客明细信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	
	
	/**
	 * 跳转考勤明细页面
	 * @param mv
	 * @return
	 */
	@RequestMapping("/attendancedetailHtml")
	public ModelAndView attendancedetailHtml(ModelAndView mv,Integer beePersonnelId){
		mv.setViewName("module/bee/marketactivity/attendancedetail");
		return mv;
	}
	
	/**
	 *跳转拓客数据展示页面
	 * @param mv
	 * @return
	 */
	@RequestMapping("/tokerHtml")
	public ModelAndView tokerHtml(ModelAndView mv,Integer beeTaskId,Integer beePersonnelId){
		mv.addObject("beeTaskId",beeTaskId);
		mv.addObject("beePersonnelId",beePersonnelId);
		mv.setViewName("module/bee/marketactivity/tokerList");
		return mv;
	}
	
	 /**
	  * 拓客数据展示列表
	  * @param pageParams
	  * @param beeTaskId
	  * @param beePersonnelId
	  * @return
	  */
	@RequestMapping("/tokerHtmlGrid")
	@ResponseBody
	public JQGridResultEntity<TokerEntity> tokerHtml(JQGridPageParams pageParams,Integer beeTaskId,Integer beePersonnelId){
		
		return marketingActivityManagementService.tokerHtml(pageParams,beeTaskId,beePersonnelId);
	}
	
	/**
	 * 拓客数据导出
	 * @return
	 */
	@RequestMapping("export")
	@ResponseBody
	public BaseResult export(Integer beeTaskId,Integer beePersonnelId){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.excelData(beeTaskId,beePersonnelId),
					marketingActivityManagementService.getTitle(), "拓客信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	
	
	/**
	 * 报备数据展示页面
	 * @param mv
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/reportHtml")
	public ModelAndView reportHtml(ModelAndView mv,Integer beeTaskId,Integer beePersonnelId){
		mv.addObject("beeTaskId",beeTaskId);
		mv.addObject("beePersonnelId",beePersonnelId);
		mv.setViewName("module/bee/marketactivity/reportList");
		return mv;
	}
	
	/**
	  * 报备数据展示列表
	  * @param pageParams
	  * @param beeTaskId
	  * @param beePersonnelId
	  * @return
	  */
	@RequestMapping("/reportHtmlGrid")
	@ResponseBody
	public JQGridResultEntity<ReportEntity> reportHtml(JQGridPageParams pageParams,Integer beeTaskId,Integer beePersonnelId){
		
		return marketingActivityManagementService.reportHtml(pageParams,beeTaskId,beePersonnelId);
	}
	
	
	/**
	 * 报备数据导出
	 * @return
	 */
	@RequestMapping("reportexport")
	@ResponseBody
	public BaseResult reportexport(Integer beeTaskId,Integer beePersonnelId){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.reportData(beeTaskId,beePersonnelId),
					marketingActivityManagementService.getreportTitle(), "报备信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	/**
	 * 到访数据展示页面
	 * @param mv
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/visitListHtml")
	public ModelAndView visitListHtml(ModelAndView mv,Integer beeTaskId,Integer beePersonnelId){
		mv.addObject("beeTaskId",beeTaskId);
		mv.addObject("beePersonnelId",beePersonnelId);
		mv.setViewName("module/bee/marketactivity/visitList");
		return mv;
	}
	
	/**
	 * 到访数据列表
	 * @param pageParams
	 * @param beeTaskId
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/visitHtmlGrid")
	@ResponseBody
	public JQGridResultEntity<VisitEntity> visitHtml(JQGridPageParams pageParams,Integer beeTaskId,Integer beePersonnelId){
		
		return marketingActivityManagementService.visitHtml(pageParams,beeTaskId,beePersonnelId);
	}
	
	
	/**
	 * 到访数据导出
	 * @return
	 */
	@RequestMapping("visitexport")
	@ResponseBody
	public BaseResult visitexport(Integer beeTaskId,Integer beePersonnelId){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.visitData(beeTaskId,beePersonnelId),
					marketingActivityManagementService.getvisitTitle(), "到访信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	/**
	 * 认购数据展示页面
	 * @param mv
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/subscriptionListHtml")
	public ModelAndView subscriptionListHtml(ModelAndView mv,Integer beeTaskId,Integer beePersonnelId){
		mv.setViewName("module/bee/marketactivity/subscriptionList");
		mv.addObject("beeTaskId",beeTaskId);
		mv.addObject("beePersonnelId",beePersonnelId);
		return mv;
	}
	
	
	/**
	 * 认购数据列表
	 * @param pageParams
	 * @param beeTaskId
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/subscriptionHtmlGrid")
	@ResponseBody
	public JQGridResultEntity<SubscriptionEntity> subscriptionHtml(JQGridPageParams pageParams,Integer beeTaskId,Integer beePersonnelId){
		
		return marketingActivityManagementService.subscriptionHtml(pageParams, beeTaskId, beePersonnelId);
	}
	
	/**
	 * 认购数据导出
	 * @return
	 */
	@RequestMapping("subexport")
	@ResponseBody
	public BaseResult subexport(Integer beeTaskId,Integer beePersonnelId){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.subData(beeTaskId,beePersonnelId),
					marketingActivityManagementService.getsubTitle(), "到访信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	/**
	 * 签约数据展示页面
	 * @param mv
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/signListHtml")
	public ModelAndView signListHtml(ModelAndView mv,Integer beeTaskId,Integer beePersonnelId){
		mv.setViewName("module/bee/marketactivity/signList");
		mv.addObject("beeTaskId",beeTaskId);
		mv.addObject("beePersonnelId",beePersonnelId);
		return mv;
	}
	
	
	/**
	 * 签约数据列表
	 * @param pageParams
	 * @param beeTaskId
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/signHtmlGrid")
	@ResponseBody
	public JQGridResultEntity<SignEntity> signHtml(JQGridPageParams pageParams,Integer beeTaskId,Integer beePersonnelId){
		
		return marketingActivityManagementService.signHtml(pageParams, beeTaskId, beePersonnelId);
	}
	
	/**
	 * 签约数据导出
	 * @return
	 */
	@RequestMapping("signexport")
	@ResponseBody
	public BaseResult signexport(Integer beeTaskId,Integer beePersonnelId){
		try {
			ExcelUtil.listToExcel(
					marketingActivityManagementService.signData(beeTaskId,beePersonnelId),
					marketingActivityManagementService.getsignTitle(), "到访信息列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}
	
	
	
	/**
	 * 打卡明细数据列表
	 * @param pageParams
	 * @param beeTaskId
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("/attenGrid")
	@ResponseBody
	public JQGridResultEntity<AttenDetailEntity> attenGrid(JQGridPageParams pageParams,Integer beePersonnelId,String attendDate){
		return marketingActivityManagementService.attenGrid(pageParams, beePersonnelId,attendDate);
	}
	
	@RequestMapping("/atten")
	public ModelAndView atten(ModelAndView mv,Integer beePersonnelId,String attendDate){
		mv.addObject("beePersonnelId", beePersonnelId);
		mv.addObject("attendDate", attendDate);
		Map<String, Object> map = marketingActivityManagementService.findAtten(beePersonnelId, attendDate);
		mv.addObject("map", map);
		mv.setViewName("module/bee/marketactivity/attendancedetail");
		return mv;
	} 

	/**
	 * 考勤明细导出
	 * @return
	 */
	@RequestMapping("attenDetailexport")
	@ResponseBody
	public BaseResult attenexport(Integer beePersonnelId,String attendDate){
		try {
			List<Map<String, Object>> list = marketingActivityManagementService.attDetailData(beePersonnelId,attendDate);
			BigDecimal alldays = new BigDecimal(0);
			for (Map<String, Object> ma : list) {
				 BigDecimal  day = new BigDecimal(ma.get("valid_day").toString());
				 alldays = alldays.add(day);
			}
			Map<String, Object> total = new HashMap<>();
			total.put("personnel_name", "总计");
			total.put("attend_date", "");
			total.put("address","");
			total.put("sign_radius", " ");
			total.put("signTime", "");
			total.put("signFloatMinute", "");
			total.put("valid_day",alldays);
			list.add(total);
			ExcelUtil.listToExcel(
					list,
					marketingActivityManagementService.attDetailTitle(), "考勤明细列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出失败！");
		}
		return generateResult(true, "导出成功！");
	}

	
	/**
	 * 导出行销活动管理中的考勤明细
	 * @param beeTeamId
	 * @param personnelName
	 * @param beeTaskId
	 * @return
	 */
	@RequestMapping("downloadexcel")
	@ResponseBody
	public BaseResult downloadAttendanceDetailsExcel(Integer beeTeamId,String personnelName,Integer beeTaskId){
		try {
			List<Map<String, Object>> list = marketingActivityManagementService.downloadAttendanceDetailsExcel(beeTeamId, personnelName, beeTaskId);
			
			
			ExcelUtil.listToExcel(list, marketingActivityManagementService.downloadTitle(), "行销拓客列表", getResponse());
		} catch (ExcelException e) {
			e.printStackTrace();
			return generateResult(false, "导出异常!");
		}
		return generateResult(true, "导出成功!");
		
	} 

	

}
