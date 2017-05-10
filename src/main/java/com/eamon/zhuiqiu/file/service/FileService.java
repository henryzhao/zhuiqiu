package com.eamon.zhuiqiu.file.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eamon.zhuiqiu.util.file.FileUpload;


@Service
public class FileService {

	/**
	 * 上传项目图片
	 * @param categoryId 分类ID
	 * @param projectId 项目ID 
	 * @param files 图片
	 * @return
	 */
	public List<String> uploadImages(CommonsMultipartFile[] files){
		String dir= ""+System.currentTimeMillis();
		//String[] paths=new String[files.length];
		List<String> paths = new ArrayList<>();
		for(int i=0;i<files.length;i++){
			try {
				paths.add(
						FileUpload.save(files[i], FileUpload.Path.COM_IMAGE, dir)
						);
				//paths[i]=FileUpload.save(files[i], FileUpload.Path.COM_IMAGE, dir);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return paths;
	}

}
