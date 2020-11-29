package com.eamon.zhuiqiu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.user.service.UserService;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;



@Controller
@RequestMapping("user")
public class UserBasicController {

	//private final Logger Log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(path = "register")
	@ResponseBody
	public Status register(
			@RequestParam String phone,
			@RequestParam String nickname,
			@RequestParam String password,
			@RequestParam int codeId,
			@RequestParam int code
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.register(phone, nickname, password, codeId, code), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
	@RequestMapping(path = "forget")
	@ResponseBody
	public Status forget(
			@RequestParam String phone,
			@RequestParam String password,
			@RequestParam int codeId,
			@RequestParam int code
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.forget(phone, password, codeId, code), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
	
	
	@RequestMapping(path = "login")
	@ResponseBody
	public Status login(
			@RequestParam String phone,
			@RequestParam String password
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.login(phone, password), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestMapping(path = "login/admin")
	@ResponseBody
	public Status loginA(
			@RequestParam String phone,
			@RequestParam String password
			){
		try {
			return new Status(true, StatusCode.SUCCESS, userService.loginAdmin(phone, password), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
}
