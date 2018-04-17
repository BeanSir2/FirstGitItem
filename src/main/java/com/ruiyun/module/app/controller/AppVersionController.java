package com.ruiyun.module.app.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseController;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.module.app.entity.AppVersion;
import com.ruiyun.module.app.service.AppVersionService;

/**
 * app版本管理controller
 * 
 * @author yaochen
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("app/version")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;

	/**
	 * 转到首页
	 * @param mv
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("module/app/version/appversionindex");
		return mv;
	}

	/**
	 * 转到新增页面
	 * @param mv
	 * @return
	 */
	@RequestMapping("toadd")
	public ModelAndView toAdd(ModelAndView mv) {
		mv.setViewName("module/app/version/appversioninfo");
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
	public ModelAndView toedit(ModelAndView mv, Integer versionId) {
		mv.addObject("data", appVersionService.show(versionId));
		mv.addObject("actionType", "edit");
		mv.setViewName("module/app/version/appversioninfo");
		return mv;
	}

	/**
	 * 获取列表数据
	 * @param pageParams
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("getversionlist")
	@ResponseBody
	public JQGridResultEntity<AppVersion> getVersionList(JQGridPageParams pageParams, AppVersion appVersion) {
		return appVersionService.getVersionList(pageParams, appVersion);
	}

	/**
	 * 保存新增
	 */
	@RequestMapping("add")
	@ResponseBody
	public BaseResult add(AppVersion appVersion) {
		return appVersionService.addAppVersion(appVersion);
	}
	
	/**
	 * 保存修改
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit(AppVersion appVersion) {
		return appVersionService.editAppVersion(appVersion);
	}
	
	/**
	 * 启用
	 * @param versionId
	 * @return
	 */
	@RequestMapping("enable")
	@ResponseBody
	public BaseResult enable(Integer versionId) {
		return appVersionService.enable(versionId);
	}
	
	/**
	 * 废除
	 * @param versionId
	 * @return
	 */
	@RequestMapping("disable")
	@ResponseBody
	public BaseResult disable(Integer versionId) {
		return appVersionService.disable(versionId);
	}
	
	/**
	 * 上传
	 * @param versionId
	 * @return
	 */
	@RequestMapping("uploadapk")
	@ResponseBody
	public BaseResult uploadApk(String filePath,MultipartFile apkFile) {
		return appVersionService.uploadApk(filePath,apkFile);
	}
	
	@RequestMapping("listDatas")
	@ResponseBody
	public ModelAndView listDatas(){
		
		
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject(list);
		modelAndView.setViewName("listDatas");
		return modelAndView;
	}
}
