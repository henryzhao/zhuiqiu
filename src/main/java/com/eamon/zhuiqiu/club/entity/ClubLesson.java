package com.eamon.zhuiqiu.club.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubLesson {
	
	private int id;
	
	private int clubId;
	
	private int lessonId;
	
	private String des;
	
	private Date createTime;
}
