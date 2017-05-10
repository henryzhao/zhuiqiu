package com.eamon.zhuiqiu.news.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.news.dao.ThemeDao;
import com.eamon.zhuiqiu.news.entity.Theme;



@Service
public class ThemeService {
	
	@Autowired
	private ThemeDao themeDao;
	
	
	public List<Map<String, Object>> getThemes(){
		List<Map<String, Object>> resultList = new ArrayList<>();
		themeDao.selectAll().stream().forEach((e)->{
			resultList.add(genThemeMap(e));
		});
		
		return resultList;
	}
	
	public int addTheme(String cName, String eName, String keywords, String des,boolean isHide){
		Theme theme = new Theme();
		theme.setCName(cName);
		theme.setEName(eName);
		theme.setKeywords(keywords);
		theme.setDes(des);
		theme.setHide(isHide);
		themeDao.insertNewTheme(theme);
		return theme.getId();
	}
	
	private List<String> getKeyList(String keywords){
		List<String> keyList = new ArrayList<>();
		String [] list = keywords.split(",");
		for(String s:list){
			keyList.add(s);
		}
		return keyList;
	}
	
	public Map<String, Object> getTheme(int id){
		Theme e = themeDao.selectById(id);
		return genThemeMap(e);
	}
	
	
	public Map<String, Object> genThemeMap(Theme e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId()+"");
		item.put("cName",e.getCName());
		item.put("eName",e.getEName());
		item.put("keywords",getKeyList(e.getKeywords()));
		item.put("des",e.getDes());
		item.put("isHide", e.isHide());
		return item;
	}
	
	
	public boolean deleteThemeById(int id){
		try{
			themeDao.deleteThemeById(id);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
