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
@RequestMapping(path = "banner")
public class BannerController {

	@Autowired
	private AdService adService;

	/**
	 * 上传Banner
	 * @param image
	 * @param des
	 * @param type
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public Status banner(
			@RequestParam String image, 
			@RequestParam String des) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.addNewAd(image, des, "-1"), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	/**
	 * 获取Banner列表
	 * @return
	 */
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public Status bannerList() {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.listBanner(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	/**
	 * 删除广告
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{bannerId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Status deleteBanner(
			@PathVariable int bannerId
			) {
		try {
			return new Status(true, StatusCode.SUCCESS, adService.delete(bannerId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	
}
