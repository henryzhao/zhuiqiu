package com.eamon.zhuiqiu.user.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int id;
	
	private String phone;
	
	private String nickname;
	
	private String head;
	
	private String password;
	
	private String salt;
	
	private String token;
	
	private String role;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Date tokenTime;
	
}
