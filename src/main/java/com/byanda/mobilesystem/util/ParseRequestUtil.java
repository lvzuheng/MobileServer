package com.byanda.mobilesystem.util;

import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ParseRequestUtil {

	public static JSONObject parseToJSONObject(InputStream in){
		JSONObject jsonObject = null ;
		try {

			StringBuffer out  =  new StringBuffer(); 
			byte[]   b  = new   byte[4096];
			for(int n; (n  = in.read(b))!= -1;)   { 
				out.append(new   String(b,   0,   n)); 
			} 
			jsonObject = JSON.parseObject(out.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonObject;
	}
	
	public static JSONObject parseToJSONObject(String json){
		JSONObject jsonObject = null ;
		jsonObject = JSON.parseObject(json);
		return jsonObject;
	}
}
