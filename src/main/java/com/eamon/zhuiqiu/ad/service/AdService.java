package com.eamon.zhuiqiu.ad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.ad.dao.AdDao;
import com.eamon.zhuiqiu.ad.entity.Ad;

@Service
public class AdService {

	@Autowired
	private AdDao adDao;
	
	
	public int addNewAd(
			String image,
			String des,
			String type
			){
		Ad ad = new Ad();
		ad.setImage(image);
		ad.setDes(des);
		ad.setType(type);
		return adDao.insertNewAd(ad);
	}
	
	
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> items = new ArrayList<>();
		adDao.selectAllAd().stream().forEach((e)->{
			items.add(genAdMap(e));
		});
		return items;
	}
	

	public Map<String, Object> getAdById(int adId) {
		Ad e =adDao.selectById(adId);
		return genAdMap(e);
	}


	public int update(int adId, String image, String des, String type) {
		Ad e = new Ad();
		e.setId(adId);
		e.setImage(image);
		e.setDes(des);
		e.setType(type);
		return adDao.updateById(e);
	}


	public int delete(int adId) {
		return adDao.deleteById(adId);
	}
	
	
	
	public int pushToField(int adId, int fieldId) {
		return adDao.pushToField(adId, fieldId);
	}

	public List<Map<String, Object>> selectAllFieldAd() {
		List<Map<String, Object>> items = new ArrayList<>();
		for(int i=1;i<=5;i++){
			Map<String, Object> item = selectByField(i);
			if(item == null)continue;
			items.add(item);
		}
		return items;
	}
	
	public Map<String, Object> selectByField(int fieldId) {
		return genAdFieldMap(adDao.getAdByField(fieldId),fieldId);
	}


	
	private Map<String, Object> genAdFieldMap(Ad e,int field){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("image", e.getImage());
		item.put("des", e.getDes());
		item.put("type", e.getType());
		item.put("createTime", e.getCreateTime().getTime());
		item.put("field", field);
		return item;
	}


	public List<Map<String, Object>> listBanner() {
		List<Map<String, Object>> items = new ArrayList<>();
		adDao.selectAllAd().stream().forEach((e)->{
			if(e.getType().equals("-1")){
				items.add(genBannerMap(e));
			}
		});
		return items;
	}


	public List<Map<String, Object>> listVideo() {
		List<Map<String, Object>> items = new ArrayList<>();
		adDao.selectAllAd().stream().forEach((e)->{
			if(e.getType().equals("-2")){
				items.add(genVideoMap(e));
			}
		});
		return items;
	}
	
	
	
	private Map<String, Object> genAdMap(Ad e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("image", e.getImage());
		item.put("des", e.getDes());
		item.put("type", e.getType());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}
	
	private Map<String, Object> genBannerMap(Ad e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("image", e.getImage());
		item.put("des", e.getDes());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}
	
	private Map<String, Object> genVideoMap(Ad e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("content", e.getImage());
		item.put("des", e.getDes());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}
	
}
