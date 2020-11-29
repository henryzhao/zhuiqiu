package com.eamon.zhuiqiu.lesson.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.lesson.service.LessonService;
import com.eamon.zhuiqiu.user.service.UserService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;


@Controller
@RequestMapping(path = "lesson")
public class LessonController {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取最近的lesson
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
			return new Status(true,StatusCode.SUCCESS,lessonService.selectAllLesson(page,rows),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 创建lesson
	 * @return
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="",method=RequestMethod.POST)
	@ResponseBody
	public Status create(
			@RequestParam int creatorId,
			@RequestParam String lessonName,
			@RequestParam String location,
			@RequestParam String tags,
			@RequestParam int peopleNum,
			
			@RequestParam String image,
			@RequestParam String des,
			@RequestParam String contact,
			
			@RequestParam int week,
			@RequestParam double price,
			
			@RequestParam long timeStart,
			@RequestParam long timeEnd,
			
			@RequestParam long joinStart,
			@RequestParam long joinEnd,
			
			@RequestParam long dateStart,
			@RequestParam long dateEnd,
			
			@RequestParam int viewId
			){
		try{
			if(creatorId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true,StatusCode.SUCCESS,lessonService.createLesson(
					lessonName, 
					creatorId, 
					location, 
					tags, 
					peopleNum, 
					image,
					des, 
					contact, 
					week, 
					price, 
					timeStart, 
					timeEnd, 
					joinStart, 
					joinEnd,
					dateStart,
					dateEnd
					),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 通过Id获取指定课程
	 * @return
	 */
	@RequestMapping(path="{lessonId}",method=RequestMethod.GET)
	@ResponseBody
	public Status getLessonById(
			@PathVariable int lessonId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,lessonService.getLessonMapById(lessonId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	/**
	 * 参与课程
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{lessonId}/join",method=RequestMethod.GET)
	@ResponseBody
	public Status join(
			@PathVariable int lessonId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,lessonService.join(lessonId,viewId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 是否参与课程
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{lessonId}/isJoin",method=RequestMethod.GET)
	@ResponseBody
	public Status isJoin(
			@PathVariable int lessonId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,lessonService.isJoin(lessonId,viewId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	
	
	/**
	 * 退出课程
	 * @return
	 */
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path="{lessonId}/exit",method=RequestMethod.GET)
	@ResponseBody
	public Status exit(
			@PathVariable int lessonId,
			@RequestParam int viewId
			){
		try{
			return new Status(true,StatusCode.SUCCESS,lessonService.exit(viewId,lessonId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	
	/**
	 * 我的课程
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
			if(userId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true,StatusCode.SUCCESS,
					lessonService.selectUsersLesson(userId),0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}

	/**
	 * 课程用户
	 * @return
	 * 
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="{lessonId}/user",method=RequestMethod.GET)
	@ResponseBody
	public Status joinU(
			@PathVariable int lessonId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					userService.getLessonUser(lessonId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 查看课程教师
	 * @return
	 * 
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="{lessonId}/teacher",method=RequestMethod.GET)
	@ResponseBody
	public Status getTeachaer(
			@PathVariable int lessonId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					userService.getLessonTeacher(lessonId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 为课程分配教师
	 * @return
	 * 
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="{lessonId}/teacher/{teacherId}",method=RequestMethod.GET)
	@ResponseBody
	public Status assignTeachaer(
			@PathVariable int lessonId,
			@PathVariable int teacherId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					lessonService.joinTeacher(lessonId, teacherId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 为课程分配助教
	 * @return
	 * 
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="{lessonId}/assistant/{assistantId}",method=RequestMethod.GET)
	@ResponseBody
	public Status assignAssistant(
			@PathVariable int lessonId,
			@PathVariable int assistantId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					lessonService.joinAssistant(lessonId, assistantId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	
	/**
	 * 查看课程助教
	 * @return
	 * 
	 */
	@RequestLimit(RequestLimit.TEACHER_PRIVATE)
	@RequestMapping(path="{lessonId}/assistant",method=RequestMethod.GET)
	@ResponseBody
	public Status getAssistant(
			@PathVariable int lessonId
			){
		try{
			return new Status(
					true,
					StatusCode.SUCCESS,
					userService.getLessonAssistant(lessonId),
					0);
		}catch(Exception e){
			e.printStackTrace();
			return new Status(false,StatusCode.FAILED,0,0);
		}
	}
	

}
