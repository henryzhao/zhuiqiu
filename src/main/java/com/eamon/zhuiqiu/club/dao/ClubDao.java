package com.eamon.zhuiqiu.club.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.eamon.zhuiqiu.club.entity.Club;

@Repository
public interface ClubDao {

	int insertNewClub(Club club);

	Club getClubById(int clubId);

	List<Club> getClubs(
			@Param("start")int start,
			@Param("line")int line);
	
	
	List<Club> getClubsByDistrictId(int districtId);
	
	
	
}
