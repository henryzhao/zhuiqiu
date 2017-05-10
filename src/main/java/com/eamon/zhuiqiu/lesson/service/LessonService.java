package com.eamon.zhuiqiu.lesson.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.lesson.dao.LessonDao;
import com.eamon.zhuiqiu.lesson.dao.LessonJoinDao;
import com.eamon.zhuiqiu.lesson.entity.Lesson;
import com.eamon.zhuiqiu.lesson.entity.LessonJoin;
import com.eamon.zhuiqiu.user.service.UserService;

@Service
public class LessonService {
	
	@Autowired
	private LessonDao lessonDao;
	
	
	@Autowired
	private LessonJoinDao lessonJoinDao;
	
	
	@Autowired
	private UserService userService;

	public List<Map<String,Object>> selectAllLesson(int page, int rows) {
		if(page<0)return null;
		int start = (page-1)*rows;
		List<Map<String,Object>> items = new ArrayList<>();
		lessonDao.selectAll(start, rows).stream().forEach((e)->{
			items.add(genLessonMap(e));
		});
		return items;
	}

	private Map<String, Object> genLessonMap(Lesson e) {
		Map<String,Object> item = new HashMap<>();
		item.put("id", e.getId());
		
		
		item.put("creatorId", e.getCreatorId());
		item.put("lessonName", e.getLessonName());
		item.put("location", e.getLocation());
		item.put("tags", e.getTags());
		item.put("peopleNum", e.getPeopleNum());
		item.put("currentNum", lessonJoinDao.selectLessonJoinNum(e.getId()));
		
		item.put("teacher", userService.getLessonTeacher(e.getId()));
		item.put("assistant", userService.getLessonAssistant(e.getId()));
		item.put("student", userService.getLessonUser(e.getId()));
		
		item.put("des", e.getDes());
		item.put("contact", e.getContact());
		item.put("price", e.getPrice());
		
		item.put("timeStart", e.getTimeStart().getTime());
		item.put("timeEnd", e.getTimeEnd().getTime());
		
		item.put("joinStart", e.getJoinStart().getTime());
		item.put("joinEnd", e.getJoinEnd().getTime());

		item.put("dateStart", e.getDateStart().getTime());
		item.put("dateEnd", e.getDateEnd().getTime());
		
		return item;
	}

	public Object createLesson(
			String lessonName,
			int creatorId,
			String location,
			String tags,
			int peopleNum,
			
			String des,
			String contact,
			
			int week,
			double price,
			
			long timeStart,
			long timeEnd,
			
			long joinStart,
			long joinEnd,
			
			long dateStart,
			long dateEnd
			) {
		Lesson lesson = new Lesson();
		lesson.setCreatorId(creatorId);
		lesson.setLessonName(lessonName);
		lesson.setLocation(location);
		lesson.setTags(tags);
		lesson.setPeopleNum(peopleNum);
		lesson.setDes(des);
		lesson.setContact(contact);
		lesson.setWeek(week);
		lesson.setPrice(price);
		lesson.setTimeStart(new Date(timeStart));
		lesson.setTimeEnd(new Date(timeEnd));
		lesson.setDateStart(new Date(dateStart));
		lesson.setDateEnd(new Date(dateEnd));
		lesson.setJoinStart(new Date(joinStart));
		lesson.setJoinEnd(new Date(joinEnd));
		return lessonDao.insertNewLesson(lesson);
	}

	public Map<String,Object> getLessonById(int lessonId) {
		Lesson e = lessonDao.selectById(lessonId);
		return genLessonMap(e);
	}

	public int join(int lessonId, int viewId) {
		if(isJoin(lessonId,viewId))return 0;
		
		LessonJoin lj = new LessonJoin();
		lj.setLessonId(lessonId);
		lj.setUserId(viewId);
		lj.setRole("student");
		lj.setDes("未付款新加入");
		return lessonJoinDao.addLessonJoin(lj);
	}

	public int joinTeacher(int lessonId, int teacherId) {
		LessonJoin lj = new LessonJoin();
		lj.setLessonId(lessonId);
		lj.setUserId(teacherId);
		lessonJoinDao.deleteLessonJoin(lj);
		
		lj.setRole("teacher");
		lj.setDes("任课教师");
		
		
		return lessonJoinDao.addLessonJoin(lj);
	}

	public Object joinAssistant(int lessonId, int assistantId) {
		LessonJoin lj = new LessonJoin();
		lj.setLessonId(lessonId);
		lj.setUserId(assistantId);
		lessonJoinDao.deleteLessonJoin(lj);
		
		lj.setRole("assistant");
		lj.setDes("助教");
		
		
		return lessonJoinDao.addLessonJoin(lj);
	}
	
	public boolean isJoin(int lessonId, int viewId) {
		LessonJoin lj = new LessonJoin();
		lj.setLessonId(lessonId);
		lj.setUserId(viewId);
		lj= lessonJoinDao.selectLessonJoin(lj);
		if(lj!=null){
			return true;
		}else{
			return false;
		}
	}

	public int exit(int viewId, int lessonId) {
		LessonJoin lj = new LessonJoin();
		lj.setLessonId(lessonId);
		lj.setUserId(viewId);
		return lessonJoinDao.deleteLessonJoin(lj);
	}

	public List<Map<String,Object>> selectUsersLesson(int userId) {
		List<Map<String,Object>> items = new ArrayList<>();
		lessonDao.selectUserLesson(userId).forEach((e)->{
			items.add(genLessonMap(e));
		});
		return items;
	}
	

	

}
