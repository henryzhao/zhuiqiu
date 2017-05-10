package com.eamon.zhuiqiu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.state.StatusException;
import com.eamon.zhuiqiu.state.Status;
import com.eamon.zhuiqiu.state.StatusCode;
import com.eamon.zhuiqiu.user.service.CaptchaService;



@Controller
@RequestMapping("captcha")
public class CaptchaController {

	@Autowired
	private CaptchaService captchaService;
	
	@RequestMapping(path = "")
	@ResponseBody
	public Status create(
			@RequestParam String phone
			){
		try {
			return new Status(true, StatusCode.SUCCESS, captchaService.create(phone), null);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
}
