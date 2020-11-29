package com.eamon.zhuiqiu.ad.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
	
	private int id;
	
	private String image;
	
	private String des;
	
	private String type;
	
	private Date createTime;

}
