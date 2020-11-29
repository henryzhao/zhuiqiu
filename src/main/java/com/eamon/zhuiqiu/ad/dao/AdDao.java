package com.eamon.zhuiqiu.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.ad.entity.Ad;

@Repository
public interface AdDao {

	int insertNewAd(Ad ad);
	
	List<Ad> selectAllAd();
	
	Ad selectById(int id);
	
	int updateById(Ad ad);
	
	int deleteById(int id);

	int pushToField(@Param("adId")int adId,@Param("fieldId")int fieldId);
	
	Ad getAdByField(@Param("fieldId")int fieldId);
	
	List<Ad> getAdInAllField();
}
