package com.eamon.zhuiqiu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.news.entity.News;




@Repository
public interface NewsDao {

	int insertNews(News news);
	
	News selectById(@Param("id") int id);
	
	List<News> selectAllByThemeId(@Param("themeId") int themeId,@Param("start") int start,@Param("line") int line);
	
	void addClickNum(@Param("id") int id);

	int deleteNews(@Param("id") int id);
	
	int updateNews(News news);
}
