package com.eamon.zhuiqiu.activity.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.activity.service.ActivityService;
import com.eamon.zhuiqiu.state.RequestLimit;
import com.eamon.zhuiqiu.state.Status;
import com.eamon.zhuiqiu.state.StatusCode;
import com.eamon.zhuiqiu.state.StatusException;


@Controller
@RequestMapping(path = "activity")
public class ActivityController {

	
	private final Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 获取最近的Activity
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(path="",method=RequestMethod.GET)
	@ResponseBody
	public Status recent(
			@RequestParam int page,
			@RequestParam(required=false,defaultValue="10") int rows
			){
		try{
			
			return new Status(true,StatusCode.SUCCESS,activityService.selectAllActivity(page,rows),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 创建Activity
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="",method=RequestMethod.POST)
	@ResponseBody
	public Status create(
			@RequestParam int creatorId,
			@RequestParam String location,
			@RequestParam String tags,
			@RequestParam int peopleNum,
			
			@RequestParam String des,
			@RequestParam String contact,
			
			@RequestParam long timeStart,
			@RequestParam long timeEnd,
			
			@RequestParam long joinStart,
			@RequestParam long joinEnd,
			
			@RequestParam int viewId
			){
		try{
			if(creatorId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true,
					StatusCode.SUCCESS,
					activityService.create(
							creatorId,
							location,
							tags,
							peopleNum,
							des,
							contact,
							timeStart,
							timeEnd,
							joinStart,
							joinEnd
							),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 通过Id获取指定Activity
	 * @param id 
	 * @return
	 */
	@RequestMapping(path="{activityId}",method=RequestMethod.GET)
	@ResponseBody
	public Status getActivityById(
			@PathVariable int activityId){
		try{
			return new Status(true,StatusCode.SUCCESS,activityService.getActivityById(activityId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	
	/**
	 * 参与活动
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{activityId}/join",method=RequestMethod.POST)
	@ResponseBody
	public Status join(
			@PathVariable int activityId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,activityService.joinActivity(viewId, activityId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 是否参与活动
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{activityId}/isJoin",method=RequestMethod.GET)
	@ResponseBody
	public Status isJoin(
			@PathVariable int activityId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,activityService.isJoinActivity(viewId, activityId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	/**
	 * 退出活动
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{activityId}/exit",method=RequestMethod.POST)
	@ResponseBody
	public Status exit(
			@PathVariable int activityId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,
					activityService.exitActivity(viewId, activityId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

	/**
	 * 我参与的
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="user/{userId}",method=RequestMethod.GET)
	@ResponseBody
	public Status iJoin(
			@PathVariable int userId,
			@RequestParam int viewId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					activityService.selectUsersAcitvity(userId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 获取活动的所有用户
	 * @return
	 */
	@RequestMapping(path="{activityId}/user",method=RequestMethod.GET)
	@ResponseBody
	public Status joinU(
			@PathVariable int activityId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					activityService.selectAcitvitysUser(activityId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	

}
