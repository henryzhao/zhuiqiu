package com.eamon.zhuiqiu.lesson.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

	private int id;
	
	private int creatorId;
	
	private String lessonName;
	
	private String location;
	
	private String tags;
	
	private int peopleNum;
	
	private String des;
	
	private String contact;
	
	private double price;
	
	private int week;
	
	private Date timeStart;
	
	private Date timeEnd;
	
	private Date dateStart;
	
	private Date dateEnd;
	
	private Date joinStart;
	
	private Date joinEnd;
	
	private Date createTime;
	
	private Date updateTime;
	
}
