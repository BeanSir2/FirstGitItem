/**
 * 
 */
package com.ruiyun.module.bee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className:BuildingListPageController
 * @author:   wurong
 * @date:     2017年7月18日 上午11:09:22
 * @version:
 */
@Controller
@RequestMapping("build/buildlistpage")
public class BuildingListPageController {

	@RequestMapping("/bulidpage")
	public ModelAndView bulidinglistpage(ModelAndView mv){
		mv.setViewName("module/bee/buildlistpage/buildlistpage");
		return mv;
	}
	
	@RequestMapping("/addpage")
	public ModelAndView addpage(ModelAndView mv){
		mv.setViewName("module/bee/buildlistpage/buildadd");
		return mv;
	}
	
	@RequestMapping("/buildadd")
	public ModelAndView buildadd(ModelAndView mv){
		mv.setViewName("");
		return mv;
	}
}
