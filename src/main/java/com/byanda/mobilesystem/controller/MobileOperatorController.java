package com.byanda.mobilesystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.byanda.mobilesystem.bean.Operator;
import com.byanda.mobilesystem.bean.Organization;
import com.byanda.mobilesystem.bean.json.OperatorList;
import com.byanda.mobilesystem.service.SqlDataManager;
import com.byanda.mobilesystem.util.ParseRequestUtil;

@Controller
@RequestMapping(value = "/Operator")
public class MobileOperatorController {
	@Autowired
	private SqlDataManager sqlDataManager;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchOperatorList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchOperator(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		StringBuilder stringBuilder = new StringBuilder();
		String sql = null;

		if(jsonObject.get("value")==null || jsonObject.get("value").equals("")){

			List<Long> list = new ArrayList<>();

			List<Long> iList = new ArrayList<>();

			iList.add(new Long(sqlDataManager.search("SELECT organizationId FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'").get(0).toString()));

			list.addAll(iList);
			for(int i = 0;i<list.size();i++){
				list = sqlDataManager.searchList("SELECT id FROM Organization WHERE parentId in(:list)").setParameterList("list", list).list();
				iList.addAll(list);
			}

			for(int i=0;i<iList.size();i++){
				stringBuilder.append("'").append(iList.get(i)).append("'").append(",");
				
				
			}
			String str =  stringBuilder.substring(0, stringBuilder.length()-1);
			sql = "Select  u.id,u.name,u.roleId,o.name FROM Operator u,Organization o  WHERE o.id in ("+str+") AND u.organizationId = o.id ORDER BY o.id";
		}else{
//			sql = "SELECT u.id,u.name,u.roleId,o.name FROM Operator u,Organization o WHERE u.id LIKE '%"+jsonObject.getString("value")+"%'OR u.name LIKE '%"+jsonObject.getString("value")+"%' OR o.name LIKE '%"+jsonObject.getString("value")+"%'";
			sql = "SELECT u.id,u.name,u.roleId,o.name FROM Operator u,Organization o  WHERE ( u.name LIKE '%"+jsonObject.getString("value")+"%'  OR u.organizationId in (SELECT id FROM Organization  WHERE  name LIKE '%"+jsonObject.getString("value")+"%')) AND o.id = u.organizationId";

		}
		List<Object> list = sqlDataManager.searchList(sql)
				.setFirstResult(Integer.valueOf(jsonObject.getString("start")))
				.setMaxResults(Integer.valueOf(jsonObject.getString("end"))-Integer.valueOf(jsonObject.getString("start")))
				.list();
		List<OperatorList> tList = new ArrayList<>();
		OperatorList operatorList;
		//			List<Object> list = sqlDataManager.search("Select  u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u,Organization o WHERE u.orgId = o.id AND row between "+jsonObject.getString("start")+" AND "+jsonObject.getString("end"));
		if(list!=null){
			for(int i =0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				operatorList = new OperatorList((Long)obj[0],(String)obj[1],(Integer)obj[2] == 0 ?"系统管理员":((Integer)obj[2] == 1?"超级管理员":"普通管理员"),(String)obj[3]);
				tList.add(operatorList);
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

	@RequestMapping(value = "/insertOperator",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String insertOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));

		Operator operator  = new Operator();
		operator.setName(jsonObject.getString("name"));
		operator.setRoleId(Integer.valueOf(jsonObject.getString("role")));
		operator.setOrganizationId(new Long((long) sqlDataManager.search("SELECT id FROM Organization WHERE name ='"+jsonObject.getString("org")+"'").get(0)).intValue() );
		try {
						sqlDataManager.save(operator);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	
	@RequestMapping(value = "/updateOperator",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateOperator(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		try {
			Operator operator =  (Operator) sqlDataManager.search(" FROM Operator WHERE name ='"+jsonObject.getString("operator")+"'").get(0);
			Operator dOperator = (Operator) sqlDataManager.search(" FROM Operator WHERE id ='"+jsonObject.getString("id")+"'").get(0);
			if (operator.getRoleId() >= dOperator.getRoleId()) 
				return "role low";
			dOperator.setName(jsonObject.getString("name"));
			dOperator.setOrganizationId(Integer.valueOf(sqlDataManager.search("SELECT id  FROM Organization WHERE name ='"+jsonObject.getString("org")+"'").get(0).toString()));
			dOperator.setRoleId(Integer.valueOf(jsonObject.getString("role")));
			sqlDataManager.save(dOperator);
			//			sqlDataManager.save(operator);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	

	@RequestMapping(value = "/deleteOperator",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteOperator(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		try {
		Operator operator =  (Operator) sqlDataManager.search(" FROM Organization WHERE name ='"+jsonObject.getString("operator")+"'");
		Operator dOperator = (Operator) sqlDataManager.search(" FROM Organization WHERE name ='"+jsonObject.getString("deleteId")+"'");
		if (operator.getRoleId() >= dOperator.getRoleId()) 
			return "role low";
			sqlDataManager.remove(dOperator);
			//			sqlDataManager.save(operator);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
}
