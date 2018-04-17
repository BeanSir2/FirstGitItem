package com.ruiyun.module.bee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseController;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.common.exception.RuiYunException;
import com.ruiyun.module.bee.entity.BeeCommonEntity;
import com.ruiyun.module.bee.entity.BeePersonnelEntity;
import com.ruiyun.module.bee.entity.BeePersonnelLogEntity;
import com.ruiyun.module.bee.entity.BeeTeamEntity;
import com.ruiyun.module.bee.entity.DataCommonEntity;
import com.ruiyun.module.bee.proxy.BeeteamProxy;
import com.ruiyun.module.bee.service.BeeManagerService;

/**
 * 行销人员管理的controller
 * @author fzy
 * @createTime 2017年6月23日
 */
@Controller
@RequestMapping("/bee")
public class BeelManagerController extends BaseController{

	@Autowired
	private BeeManagerService beeManagerService;

	@Autowired
	private BeeteamProxy beeteamProxy;

	@RequestMapping("personnel/index")
	public ModelAndView personnelindex(ModelAndView mv){
		mv.setViewName("module/bee/personnel/personnelindex");
		return mv;
	}

	/**
	 * 行销人员管理的数据列表
	 * @param entity
	 * @return
	 */
	@RequestMapping("personnel/getpersonnelgriddata")
	@ResponseBody
	public JQGridResultEntity<BeePersonnelEntity> getGridDataPersonnel(JQGridPageParams pageParams, BeePersonnelEntity entity){

		return beeManagerService.getGridDataPersonnel(pageParams, entity);

	}

	/**
	 * 行销团队的数据列表
	 * @param pageParams
	 * @param entity
	 * @return
	 */
	@RequestMapping("personnel/getteamgriddata")
	@ResponseBody
	public JQGridResultEntity<BeeTeamEntity> getGridDataTeamal(JQGridPageParams pageParams, BeeTeamEntity entity){

		return beeManagerService.getGridDataTeam(pageParams, entity);

	}
	/**
	 * 行销团队人员列表
	 * @param pageParams
	 * @param entity
	 * @return
	 */
	@RequestMapping("personnel/teamdata")
	@ResponseBody
	public JQGridResultEntity<BeePersonnelEntity> teamData(JQGridPageParams pageParams,Integer beeTeamId){

		return beeManagerService.addperson(pageParams, beeTeamId);

	}
	/**
	 * 删除行销人员中的数据
	 * @param beeTeamId
	 * @param beePersonnelId
	 * @return
	 */
	@RequestMapping("personnel/teamdatadel")
	@ResponseBody
	public BaseResult teamDataDel(BeeTeamEntity entity){
		try {
			return beeManagerService.teamDataDel(entity);
		} catch (RuiYunException ry) {

			ry.getStackTrace();
			return (BaseResult) ry.getObj();
		}
	}

	/**
	 * 行销人员添加列表
	 * @param pageParams
	 * @param beeTeamId
	 * @return
	 */
	@RequestMapping("personnel/addteamdata")
	@ResponseBody
	public JQGridResultEntity<BeePersonnelEntity> addteamData(JQGridPageParams pageParams,BeePersonnelEntity entity){

		return beeManagerService.addteamData(pageParams, entity);

	}

	/**
	 * 行销人员添加至团队
	 * @param beeTeamId
	 * @param beePersonnelId
	 * @return
	 * @throws RuiYunException 
	 */
	@RequestMapping("personnel/insertteam")
	@ResponseBody
	public BaseResult insertteam(BeePersonnelEntity entity,String beelist){
		try{

			return beeManagerService.insertteam(entity,beelist);
		} catch (RuiYunException ry) {

			ry.getStackTrace();
			return (BaseResult) ry.getObj();
		}


	}

