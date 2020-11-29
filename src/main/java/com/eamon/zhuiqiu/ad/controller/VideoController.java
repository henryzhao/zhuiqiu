package com.eamon.zhuiqiu.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamon.zhuiqiu.ad.service.AdService;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;

@Controller
@RequestMapping(path = "video")
public class VideoController {

	@Autowired
	private AdService adService;

	
	/**
	 * 上传video
	 * @param image
	 * @param des
	 * @param type
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public Status banner(
			@RequestParam String content, 
			@RequestParam String des) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.addNewAd(content, des, "-2"), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	/**
	 * 获取video列表
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public Status bannerList() {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.listVideo(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	/**
	 * 删除video
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{videoId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Status deleteBanner(
			@PathVariable int videoId
			) {
		try {
			return new Status(true, StatusCode.SUCCESS, adService.delete(videoId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	
}
