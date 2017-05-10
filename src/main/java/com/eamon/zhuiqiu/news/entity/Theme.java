package com.eamon.zhuiqiu.news.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theme {
	
	private int id;
	
	private String cName;
	
	private String eName;
	
	private String keywords;
	
	private String des;

	private boolean isHide;
}
