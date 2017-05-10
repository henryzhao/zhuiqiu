package com.eamon.zhuiqiu.user.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {

	private int id;
	
	private String phone;
	
	private int code;
	
	private Date createTime;
	
}
