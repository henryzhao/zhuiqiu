package com.eamon.zhuiqiu.club.dao;

import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.club.entity.ClubLesson;


@Repository
public interface ClubLessonDao {
	
	int linkClubLesson(ClubLesson clubLesson);
	
	int unlinkClubLesson(ClubLesson clubLesson);
	
}
