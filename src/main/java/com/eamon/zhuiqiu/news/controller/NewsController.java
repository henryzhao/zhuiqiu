package com.eamon.zhuiqiu.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.news.service.NewsService;
import com.eamon.zhuiqiu.news.service.ThemeService;
import com.eamon.zhuiqiu.state.RequestLimit;
import com.eamon.zhuiqiu.state.Status;
import com.eamon.zhuiqiu.state.StatusCode;


@Controller
@RequestMapping(path = "news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
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
	 * 获取某个主题下的新闻
	 * @return
	 */
	@RequestMapping(path="theme/{themeId}",method=RequestMethod.GET)
	@ResponseBody
	public Status getNews(
			@PathVariable int themeId,
			@RequestParam int page,
			@RequestParam(required=false,defaultValue="10") int rows
			){
		try{
			return new Status(true,StatusCode.SUCCESS,newsService.getNewsByThemeId(themeId,page,rows),0);
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
	
	
	/**
	 * 在某个主题下创建新闻
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="theme/{themeId}",method=RequestMethod.POST)
	@ResponseBody
	public Status createNews(
			@PathVariable int themeId,
			@RequestParam String headPic,
			@RequestParam String title,
			@RequestParam(required=false) String subTitle,
			@RequestParam String author,
			@RequestParam String source,
			@RequestParam(required=false) String keywords,
			@RequestParam(required=false) String navContent,
			@RequestParam String content
			){
		try{
			return new Status(true,StatusCode.SUCCESS,newsService.createNews(themeId,headPic,title,subTitle,author,source,keywords,navContent,content),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

	
	@RequestMapping(path="{newsId}",method=RequestMethod.GET)
	@ResponseBody
	public Status getNewsById(
			@PathVariable int newsId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,newsService.getNewsById(newsId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 删除新闻
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="{newsId}/delete",method=RequestMethod.GET)
	@ResponseBody
	public Status newsDelete(
			@PathVariable int newsId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,newsService.deleteNewsById(newsId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 编辑新闻
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="{newsId}/update",method=RequestMethod.POST)
	@ResponseBody
	public Status updateNews(
			@PathVariable int newsId,
			@RequestParam String headPic,
			@RequestParam String title,
			@RequestParam(required=false) String subTitle,
			@RequestParam String author,
			@RequestParam String source,
			@RequestParam(required=false) String keywords,
			@RequestParam(required=false) String navContent,
			@RequestParam String content
			){
		try{
			return new Status(true,StatusCode.SUCCESS,newsService.updateNews(newsId,headPic,title,subTitle,author,source,keywords,navContent,content),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
}
