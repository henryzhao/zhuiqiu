package com.eamon.zhuiqiu.activity.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	private int id;
	
	private int creatorId;
	
	private String location;
	
	private String tags;
	
	private int peopleNum;
	
	private String des;
	
	private String contact;
	
	private Date timeStart;
	
	private Date timeEnd;
	
	private Date joinStart;
	
	private Date joinEnd;
	
	private Date createTime;
	
	private Date updateTime;
	
}
