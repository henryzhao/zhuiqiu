package com.eamon.zhuiqiu.club.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
	
	private int id;
	
	private String name;
	
	private int districtId;
	
	private String des;
	
	private String image;
	
	private String more;
	
	private String badge;
	
	private Date createTime;
	
}