	/**
	 * 行销人员添加视图跳转
	 * @param mv
	 * @return
	 */
	@RequestMapping("personnel/addpersonnel")
	public ModelAndView add(ModelAndView mv,BeeCommonEntity entity,String actionType){
		if("view".equals(actionType)){
			BeePersonnelEntity bee = beeManagerService.selectById(entity.getBeePersonnelId());
			DataCommonEntity data = beeManagerService.getData(entity);
			List<BeePersonnelLogEntity> log = beeManagerService.selectByBePersonnelId(entity.getBeePersonnelId());
			mv.addObject("data",data);
			mv.addObject("actionType","view");
			mv.addObject("log",log);
			mv.addObject("entity",bee);
		}else if("insert".equals(actionType)){
			mv.addObject("actionType","insert");
		}else{
			BeePersonnelEntity bee = beeManagerService.selectById(entity.getBeePersonnelId());
			mv.addObject("entity",bee);
			mv.addObject("actionType","modify");
		}
		mv.setViewName("module/bee/personnel/addpersonnellist");
		return mv;
	}

	@RequestMapping("personnel/addper")
	@ResponseBody
	public BaseResult addperson(BeePersonnelEntity entity,String actionType){
		try {

			return beeManagerService.addperson(entity,actionType);
		} catch (RuiYunException ry) {

			ry.getStackTrace();
			return (BaseResult) ry.getObj();
		}

	}


	/**
	 * 行销团队添加
	 * @param mv
	 * @return
	 */
	@RequestMapping("personnel/viewdata")
	public ModelAndView byteam(ModelAndView mv,BeeCommonEntity entity,int status){

		if(0 == status){
			//添加
			mv.addObject("actionType","insert");
		}else if(1 == status){
			BeeTeamEntity team = beeteamProxy.getByBeeTeamId(entity.getBeeTeamId());
			DataCommonEntity data = beeManagerService.getData(entity);
			List<BeePersonnelLogEntity> log = beeManagerService.selectByBeeTeamId(entity.getBeeTeamId());
			mv.addObject("entity",team);
			mv.addObject("data",data);
			mv.addObject("log",log);
			//修改
			mv.addObject("actionType","modify");
		}else{
			//查看
			mv.addObject("actionType","view");
		}
		//跳转
		mv.setViewName("module/bee/personnel/addlist");
		return mv;
	}

	/**
	 * 操作行销团队
	 * @param entity
	 * @return
	 */
	@RequestMapping("personnel/addteam")
	@ResponseBody
	public BaseResult addTeam(BeeTeamEntity entity,Integer flag){
		try {

			return beeManagerService.addTeam(entity,flag);
		}  catch (RuiYunException ry) {

			ry.getStackTrace();
			return (BaseResult) ry.getObj();
		}
	}

	@RequestMapping("personnel/disableteam")
	@ResponseBody
	public BaseResult disableTam(Integer beeTeamId){
		try {

			return beeManagerService.getDisableTeam(beeTeamId);
		} catch (RuiYunException e) {

			e.printStackTrace();
			return (BaseResult) e.getObj();
		}
	}

	/**
	 * 行销团队中的人员新增
	 * @param mv
	 * @return
	 */
	@RequestMapping("personnel/analysedata")
	public ModelAndView analysedata(Integer beeTeamId,Integer parentBeePersonnelId, ModelAndView mv){
		mv.addObject("beeTeamId",beeTeamId);
		mv.addObject("parentBeePersonnelId",parentBeePersonnelId);
		//添加团队人员
		mv.setViewName("module/bee/personnel/addpersonnel");
		return mv;
	}

	/**
	 * 跳转到添加团队人员
	 * @param mv
	 * @param id
	 * @return
	 */
	@RequestMapping("personnel/addteamperson")
	public ModelAndView bypersonteam(Integer beeTeamId,Integer parentBeePersonnelId,ModelAndView mv){
		//添加团队
		mv.addObject("beeTeamId",beeTeamId);
		mv.addObject("parentBeePersonnelId",parentBeePersonnelId);
		mv.setViewName("module/bee/personnel/addteaminfo");

		return mv;
	}


	/**
	 * 数据统计
	 * @return
	 */
	@RequestMapping("personnel/datateam")
	@ResponseBody
	public JQGridResultEntity<DataCommonEntity> statiste(JQGridPageParams pageParams,BeeCommonEntity entity){
		return beeManagerService.getData(pageParams, entity);

	}

}
