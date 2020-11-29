package com.eamon.zhuiqiu.news.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Live {

	private int id;
	
	private int themeId;
	
	private String headPic;
	
	private String title;
	
	private String subTitle;
	
	private String keywords;
	
	private String des;
	
	private int clickNum;
	
	private boolean isFinished;
	
	private Date createTime;
	
	private String createBy;
	
	private Date updateTime;
	
}
