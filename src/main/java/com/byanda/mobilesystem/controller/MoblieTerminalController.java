package com.byanda.mobilesystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.byanda.mobilesystem.bean.Operator;
import com.byanda.mobilesystem.bean.Organization;
import com.byanda.mobilesystem.bean.User;
import com.byanda.mobilesystem.bean.json.TerminalList;
import com.byanda.mobilesystem.service.SqlDataManager;
import com.byanda.mobilesystem.util.ParseRequestUtil;

@Controller
@RequestMapping(value = "/terminal")
public class MoblieTerminalController {

	@Autowired
	private SqlDataManager sqlDataManager;

	@RequestMapping(value = "/searchUserName",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String SearchUserName(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		List<Object> list = sqlDataManager.search("FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'");
		if(list!=null && list.size()>0){
			return "1";
		}
		return "0";
	}

	@RequestMapping(value = "/login",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			List<Object> list = sqlDataManager.search("FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'");
			if(list!=null){
				if(((Operator)list.get(0)).getPassword().equalsIgnoreCase((String) jsonObject.get("password"))){
					return "1";
				}
			}
			return "0";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/terminalList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchTerminal(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			StringBuilder stringBuilder = new StringBuilder();
			String sql = null;
			if(jsonObject.get("value")==null || jsonObject.get("value").equals("")){
				for(int i=0;i<jsonObject.getJSONArray("orgid").size();i++){
					stringBuilder.append("'").append(jsonObject.getJSONArray("orgid").getString(i)).append("'").append(",");
				}
				String str =  stringBuilder.substring(0, stringBuilder.length()-1);
				sql = "Select  u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u,Organization o  WHERE o.id in ("+str+") AND u.orgId = o.id ORDER BY o.id";
			}else{
//				sql = "SELECT u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u,Organization o WHERE u.id LIKE '%"+jsonObject.getString("value")+"%'OR u.terminalId LIKE '%"+jsonObject.getString("value")+"%'OR u.userName LIKE '%"+jsonObject.getString("value")+"%' OR o.name LIKE '%"+jsonObject.getString("value")+"%'";
				sql = "SELECT u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u, Organization o  WHERE ( u.terminalId LIKE '%"+jsonObject.getString("value")+"%' OR u.userName LIKE '%"+jsonObject.getString("value")+"%'  OR u.orgId in (SELECT id FROM Organization  WHERE  name LIKE '%"+jsonObject.getString("value")+"%')) AND o.id = u.orgId";

//SELECT u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM user u , ORGANIZATION o  WHERE u.id in (
//SELECT u.id FROM user u  WHERE u.id LIKE '%执法仪%' OR u.terminalId LIKE '%执法仪%' OR u.userName LIKE '%执法仪%'  OR u.orgId = (SELECT id FROM ORGANIZATION  WHERE  name //LIKE '%执法仪%')) AND o.id = u.orgId;
			}
			List<Object> list = sqlDataManager.searchList(sql)
								.setFirstResult(Integer.valueOf(jsonObject.getString("start")))
								.setMaxResults(Integer.valueOf(jsonObject.getString("end"))-Integer.valueOf(jsonObject.getString("start")))
								.list();
			List<TerminalList> tList = new ArrayList<>();
			TerminalList terminalList;
			//			List<Object> list = sqlDataManager.search("Select  u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u,Organization o WHERE u.orgId = o.id AND row between "+jsonObject.getString("start")+" AND "+jsonObject.getString("end"));
			if(list!=null){
				for(int i =0;i<list.size();i++){
					 Object[] obj = (Object[]) list.get(i);
					terminalList = new TerminalList((Long)obj[0],(String)obj[1],(String)obj[2],(String)obj[3],(String)obj[4]);
					tList.add(terminalList);
				}
				String jsono = JSONObject.toJSON(tList).toString();
				return jsono;
			}
			return null;
	}
	
	@RequestMapping(value = "/searchOrg",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			List<Long> list = new ArrayList<>();
			
			List<Organization> oList = new ArrayList<>();
			
			List<Long> iList = new ArrayList<>();
			iList.add(new Long(sqlDataManager.search("SELECT organizationId FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'").get(0).toString()));
			
			list.addAll(iList);
			
			for(int i = 0;i<list.size();i++){
				list = sqlDataManager.searchList("SELECT id FROM Organization WHERE parentId in(:list)").setParameterList("list", list).list();
				iList.addAll(list);
			}
			
			oList.addAll( sqlDataManager.searchList("FROM Organization WHERE id in(:iList)").setParameterList("iList", iList).list());
			return jsonObject.toJSONString(oList);
	}
	
	@RequestMapping(value = "/editTermianl",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String editTermianl(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			String sql = "UPDATE User SET orgId = (SELECT id FROM Organization WHERE name = '"+jsonObject.getString("org")+"'),userName = '"+jsonObject.getString("userName")+"' WHERE terminalId = '"+jsonObject.getString("terminalId")+"'";
			if(sqlDataManager.update(sql)){
				return "1";
			}
			return "0";
	}
	
	@RequestMapping(value = "/deleteTermianl",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteTermianl(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			String sql = "DELETE FROM User WHERE terminalId ='"+jsonObject.getString("terminalId")+"'";
			if(sqlDataManager.update(sql)){
				return "1";
			}
			return "0";
	}
	
	@RequestMapping(value = "/insertTermianl",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String insertTermianl(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
			JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
			try {
				User user = new User();
				user.setTerminalId(jsonObject.getString("terminalId"));
				user.setUserName(jsonObject.getString("userName"));
				user.setTerminalNum(jsonObject.getString("terminalId").substring(0, jsonObject.getString("terminalId").length()-1));
				user.setOrgId(Integer.valueOf(sqlDataManager.search("SELECT id FROM Organization WHERE name = '"+jsonObject.getString("org")+"'").get(0)+""));
				
//			String sql = "INSERT INTO User (terminalId,userName,orgId)  values ('"+jsonObject.getString("terminalId")+"','"+jsonObject.getString("userName")+"',(SELECT id FROM Organization WHERE name = '"+jsonObject.getString("org")+"'))";
				if(sqlDataManager.save(user)){
					return "1";
				}
			} catch (Exception e) {
				// TODO: handle exception
				return "0";
			}
			return "0";
	}
	
//	@RequestMapping(value = "/insertAllTermianl",produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String insertAllTermianl(HttpServletRequest request,HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
//		User user = new User();
////		user.setTerminalId(jsonObject.getString("terminalId"));
////		user.setUserName(jsonObject.getString("userName"));
////		user.setOrgId(Integer.valueOf(sqlDataManager.search("SELECT id FROM Organization WHERE name = '"+jsonObject.getString("org")+"'").get(0)+""));
//		List<Integer> ids = new ArrayList<>();
//		
//		for(int i=0;i<10;i++){
//			ids.add(e)
//		}
//		
//		sqlDataManager.search(sql)
//		
//		
////			String sql = "INSERT INTO User (terminalId,userName,orgId)  values ('"+jsonObject.getString("terminalId")+"','"+jsonObject.getString("userName")+"',(SELECT id FROM Organization WHERE name = '"+jsonObject.getString("org")+"'))";
//		if(sqlDataManager.save(user)){
//			return "1";
//		}
//		return "0";
//	}
	
	@RequestMapping(value = "/searchList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchList(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		String sql = "SELECT u.terminalId,u.userName,u.terminalNum,o.name FROM User u,Organization o WHERE u.id LIKE '%"+jsonObject.getString("value")+"%' OR o.name LIKE '%"+jsonObject.getString("value")+"%'";
		List<Object>list = sqlDataManager.search(sql);
		TerminalList terminalList = null;
		List<TerminalList> tList = new ArrayList<>();
		if(list!=null){
			for(int i =0;i<list.size();i++){
				 Object[] obj = (Object[]) list.get(i);
				terminalList = new TerminalList((Long)obj[0],(String)obj[1],(String)obj[2],(String)obj[3],(String)obj[4]);
				tList.add(terminalList);
			}
			String jsono = JSONObject.toJSON(tList).toString();
			return jsono;
		}
		return null;
	}
	
}
