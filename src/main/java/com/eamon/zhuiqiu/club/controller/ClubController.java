package com.eamon.zhuiqiu.club.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.club.service.ClubService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;



@Controller
@RequestMapping("club")
public class ClubController {
	
	@Autowired
	private ClubService clubService;

	@RequestMapping(path = "",method = RequestMethod.GET)
	@ResponseBody
	public Status get(
			@RequestParam int page,
			@RequestParam(required=false,defaultValue="10") int rows
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.getClubList(page, rows),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	

	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "",method = RequestMethod.POST)
	@ResponseBody
	public Status create(
			@RequestParam String name, 
			@RequestParam int districtId, 
			@RequestParam String des,
			@RequestParam(required=false,defaultValue="") String image, 
			@RequestParam(required=false,defaultValue="") String badge, 
			@RequestParam(required=false,defaultValue="") String more
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.create(name, districtId, des, image, badge, more),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestMapping(path = "{clubId}",method = RequestMethod.GET)
	@ResponseBody
	public Status getDistrictId(
			@PathVariable int clubId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.getClubById(clubId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "district/{districtId}",method = RequestMethod.POST)
	@ResponseBody
	public Status createByDistrict(
			@PathVariable int districtId, 
			@RequestParam String name, 
			@RequestParam String des,
			@RequestParam(required=false,defaultValue="") String image,
			@RequestParam(required=false,defaultValue="") String badge, 
			@RequestParam(required=false,defaultValue="") String more
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.create(name, districtId, des, image, badge, more),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestMapping(path = "district/{districtId}",method = RequestMethod.GET)
	@ResponseBody
	public Status getByDistrict(
			@PathVariable int districtId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.getClubListByDisctrict(districtId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
	@RequestMapping(path = "{clubId}/lesson",method = RequestMethod.GET)
	@ResponseBody
	public Status getClubLesson(
			@PathVariable int clubId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.getClubLesson(clubId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{clubId}/lesson/{lessonId}/link",method = RequestMethod.POST)
	@ResponseBody
	public Status linkClubLesson(
			@PathVariable int clubId,
			@PathVariable int lessonId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.linkClubLesson(clubId,lessonId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{clubId}/lesson/{lessonId}/unlink",method = RequestMethod.POST)
	@ResponseBody
	public Status unlinkClubLesson(
			@PathVariable int clubId,
			@PathVariable int lessonId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					clubService.unlinkClubLesson(clubId,lessonId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
}
