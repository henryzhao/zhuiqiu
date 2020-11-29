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
@RequestMapping(path = "ad")
public class AdController {

	@Autowired
	private AdService adService;

	/**
	 * 上传广告
	 * @param image
	 * @param des
	 * @param type
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseBody
	public Status upload(
			@RequestParam String image, 
			@RequestParam String des, 
			@RequestParam String type) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.addNewAd(image, des, type), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	/**
	 * 获取广告列表
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public Status list() {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.list(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	/**
	 * 根据id获取广告
	 * @param adId
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{adId}", method = RequestMethod.GET)
	@ResponseBody
	public Status getById(
			@PathVariable int adId
			) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.getAdById(adId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	

	
	/**
	 * 更新广告
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{adId}/update", method = RequestMethod.POST)
	@ResponseBody
	public Status update(
			@PathVariable int adId,
			@RequestParam String image, 
			@RequestParam String des, 
			@RequestParam String type) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.update(adId,image,des,type), 0);
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
	@RequestMapping(path = "{adId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Status delete(
			@PathVariable int adId
			) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.delete(adId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	
	/**
	 * 将广告发布到某栏位
	 * @return
	 */
	@RequestLimit(RequestLimit.ADMIN_PRIVATE)
	@RequestMapping(path = "{adId}/field/{fieldId}", method = RequestMethod.POST)
	@ResponseBody
	public Status field(
			@PathVariable int adId,
			@PathVariable int fieldId
			) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.pushToField(adId,fieldId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	
	/**
	 * 获取所有栏位广告
	 * @return
	 */
	@RequestMapping(path = "field", method = RequestMethod.GET)
	@ResponseBody
	public Status fieldAll() {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.selectAllFieldAd(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
	
	/**
	 * 获取某栏位广告
	 * @return
	 */
	@RequestMapping(path = "field/{fieldId}", method = RequestMethod.GET)
	@ResponseBody
	public Status fieldById(
			@PathVariable int fieldId
			) {
		try {

			return new Status(true, StatusCode.SUCCESS, adService.selectByField(fieldId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(false, StatusCode.FAILED, 0, 0);
		}
	}
}
