package com.eamon.zhuiqiu.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eamon.zhuiqiu.file.service.FileService;
import com.eamon.zhuiqiu.user.entity.User;
import com.eamon.zhuiqiu.user.service.UserService;
import com.eamon.zhuiqiu.util.file.FileFormat;
import com.eamon.zhuiqiu.util.file.FileUpload;
import com.eamon.zhuiqiu.util.state.Status;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;

@Controller
@RequestMapping(path = "file")
public class FileController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	/**
	 * 上传新闻图片
	 * @param userId
	 * @param token
	 * @param files
	 * @return
	 */
	@RequestMapping(path = "image/news/{userId}/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadNews(@PathVariable int userId, @PathVariable String token,
			@RequestParam CommonsMultipartFile[] files) {
		try {
			checkPermission(userId, token);
			Status s = checkImageFiles(files);
			if (s != null)
				return s;

			return new Status(true, StatusCode.SUCCESS, fileService.uploadImageFilesByType(files, "news"), "上传成功");
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}

	/**
	 * 上传课程图片
	 * @param userId
	 * @param token
	 * @param files
	 * @return
	 */
	@RequestMapping(path = "image/lesson/{userId}/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadLesson(@PathVariable int userId, @PathVariable String token,
			@RequestParam CommonsMultipartFile[] files) {
		try {
			checkPermission(userId, token);
			Status s = checkImageFiles(files);
			if (s != null)
				return s;

			return new Status(true, StatusCode.SUCCESS, fileService.uploadImageFilesByType(files, "lesson"), "上传成功");
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	/**
	 * 上传头像
	 * @param userId
	 * @param token
	 * @param file
	 * @return
	 */
	@RequestMapping(path = "image/head/{userId}/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadHead(@PathVariable int userId, @PathVariable String token,
			@RequestParam CommonsMultipartFile file) {
		try {
			checkPermission(userId, token);
			Status s = checkImageFile(file);
			if (s != null)
				return s;

			return new Status(true, StatusCode.SUCCESS, fileService.uploadImageFile(file, "head"), "上传成功");
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	/**
	 * 上传直播图片
	 * @param userId
	 * @param token
	 * @param file
	 * @return
	 */
	@RequestMapping(path = "image/news/live/{userId}/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadLive(@PathVariable int userId, @PathVariable String token,
			@RequestParam CommonsMultipartFile[] files) {
		try {
			checkPermission(userId, token);
			Status s = checkImageFiles(files);
			if (s != null)
				return s;

			return new Status(true, StatusCode.SUCCESS, fileService.uploadImageFilesByType(files, "live"), "上传成功");
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	
	/**
	 * 上传Flash
	 * @param userId
	 * @param token
	 * @param file
	 * @return
	 */
	@RequestMapping(path = "flash/ad/{userId}/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadFlash(@PathVariable int userId, @PathVariable String token,
			@RequestParam CommonsMultipartFile file) {
		try {
			checkPermission(userId, token);
			Status s = checkFlashFile(file);
			if (s != null)
				return s;

			return new Status(true, StatusCode.SUCCESS, fileService.uploadFlashFile(file, "ad"), "上传成功");
		} catch (Exception e) {
			return StatusException.procExcp(e);
		}
	}
	

	private Status checkFlashFile(CommonsMultipartFile file) throws IOException {
		// 空文件判断
		if (file.isEmpty()) {
			return new Status(false, StatusCode.ERROR_DATA, 0, "文件不可为空");
		}
		// 不支持图片格式判断
		if (!FileFormat.isFlash(file.getInputStream())) {
			return new Status(false, StatusCode.UNSUPPORT_FILE_FORMAT, 0, "不支持的FLASH格式");
		}
		// 文件大小判断
		if (file.getSize() > FileUpload.FILE_MAX_SIZE) {
			return new Status(false, StatusCode.FILE_TOO_LARGE, 0, "FLASH大小超过了上传限制");
		}
		return null;
	}

	/**
	 * 检测图片组
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private Status checkImageFiles(CommonsMultipartFile[] files) throws IOException {
		for (CommonsMultipartFile file : files) {
			// 空文件判断
			if (file.isEmpty()) {
				return new Status(false, StatusCode.ERROR_DATA, 0, "文件不可为空");
			}
			// 不支持图片格式判断
			if (!FileFormat.isImage(file.getInputStream())) {
				return new Status(false, StatusCode.UNSUPPORT_IMAGE_FORMAT, 0, "不支持的图片格式");
			}
			// 文件大小判断
			if (file.getSize() > FileUpload.FILE_MAX_SIZE) {
				return new Status(false, StatusCode.FILE_TOO_LARGE, 0, "图片大小超过了上传限制");
			}
		}
		return null;
	}

	
	
	
	/**
	 * 检测单个图片
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private Status checkImageFile(CommonsMultipartFile file) throws IOException {
		// 空文件判断
		if (file.isEmpty()) {
			return new Status(false, StatusCode.ERROR_DATA, 0, "文件不可为空");
		}
		// 不支持图片格式判断
		if (!FileFormat.isImage(file.getInputStream())) {
			return new Status(false, StatusCode.UNSUPPORT_IMAGE_FORMAT, 0, "不支持的图片格式");
		}
		// 文件大小判断
		if (file.getSize() > FileUpload.FILE_MAX_SIZE) {
			return new Status(false, StatusCode.FILE_TOO_LARGE, 0, "图片大小超过了上传限制");
		}
		return null;
	}

	
	/**
	 * 检测权限
	 * @param userId
	 * @param token
	 * @return
	 * @throws StatusException
	 */
	private User checkPermission(int userId, String token) throws StatusException {
		User user = userService.getUserById(userId);
		userService.checkUserNull(user);
		if (!token.equals(user.getToken()))
			throw new StatusException(StatusCode.PERMISSION_LOW);
		return user;
	}

}
