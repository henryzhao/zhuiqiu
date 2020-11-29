package com.eamon.zhuiqiu.club.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
	
	private int id;
	
	private String name;
	
	private String des;
	
	private Date createTime;
	
}
