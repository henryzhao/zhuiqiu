package com.eamon.zhuiqiu.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.news.service.ThemeService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;


@Controller
@RequestMapping(path = "news")
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;
	
	
	/**
	 * 获取主题列表
	 * @return
	 */
	@RequestMapping(path="theme",method=RequestMethod.GET)
	@ResponseBody
	public Status getTheme(){
		try{
			return new Status(true,StatusCode.SUCCESS,themeService.getThemes(),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 创建新主题
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="theme",method=RequestMethod.POST)
	@ResponseBody
	public Status newTheme(
			@RequestParam String cName, 
			@RequestParam String eName, 
			@RequestParam String keywords, 
			@RequestParam String des,
			@RequestParam boolean isHide
			){
		try{
			return new Status(true,StatusCode.SUCCESS,themeService.addTheme(cName, eName, keywords, des,isHide),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

	
	/**
	 * 删除主题
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="theme/{themeId}/delete",method=RequestMethod.GET)
	@ResponseBody
	public Status getNewsDelete(
			@PathVariable int themeId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,themeService.deleteThemeById(themeId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	
	
}
