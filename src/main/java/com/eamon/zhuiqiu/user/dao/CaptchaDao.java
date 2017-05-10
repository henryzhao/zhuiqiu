package com.eamon.zhuiqiu.user.dao;

import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.user.entity.Captcha;





@Repository
public interface CaptchaDao {

	int invalidOldCapthcha(int id);

	Captcha getCaptchaById(int capthaId);

	Captcha getCaptchaByPhone(String phone);

	int insertNewCaptcha(Captcha captcha);

	
	
	
}
