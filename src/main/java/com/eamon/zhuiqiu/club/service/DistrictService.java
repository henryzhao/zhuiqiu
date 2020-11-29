package com.eamon.zhuiqiu.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.club.dao.DistrictDao;
import com.eamon.zhuiqiu.club.entity.District;

@Service
public class DistrictService {

	@Autowired
	private DistrictDao districtDao;
	
	public int createDistrict(
			String name,
			String des
			){
		District district = new District();
		district.setName(name);
		district.setDes(des);
		return districtDao.insertNewDistrict(district);
	}
	
	public Map<String, Object> getDistrictById(int districtId){
		District e = districtDao.getDistrictById(districtId);
		return genDistrictMap(e);
	}
	
	
	public List<Map<String, Object>> getDistrictList(){
		List<Map<String, Object>> items = new ArrayList<>();
		districtDao.getDistricts().stream().forEach((e)->{
			items.add(genDistrictMap(e));
		});
		return items;
	}
	
	
	private Map<String, Object> genDistrictMap(District e){
		if(e==null)return null;
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("name", e.getName());
		item.put("des", e.getDes());
		item.put("createTime", e.getCreateTime().getTime());
		return item;
	}
	
	
}
