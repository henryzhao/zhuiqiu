package com.eamon.zhuiqiu.news.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveItem {

	private int id;
	
	private int liveId;
	
	private String content;
	
	private Date createTime;
	
}
