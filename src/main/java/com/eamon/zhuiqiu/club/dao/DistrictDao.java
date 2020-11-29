package com.eamon.zhuiqiu.club.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.club.entity.District;




@Repository
public interface DistrictDao {


	int insertNewDistrict(District district);

	District getDistrictById(int id);

	List<District> getDistricts();
	
}
