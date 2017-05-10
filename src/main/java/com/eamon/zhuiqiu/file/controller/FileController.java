package com.eamon.zhuiqiu.file.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eamon.zhuiqiu.file.service.FileService;
import com.eamon.zhuiqiu.state.Status;
import com.eamon.zhuiqiu.state.StatusCode;
import com.eamon.zhuiqiu.util.file.FileFormat;
import com.eamon.zhuiqiu.util.file.FileUpload;


@Controller
@RequestMapping(path = "file")
public class FileController {
	

	@Autowired 
	private FileService fileService;
	/**
	 * 上传项目图片
	 * @param files 上传的文件
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(path="images",method=RequestMethod.POST)
	@ResponseBody
	public Status uploadProjectImages(@RequestParam CommonsMultipartFile[] files) throws IOException{
		if(files.length==0){
			return new Status(false,StatusCode.FAILED,"文件不可为空",null);
		}
		for(CommonsMultipartFile file:files){
			if(file.isEmpty()){
				return new Status(false,StatusCode.FAILED,"文件不可为空",null);
			}
			if(file.getSize()>FileUpload.FILE_MAX_SIZE){
				return new Status(false,StatusCode.FILE_TOO_LARGE,"图片大小超过了上传限制",null);
			}
			if(!FileFormat.isImage(file.getInputStream())){
				return new Status(false,StatusCode.UNSUPPORT_IMAGE_FORMAT,"不支持的图片格式",null);
			}
		}
		List<String> paths;
		if((paths=fileService.uploadImages(files))!=null && (paths.size()!=0)){
			return new Status(true,StatusCode.SUCCESS,paths,null);
		}
		return new Status(false,StatusCode.FILEUPLOAD_ERROR,"文件上传失败",null);
	}
	

}
