package com.eamon.zhuiqiu.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.news.service.NewsService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;


@Controller
@RequestMapping(path = "news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

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
	

	
	/**
	 * 根据id获取新闻
	 * @param newsId
	 * @return
	 */
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
	
	private static boolean isTest = false;
	
	@RequestMapping(path="test",method=RequestMethod.GET)
	@ResponseBody
	public Status test(){
		try{
			return new Status(isTest,StatusCode.SUCCESS,isTest,0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 编辑新闻
	 * @return
	 */
	@RequestMapping(path="offTest",method=RequestMethod.GET)
	@ResponseBody
	public Status offTest(){
		try{
			isTest = false;
			return new Status(true,StatusCode.SUCCESS,true,0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	@RequestMapping(path="onTest",method=RequestMethod.GET)
	@ResponseBody
	public Status onTest(){
		try{
			isTest = true;
			return new Status(true,StatusCode.SUCCESS,true,0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
}
