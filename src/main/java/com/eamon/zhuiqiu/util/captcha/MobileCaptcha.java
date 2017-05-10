package com.eamon.zhuiqiu.util.captcha;


public class MobileCaptcha {
	
	public static void sendCaptcha(String phone,String code){
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MESSAGEXsend submail = new MESSAGEXsend(config);
		submail.addTo(phone);
		submail.setProject("7KIFZ1");
		submail.addVar("code", code);
		submail.xsend();
	}
	
}
