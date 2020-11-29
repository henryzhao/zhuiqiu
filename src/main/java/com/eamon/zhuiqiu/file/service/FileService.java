package com.eamon.zhuiqiu.file.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eamon.zhuiqiu.util.file.FileUpload;





@Service
public class FileService {
	
	
	public String uploadImageFile(CommonsMultipartFile file,String placeholder) throws IOException{
		String loc = FileUpload.save(file, FileUpload.Path.IMAGE_FILE, placeholder+"");
		return loc;
	}

	public Object uploadImageFiles(CommonsMultipartFile[] files,int userId) throws IOException {
		// TODO Auto-generated method stub
		List<String> imageList = new ArrayList<>();
		
		for(CommonsMultipartFile file:files){
			imageList.add(uploadImageFile(file,userId+""));
		}
		return imageList;
	}

	public Object uploadImageFilesByType(CommonsMultipartFile[] files,String type) throws IOException {
		List<String> imageList = new ArrayList<>();
		
		for(CommonsMultipartFile file:files){
			imageList.add(uploadImageFile(file,type));
		}
		return imageList;
	}

	public Object uploadFlashFile(CommonsMultipartFile file, String placeholder) throws IOException {
		String loc = FileUpload.save(file, FileUpload.Path.FLASH_FILE, placeholder+"");
		return loc;
	}
	
	

}
