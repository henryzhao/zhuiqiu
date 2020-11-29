package com.eamon.zhuiqiu.news.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.news.dao.LiveDao;
import com.eamon.zhuiqiu.news.dao.LiveItemDao;
import com.eamon.zhuiqiu.news.entity.Live;
import com.eamon.zhuiqiu.news.entity.LiveItem;

@Service
public class LiveService {
	
	@Autowired
	private LiveDao liveDao;
	
	@Autowired
	private LiveItemDao liveItemDao;

	public List<Map<String, Object>> getLiveListByTheme(int themeId) {
		List<Map<String, Object>> liveList = new ArrayList<>();
		liveDao.selectByThemeId(themeId).stream().forEach((e)->{
			liveList.add(genLiveMap(e));
		});
		return liveList;
	}

	public Object postLive(int themeId, String headPic, String title, String subTitle, String keywords, String des) {
		Live live = new Live();
		live.setThemeId(themeId);
		live.setHeadPic(headPic);
		live.setTitle(title);
		live.setSubTitle(subTitle);
		live.setKeywords(keywords);
		live.setDes(des);
		return liveDao.insertNewLive(live);
	}

	public Map<String, Object> getLiveById(int liveId) {
		Live live = liveDao.selectById(liveId);
		return genLiveMap(live);
	}
	
	public Object updateLiveById(int liveId, int themeId, String headPic, String title, String subTitle,
			String keywords, String des) {
		Live live = new Live();
		live.setId(liveId);
		live.setThemeId(themeId);
		live.setHeadPic(headPic);
		live.setTitle(title);
		live.setSubTitle(subTitle);
		live.setKeywords(keywords);
		live.setDes(des);
		return liveDao.updateById(live);
	}
	
	public Object deleteLiveById(int liveId) {
		return liveDao.deleteById(liveId);
	}

	public Object getLiveItemFromId(int liveId, int lastId) {
		List<Map<String, Object>> liveItemList = new ArrayList<>();
		liveItemDao.selectAll(liveId, lastId).stream().forEach((e)->{
			liveItemList.add(genLiveItemMap(e));
		});
		return liveItemList;
	}

	public Object postLiveItem(int liveId, String content) {
		LiveItem liveItem = new LiveItem();
		liveItem.setLiveId(liveId);
		liveItem.setContent(content);
		return liveItemDao.insertNewLiveItem(liveItem);
	}

	public int deleteLiveItemById(int itemId) {
		return liveItemDao.deleteById(itemId);
	}

	private Map<String, Object> genLiveMap(Live e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("themeId", e.getThemeId());
		item.put("headPic", e.getHeadPic());
		item.put("title", e.getTitle());
		item.put("subTitle", e.getSubTitle());
		item.put("keywords", e.getKeywords().split(","));
		item.put("des", e.getDes());
		item.put("clickNum", e.getClickNum());
		item.put("isFinished", e.isFinished());
		item.put("updateTime", e.getUpdateTime().getTime());
		return item;
	}

	private Map<String, Object> genLiveItemMap(LiveItem e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("liveId", e.getLiveId());
		item.put("content", e.getContent());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}
	
	
	
}
