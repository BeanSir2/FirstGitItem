package com.ruiyun.module.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseController;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.module.app.entity.AppJPushConfig;
import com.ruiyun.module.app.service.AppJPushConfigService;

/**
 * app极光推送配置controller
 * @author yaochen
 * @date 2017年6月5日
 */
@Controller
@RequestMapping("app/jpush/config")
public class AppJPushConfigController extends BaseController{

	@Autowired
	private AppJPushConfigService appJPushConfigService;
	
	/**
	 * 转到首页
	 * @param mv
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("module/app/jpush/config/index");
		return mv;
	}

	/**
	 * 转到新增页面
	 * @param mv
	 * @return
	 */
	@RequestMapping("toadd")
	public ModelAndView toAdd(ModelAndView mv) {
		mv.setViewName("module/app/jpush/config/jpushconfiginfo");
		mv.addObject("actionType", "add");
		return mv;
	}

	/**
	 * 转到修改页面
	 * @param mv
	 * @param versionId
	 * @return
	 */
	@RequestMapping("toedit")
	public ModelAndView toedit(ModelAndView mv, Integer jpushConfigId) {
		mv.addObject("data", appJPushConfigService.show(jpushConfigId));
		mv.addObject("actionType", "edit");
		mv.setViewName("module/app/jpush/config/jpushconfiginfo");
		return mv;
	}

	/**
	 * 获取列表数据
	 * @param pageParams
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("getjpushconfiglist")
	@ResponseBody
	public JQGridResultEntity<AppJPushConfig> getJPushConfigList(JQGridPageParams pageParams, String appName) {
		return appJPushConfigService.getJPushConfigList(pageParams, appName);
	}

	/**
	 * 保存新增
	 */
	@RequestMapping("add")
	@ResponseBody
	public BaseResult add(AppJPushConfig config) {
		return appJPushConfigService.addAppVersion(config);
	}
	
	/**
	 * 保存修改
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit(AppJPushConfig config) {
		return appJPushConfigService.editAppVersion(config);
	}
	
}
