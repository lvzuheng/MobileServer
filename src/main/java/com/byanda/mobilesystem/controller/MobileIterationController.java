package com.byanda.mobilesystem.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.byanda.mobilesystem.bean.IntercomGroup;
import com.byanda.mobilesystem.bean.Iteration;
import com.byanda.mobilesystem.util.ApkUtils;
import com.byanda.mobilesystem.util.Configure;



@Controller
@RequestMapping(value = "/iteration")
public class MobileIterationController {
	private String address = Configure.getConfig().getApkAddress();
	private Iteration iteration;

	@RequestMapping("/iterationInfo")
	@ResponseBody
	public String iterationInfo(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		File newFile  = getUpdateFile();
		if(newFile != null){
			if(iteration == null || newFile.lastModified() != iteration.getModifiedTime()){
				ApkUtils apkUtils = ApkUtils.ApkParse(newFile.getAbsolutePath());
				long lastModify =newFile.lastModified();
				String versionCode =apkUtils.parseAttrbute("versionCode").get(0);
				System.out.println("当前地址为："+request.getLocalAddr()+","+request.getRemoteAddr()+","+request.getRequestURI());
				String url ="http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/iteration/iterationDownload";
				System.out.println("当前地址为："+url);
				this.iteration = new Iteration(lastModify,newFile.getName(),versionCode,url,true);
			}
		}
		else {
			iteration =  new Iteration(0,null,null,null,false);
		}

		return JSON.toJSONString(iteration);
	}
	@RequestMapping(value = "/iterationDownload",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void download(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("来访者："+request.getRemoteAddr());
		File file = new File(getUpdateFile().getAbsolutePath());
		String filenames = file.getName();
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filenames.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			os.write(buffer);// 输出文件
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File getUpdateFile(){
		Long modifyTime = (long) 0;
		File  newFile = null;
		System.out.println("迭代apk存放地址为：");
		System.out.println("迭代apk存放地址为："+address);
		File file = new File(address);
		if(file.exists()){
			File[] files = file.listFiles();
			for (File child : files) {
				if (!child.isDirectory()) {
					if(child.getName().substring(child.getName().lastIndexOf("."), child.getName().length()).equals(".apk")){
						if(child.lastModified()>modifyTime){
							newFile = child;
						}
					}
				}
			}
		}else {
			file.mkdir();
		}
		return newFile;
	}
}
