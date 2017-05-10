package com.eamon.zhuiqiu.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.activity.entity.Activity;




@Repository
public interface ActivityDao {

	int insertNewActivity(Activity activity);
	
	List<Activity> selectAll(
			@Param("start") int start, 
			@Param("rows") int rows);
	
	Activity selectById(int id);

	List<Activity> selectUserAcitvity(int userId);
}
