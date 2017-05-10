package com.eamon.zhuiqiu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.user.entity.User;


@Repository
public interface UserDao {

	int insertNewUser(User user);

	User getUserByPhone(String phone);

	User getUserById(int id);

	int updateToken(User user);

	int updatePassword(User user);

	int updateNickname(User user);

	int updateHead(User user);

	int updateRole(User user);
	
	List<User> selectAcitvityUser(@Param("activityId") int activityId);

	List<User> selectLessonUser(@Param("lessonId") int lessonId);
	
	List<User> selectLessonTeacher(@Param("lessonId") int lessonId);
	
	List<User> selectLessonAssistant(@Param("lessonId") int lessonId);
}
