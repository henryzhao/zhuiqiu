package com.eamon.zhuiqiu.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.news.entity.Live;

@Repository
public interface LiveDao {
	
	List<Live> selectAll();
	
	List<Live> selectByThemeId(int themeId);
	
	Live selectById(int id);
	
	int insertNewLive(Live live);
	
	int updateById(Live live);
	
	int deleteById(int id);

}
