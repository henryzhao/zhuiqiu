package com.eamon.zhuiqiu.activity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.activity.dao.ActivityDao;
import com.eamon.zhuiqiu.activity.dao.ActivityJoinDao;
import com.eamon.zhuiqiu.activity.entity.Activity;
import com.eamon.zhuiqiu.activity.entity.ActivityJoin;
import com.eamon.zhuiqiu.user.service.UserService;

@Service
public class ActivityService {

	
//	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	
//	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private ActivityJoinDao activityJoinDao;
	
	@Autowired
	private UserService userService;
	
	public int create(
			int creatorId, 
			String location, 
			String tags, 
			int peopleNum, 
			String des,
			String contact,
			long timeStart,
			long timeEnd,
			long joinStart, 
			long joinEnd) {
		
		Activity activity = new Activity();
		
		activity.setCreatorId(creatorId);
		activity.setLocation(location);
		activity.setTags(tags);
		activity.setPeopleNum(peopleNum);
		activity.setDes(des);
		activity.setContact(contact);
//		try {
			activity.setTimeStart(new Date(timeStart));
			activity.setTimeEnd(new Date(timeEnd));
			activity.setJoinStart(new Date(joinStart));
			activity.setJoinEnd(new Date(joinEnd));
			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		activityDao.insertNewActivity(activity);
		ActivityJoin aj = new ActivityJoin();
		aj.setActivityId(activity.getId());
		aj.setUserId(creatorId);
		aj.setDes("creator");
		activityJoinDao.addActivityJoin(aj);
		return activity.getId();
	}
	
	public int joinActivity(ActivityJoin activityJoin){
		ActivityJoin aj =activityJoinDao.selectActivityJoin(activityJoin);
		if(aj==null)
			return activityJoinDao.addActivityJoin(activityJoin);
		else{
			return 0;
		}
	}
	
	public int joinActivity(int userId,int activityId){
		if(isJoinActivity(userId,activityId))return 0;
		ActivityJoin aj = new ActivityJoin();
		aj.setActivityId(activityId);
		aj.setUserId(userId);
		aj.setDes("normal");
		return joinActivity(aj);
	}
	
	public boolean isJoinActivity(int userId, int activityId) {
		ActivityJoin aj = new ActivityJoin();
		aj.setActivityId(activityId);
		aj.setUserId(userId);
		aj = activityJoinDao.selectActivityJoin(aj);
		if(aj!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public int exitActivity(int userId, int activityId) {
		ActivityJoin aj = new ActivityJoin();
		aj.setActivityId(activityId);
		aj.setUserId(userId);
		return activityJoinDao.deleteActivityJoin(aj);
	}

	
	
	public Map<String,Object> getActivityById(int id){
		Activity e =activityDao.selectById(id);
		if(e==null)return null;
		return genActivityMap(e);
	}
	
	public List<Map<String,Object>> selectAllActivity(int page, int rows){
		if(page<0)return null;
		int start = (page-1)*rows;
		
		List<Map<String,Object>> items = new ArrayList<>();
		activityDao.selectAll(start,rows).stream().forEach((e)->{
			Map<String,Object> item = genActivityMap(e);
			items.add(item);
		});
		return items;
	}
	
	
	public List<Map<String,Object>> selectUsersAcitvity(int userId){
		List<Map<String,Object>> items = new ArrayList<>();
		activityDao.selectUserAcitvity(userId).forEach((e)->{
			Map<String,Object> item = genActivityMap(e);
			items.add(item);
		});
		return items;
	}
	
	public List<Map<String,Object>> selectAcitvitysUser(int activityId){
		return userService.getActivityUser(activityId);
	}
	
	
	private Map<String,Object> genActivityMap(Activity e){
		Map<String,Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("creatorId", e.getCreatorId());
		item.put("location", e.getLocation());
		item.put("tags", e.getTags());
		item.put("peopleNum", e.getPeopleNum());
		item.put("currentNum", activityJoinDao.selectActivityJoinNum(e.getId()));
		
		item.put("des", e.getDes());
		item.put("contact", e.getContact());
		
		
		item.put("timeStart", e.getTimeStart().getTime());
		item.put("timeEnd", e.getTimeEnd().getTime());
		
		item.put("joinStart", e.getJoinStart().getTime());
		item.put("joinEnd", e.getJoinEnd().getTime());
		return item;
	}





	
	
	
	
}
