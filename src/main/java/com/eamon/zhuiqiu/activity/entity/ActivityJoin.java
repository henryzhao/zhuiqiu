package com.eamon.zhuiqiu.activity.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityJoin {

	private int id;
	
	private int userId;
	
	private int activityId;
	
	private String des;
	
	private Date createTime;
}
