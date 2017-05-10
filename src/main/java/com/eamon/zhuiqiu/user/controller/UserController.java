package com.eamon.zhuiqiu.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserController {

	private final Logger Log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UserService userService;
	
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path = "{userId}/token")
	@ResponseBody
	public Status checkToken(
			@PathVariable int userId,
			@RequestParam int viewId
			){
		try {
			Log.debug("yes");
			if(userId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true, StatusCode.SUCCESS, 0, null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}

	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path = "{userId}/logout")
	@ResponseBody
	public Status logout(
			@PathVariable int userId,
			@RequestParam int viewId
			){
		try {
			Log.debug("yes");
			if(userId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true, StatusCode.SUCCESS, userService.logout(viewId), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path = "{userId}/nickname",method=RequestMethod.POST)
	@ResponseBody
	public Status nickname(
			@PathVariable int userId,
			@RequestParam int viewId,
			@RequestParam String nickname
			){
		try {
			if(userId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true, StatusCode.SUCCESS, userService.changeNickname(viewId,nickname), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.USER_PRIVATE)
	@RequestMapping(path = "{userId}/head",method=RequestMethod.POST)
	@ResponseBody
	public Status head(
			@PathVariable int userId,
			@RequestParam int viewId,
			@RequestParam String head
			){
		try {
			if(userId!=viewId)throw new StatusException(StatusCode.PERMISSION_LOW);
			return new Status(true, StatusCode.SUCCESS, userService.changeHead(viewId,head), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
	@RequestMapping(path = "{userId}/info",method=RequestMethod.GET)
	@ResponseBody
	public Status info(
			@PathVariable int userId
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.getUserMapById(userId), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
}