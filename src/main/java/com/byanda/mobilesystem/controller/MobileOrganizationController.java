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
import com.byanda.mobilesystem.bean.json.OrganizationList;
import com.byanda.mobilesystem.service.SqlDataManager;
import com.byanda.mobilesystem.util.ParseRequestUtil;

@Controller
@RequestMapping(value = "/Organization")
public class MobileOrganizationController {
	@Autowired
	private SqlDataManager sqlDataManager;

	@RequestMapping(value = "/searchOrgList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));

		String sql = null ;
		Operator op=  (Operator) sqlDataManager.search("  FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'").get(0);
		List<Object> oList = null;
		List<Long> list = new ArrayList<>();

		List<Long> iList = new ArrayList<>();
		if(op.getRoleId() == 0){
			if(jsonObject.get("value")==null || jsonObject.get("value").equals("")){
				sql = "SELECT o1.id,o1.name AS name,o2.name AS parentname  FROM ORGANIZATION o1 left join ORGANIZATION o2 ON o1.parentId = o2.id   ORDER BY o1.parentId*1";
			}else{
				sql = "SELECT o1.id,o1.name AS name,o2.name AS parentname FROM ORGANIZATION o1 left join ORGANIZATION o2 ON o1.parentId = o2.id  AND o1.name LIKE '%"+jsonObject.getString("value")+"%' ORDER BY o1.parentId*1";
			}
			oList = sqlDataManager.searchSqlList(sql)
					.setFirstResult(Integer.valueOf(jsonObject.getString("start")))
					.setMaxResults(Integer.valueOf(jsonObject.getString("end"))-Integer.valueOf(jsonObject.getString("start")))
					.list();
		}else {


			iList.add(new Long(sqlDataManager.search("SELECT organizationId FROM Operator WHERE name = '"+jsonObject.getString("userName")+"'").get(0).toString()));

			list.addAll(iList);
			while(list.size() >0){
				String string = "";
				list = sqlDataManager.searchList("SELECT id FROM Organization WHERE parentId in(:list)  ORDER BY parentId").setParameterList("list", list).list();
				String string2 = "";
				iList.addAll(list);

			}
			String string = "";
			for(int i =0;i<iList.size();i++){
				string = string+","+iList.get(i);
			}
			System.out.println(string);
			if(jsonObject.get("value")==null || jsonObject.get("value").equals("")){
				sql = "SELECT o1.id,o1.name AS name,o2.name AS parentname  FROM ORGANIZATION o1 left join ORGANIZATION o2 ON o1.parentId = o2.id  WHERE  o1.id in(:iList)   ORDER BY o1.parentId*1";
			}else{
				sql = "SELECT o1.id,o1.name AS name,o2.name AS parentname FROM ORGANIZATION o1 left join ORGANIZATION o2 ON o1.parentId = o2.id  WHERE  o1.id in(:iList) AND o1.name LIKE '%"+jsonObject.getString("value")+"%' ORDER BY o1.parentId*1";
			}
			oList = sqlDataManager.searchSqlList(sql)
					.setFirstResult(Integer.valueOf(jsonObject.getString("start")))
					.setMaxResults(Integer.valueOf(jsonObject.getString("end"))-Integer.valueOf(jsonObject.getString("start")))
					.setParameterList("iList", iList)
					.list();
		}

		OrganizationList organizationList = null;
		List<OrganizationList> tList = new ArrayList<>();
		if(list!=null){
			for(int i =0;i<oList.size();i++){
				Object[] obj = (Object[]) oList.get(i);
				System.out.println((String)obj[1]);
				organizationList = new OrganizationList((Integer)obj[0],(String)obj[1],(String)obj[2]);
				tList.add(organizationList);
			}
			String jsono = JSONObject.toJSON(tList).toString();
			return jsono;
		}

		return null;
	}

	@RequestMapping(value = "/insertOrg",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String insertOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));

		Organization organization = new Organization();
		organization.setName(jsonObject.getString("name"));
		organization.setParentId((long) sqlDataManager.search("SELECT id FROM Organization WHERE name ='"+jsonObject.getString("parentName")+"'").get(0));
		try {
			sqlDataManager.save(organization);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/editOrg",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String editOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		long pId = (long) sqlDataManager.search("SELECT id FROM Organization WHERE name = '"+jsonObject.getString("parentName")+"'").get(0);
		String sql = "UPDATE Organization SET parentId = '"+pId+"',name = '"+jsonObject.getString("name")+"' WHERE id = '"+jsonObject.getString("id")+"'";
		if(sqlDataManager.update(sql)){
			return "1";
		}
		return "0";

	}

	@RequestMapping(value = "/deleteOrg",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteOrg(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));

		try {
			System.out.println(jsonObject.getString("id"));
			String sql = "DELETE FROM Organization WHERE id ='"+jsonObject.getString("id")+"'";
			sqlDataManager.remove(sql);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
