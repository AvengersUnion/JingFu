package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.multipart.MultipartFile;

import com.app.common.BaseResult;


/**
 * 这是一个上传文件的工具类
 * @author 李洋
 *
 */
public class UpdateFile {
	
	/**
	 * 上传excel文件
	 * @param request
	 * @param file
	 * @return
	 */
	public static BaseResult upload(HttpServletRequest request,MultipartFile file) {
		try {
			//1.得到文件名
			String fileName = file.getOriginalFilename();
			//2.得到文件的后缀名
	        String fileSuffixName = fileName.substring(fileName.lastIndexOf("."));
	        //3.判断上传的是否是excel
	        if (CheckFileUtils.checkPicture(fileSuffixName)) {
	        	//4.文件上传后的格式为uuid+文件的后缀名
				String uuid = UUID.randomUUID().toString();
	        	//5.生成上传后的名字
	        	String lastFileName=uuid+fileSuffixName;
	        	//6.获得上传文件新路径 
	        	String realPath = request.getSession().getServletContext().getRealPath("/static/images/zixun/"+lastFileName); 
	        	//7.上传文件
	            file.transferTo(new File(realPath));
	            //fileUri="/pics/"+fileLastName;
	            return BaseResult.ok(realPath);
			}
		} catch (IOException e) {
			//文件上传失败
			e.printStackTrace();
			return BaseResult.build(400, "文件上传失败");
		}
		//文件上传失败
		return BaseResult.build(400,"文件上传失败");
	}
	
}
