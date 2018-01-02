package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.common.kindEditorResult;


/**
 * 文件上传的Controller
 * @author 567
 *
 */
@Controller
@RequestMapping("kindEditor")
//@RequestMapping("advisory")
public class UploadController {

	//@Value("${SAVE_FRONT_PATH}")
	private String SAVE_FRONT_PATH;

	//@Value("${HTTP_FRONT_PATH}") 
	private String HTTP_FRONT_PATH;

	//@Value("${LIUNX_WINDOW_SIGN}")
	private String LIUNX_WINDOW_SIGN;

	/**
	 * 上传入口
	 * 
	 * @param request
	 * @param response
	 * @param imgFile
	 */
	@RequestMapping("upFile")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("imgFile") MultipartFile[] imgFile) {

		// 循环接收到的文件
		for (MultipartFile file : imgFile) {
			// 通过旧名字变成新名字
			String oldFileName = file.getOriginalFilename();
			String newFileName = makeFileName(oldFileName);

			// 获取存储目录的后半部分
			String save_back_path = makeDir(newFileName);

			File newFile = new File(SAVE_FRONT_PATH + save_back_path, newFileName);

			// 查看是否存在，不存在就创建
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			// 写入文件
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				//return com.app.common.kindEditorResult.error("参数错误");
				return kindEditorResult.error("参数错误");
			} catch (IOException e) {
				e.printStackTrace();
				return kindEditorResult.error("写入失败");
			}
			
			return kindEditorResult.ok(HTTP_FRONT_PATH + save_back_path + newFileName);
		}

		return kindEditorResult.error("请选择上传文件");

	}

	/**
	 * 上传新的文件名
	 * @param fileName 原来的文件名
	 * @return 新的文件名
	 */
	private String makeFileName(String fileName) {
		return UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 根据文件名生成存储目录
	 * 
	 * @param fileName
	 *            文件名
	 * @param savePath
	 *            存储的部分目录
	 * @return 真实存储目录
	 */
	private String makeDir(String fileName) {
		// 根据文件名hashCode生成目录
		int hashcode = fileName.hashCode();
		int dir1 = hashcode & 0xf;
		int dir2 = (hashcode & 0xf0) >> 4;

		String dir = LIUNX_WINDOW_SIGN + dir1 + LIUNX_WINDOW_SIGN + dir2 + LIUNX_WINDOW_SIGN;

		return dir;
	}

}
