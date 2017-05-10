package com.eamon.zhuiqiu.lesson.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonJoin {

	private int id;
	
	private int lessonId;
	
	private int userId;
	
	private String des;
	
	private String role;
	
	private Date createTime;
}
