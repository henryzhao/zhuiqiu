package com.eamon.zhuiqiu.lesson.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.lesson.entity.LessonJoin;




@Repository
public interface LessonJoinDao {


	LessonJoin selectLessonJoin(LessonJoin lessonJoin);

	int addLessonJoin(LessonJoin lessonJoin);
	
	int deleteLessonJoin(LessonJoin lessonJoin);
	
	int selectLessonJoinNum(@Param("lessonId") int lessonId);
	
}
