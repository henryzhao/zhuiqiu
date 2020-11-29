package com.eamon.zhuiqiu.club.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.club.service.DistrictService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;



@Controller
@RequestMapping("district")
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;

	@RequestMapping(path = "",method = RequestMethod.GET)
	@ResponseBody
	public Status get(){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					districtService.getDistrictList(),
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
			@RequestParam String des
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					districtService.createDistrict(name, des),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	@RequestMapping(path = "{districtId}",method = RequestMethod.GET)
	@ResponseBody
	public Status getDistrictId(
			@PathVariable int districtId
			){
		try {
			return new Status(
					true, 
					StatusCode.SUCCESS, 
					districtService.getDistrictById(districtId),
					0);
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	
}
