package com.byanda.mobilesystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.byanda.mobilesystem.bean.Operator;
import com.byanda.mobilesystem.service.SqlDataManager;
import com.byanda.mobilesystem.util.ParseRequestUtil;

@Controller
@RequestMapping(value = "/login")
public class MobileLoginController {

	@Autowired
	private SqlDataManager sqlDataManager;
	
	@RequestMapping(value = "/loginToManager",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			List<Object> list = sqlDataManager.search("FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'");
			try {
				if(list!=null){
					if(((Operator)list.get(0)).getPassword().equalsIgnoreCase((String) jsonObject.get("password"))){
						return "1";
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				return "0";
			}
			return "0";
	}
	
	@RequestMapping(value = "/searchUserName",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String SearchUserName(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		try {
			List<Object> list = sqlDataManager.search("FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'");
			if(list!=null && list.size()>0){
				return "1";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
		return "0";
	}
}
