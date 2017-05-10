package com.eamon.zhuiqiu.lesson.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.lesson.entity.Lesson;




@Repository
public interface LessonDao {

	int insertNewLesson(Lesson lesson);
	
	List<Lesson> selectAll(
			@Param("start") int start, 
			@Param("rows") int rows);
	
	Lesson selectById(int id);

	List<Lesson> selectUserLesson(int userId);
}
