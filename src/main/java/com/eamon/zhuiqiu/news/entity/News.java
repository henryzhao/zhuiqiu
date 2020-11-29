package com.eamon.zhuiqiu.news.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
	private int id;
	
	private int themeId;
	
	private String headPic;
	
	private String title;
	
	private String subTitle;
	
	private String author;
	
	private String source;
	
	private String keywords;
	
	private String navContent;
	
	private String content;
	
	private int clickNum;
	
	private Date updateTime;
	
	private Date createTime;
}
