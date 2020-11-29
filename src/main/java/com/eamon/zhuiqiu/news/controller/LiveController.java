package com.eamon.zhuiqiu.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.news.service.LiveService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;


@Controller
@RequestMapping(path = "news")
public class LiveController {

	@Autowired
	private LiveService liveService;
	
	
	/**
	 * 获取某个主题下的直播列表
	 * @return
	 */
	@RequestMapping(path="theme/{themeId}/live",method=RequestMethod.GET)
	@ResponseBody
	public Status getLive(
			@PathVariable int themeId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.getLiveListByTheme(themeId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 在某个主题下创建直播
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="theme/{themeId}/live",method=RequestMethod.POST)
	@ResponseBody
	public Status createLive(
			@PathVariable int themeId,
			@RequestParam String headPic,
			@RequestParam String title,
			@RequestParam(required=false,defaultValue="") String subTitle,
			@RequestParam(required=false,defaultValue="") String keywords,
			@RequestParam(required=false,defaultValue="") String des
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.postLive(themeId,headPic,title,subTitle,keywords,des),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

	/**
	 * 根据id获取直播详情
	 * @param liveId
	 * @return
	 */
	@RequestMapping(path="live/{liveId}",method=RequestMethod.GET)
	@ResponseBody
	public Status getLiveById(
			@PathVariable int liveId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.getLiveById(liveId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 删除直播
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="live/{liveId}/delete",method=RequestMethod.POST)
	@ResponseBody
	public Status liveDelete(
			@PathVariable int liveId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.deleteLiveById(liveId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 编辑直播
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="live/{liveId}/update",method=RequestMethod.POST)
	@ResponseBody
	public Status updateLive(
			@PathVariable int liveId,
			@RequestParam int themeId,
			@RequestParam String headPic,
			@RequestParam String title,
			@RequestParam(required=false,defaultValue="") String subTitle,
			@RequestParam(required=false,defaultValue="") String keywords,
			@RequestParam(required=false,defaultValue="") String des
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.updateLiveById(liveId,themeId,headPic,title,subTitle,keywords,des),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 根据id获取直播项
	 * @param liveId
	 * @return
	 */
	@RequestMapping(path="live/{liveId}/item",method=RequestMethod.GET)
	@ResponseBody
	public Status getLiveByIdItem(
			@PathVariable int liveId,
			@RequestParam(required=false,defaultValue="0") int lastId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.getLiveItemFromId(liveId,lastId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 根据id发布直播项
	 * @param liveId
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="live/{liveId}/item",method=RequestMethod.POST)
	@ResponseBody
	public Status postLiveByIdItem(
			@PathVariable int liveId,
			@RequestParam String content
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.postLiveItem(liveId,content),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 根据id删除直播项
	 * @param liveId
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path="live/item/{itemId}/delete",method=RequestMethod.POST)
	@ResponseBody
	public Status deleteLiveItem(
			@PathVariable int itemId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					liveService.deleteLiveItemById(itemId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

}
