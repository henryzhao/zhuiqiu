package com.eamon.zhuiqiu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.news.entity.LiveItem;

@Repository
public interface LiveItemDao {

	int insertNewLiveItem(LiveItem liveItem);
	
	List<LiveItem> selectAll(@Param("liveId")int liveId,@Param("lastId")int lastId);
	
	int deleteById(int id);
	
}
