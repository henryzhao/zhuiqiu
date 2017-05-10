package com.eamon.zhuiqiu.activity.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.activity.entity.ActivityJoin;




@Repository
public interface ActivityJoinDao {


	ActivityJoin selectActivityJoin(ActivityJoin activityJoin);

	int addActivityJoin(ActivityJoin activityJoin);
	
	int deleteActivityJoin(ActivityJoin aj);
	
	int selectActivityJoinNum(@Param("activityId") int activityId);
	
}
