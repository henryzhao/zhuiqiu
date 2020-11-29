package com.eamon.zhuiqiu.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.club.dao.ClubDao;
import com.eamon.zhuiqiu.club.dao.ClubLessonDao;
import com.eamon.zhuiqiu.club.entity.Club;
import com.eamon.zhuiqiu.club.entity.ClubLesson;
import com.eamon.zhuiqiu.lesson.entity.Lesson;
import com.eamon.zhuiqiu.lesson.service.LessonService;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;

@Service
public class ClubService {
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private ClubLessonDao clubLessonDao;
	
	@Autowired
	private LessonService lessonService;
	
	public int create(
			String name,
			int districtId,
			String des,
			String image,
			String badge,
			String more
			) throws StatusException{
		if(districtService.getDistrictById(districtId)==null)
			throw new StatusException(StatusCode.ERROR_DATA);
		Club club = new Club();
		club.setName(name);
		club.setDistrictId(districtId);
		club.setDes(des);
		club.setImage(image);
		club.setBadge(badge);
		club.setMore(more);
		return clubDao.insertNewClub(club);
	}
	
	public List<Map<String, Object>> getClubList(int page,int rows){
		if(page<0)return null;
		int start = (page-1)*rows;
		List<Map<String, Object>> items = new ArrayList<>();
		
		clubDao.getClubs(start, rows).stream().forEach((e)->{
			items.add(genClubMap(e));
		});
		return items;
	}
	
	
	public List<Map<String, Object>> getClubListByDisctrict(int districtId){

		List<Map<String, Object>> items = new ArrayList<>();
		
		clubDao.getClubsByDistrictId(districtId).stream().forEach((e)->{
			items.add(genClubMap(e));
		});
		return items;
	}
	
	public Map<String, Object> getClubById(int id){
		Club club = clubDao.getClubById(id);
		return genClubMap(club);
	}
	
	
	
	private Map<String, Object> genClubMap(Club e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("name", e.getName());
		item.put("district", districtService.getDistrictById(e.getDistrictId()));
		item.put("des", e.getDes());
		item.put("image", e.getImage());
		item.put("badge", e.getBadge());
		item.put("more", e.getMore());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}

	public int linkClubLesson(int clubId, int lessonId) throws StatusException {
		Club club = clubDao.getClubById(clubId);
		if(club==null)throw new StatusException(StatusCode.ERROR_DATA);
		Lesson lesson = lessonService.getLessonById(lessonId);
		if(lesson==null)throw new StatusException(StatusCode.ERROR_DATA);
		
		ClubLesson clubLesson = new ClubLesson();
		clubLesson.setClubId(clubId);
		clubLesson.setLessonId(lessonId);
		clubLesson.setDes(club.getDes()+" "+lesson.getDes());
		
		return clubLessonDao.linkClubLesson(clubLesson);
	}

	public int unlinkClubLesson(int clubId, int lessonId) throws StatusException {
		Club club = clubDao.getClubById(clubId);
		if(club==null)throw new StatusException(StatusCode.ERROR_DATA);
		Lesson lesson = lessonService.getLessonById(lessonId);
		if(lesson==null)throw new StatusException(StatusCode.ERROR_DATA);
		
		ClubLesson clubLesson = new ClubLesson();
		clubLesson.setClubId(clubId);
		clubLesson.setLessonId(lessonId);
		
		return clubLessonDao.unlinkClubLesson(clubLesson);
	}

	public List<Map<String, Object>> getClubLesson(int clubId) throws StatusException {
		Club club = clubDao.getClubById(clubId);
		if(club==null)throw new StatusException(StatusCode.ERROR_DATA);
		return lessonService.selectClubLesson(clubId);
	} 
	

}
