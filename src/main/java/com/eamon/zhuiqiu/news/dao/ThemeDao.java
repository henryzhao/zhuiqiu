package com.eamon.zhuiqiu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.news.entity.Theme;




@Repository
public interface ThemeDao {

	void insertNewTheme(Theme theme);
	
	List<Theme> selectAll();
	
	Theme selectById(int id);

	void deleteThemeById(@Param("id")int id);
}
