package com.eamon.zhuiqiu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.state.StatusException;
import com.eamon.zhuiqiu.state.RequestLimit;
import com.eamon.zhuiqiu.state.Status;
import com.eamon.zhuiqiu.state.StatusCode;
import com.eamon.zhuiqiu.user.service.UserService;



@Controller
@RequestMapping("user")
public class UserRoleController {

	//private final Logger Log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UserService userService;
	
	@RequestLimit(RequestLimit.SUPER_ADMIN_PRIVATE)
	@RequestMapping(path = "{userId}/assign/admin",method=RequestMethod.POST)
	@ResponseBody
	public Status role(
			@RequestParam int userId,
			@RequestParam int viewId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.changeRole(userId,RequestLimit.ADMIN), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{userId}/assign/teacher",method=RequestMethod.POST)
	@ResponseBody
	public Status roleTea(
			@RequestParam int userId,
			@RequestParam int viewId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.changeRole(userId,RequestLimit.TEACHER), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{userId}/assign/student",method=RequestMethod.POST)
	@ResponseBody
	public Status roleStu(
			@RequestParam int userId,
			@RequestParam int viewId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.changeRole(userId,RequestLimit.STUDENT), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{userId}/assign/parent",method=RequestMethod.POST)
	@ResponseBody
	public Status rolePar(
			@RequestParam int userId,
			@RequestParam int viewId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.changeRole(userId,RequestLimit.PARENT), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{userId}/assign/user",method=RequestMethod.POST)
	@ResponseBody
	public Status roleNor(
			@RequestParam int userId,
			@RequestParam int viewId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.changeRole(userId,RequestLimit.USER), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
}
