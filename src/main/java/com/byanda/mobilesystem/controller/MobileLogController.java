package com.byanda.mobilesystem.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.byanda.mobilesystem.bean.json.LogList;
import com.byanda.mobilesystem.service.SqlDataManager;
import com.byanda.mobilesystem.util.ParseRequestUtil;

@Controller
@RequestMapping(value = "/Log")
public class MobileLogController {

	@Autowired
	private SqlDataManager sqlDataManager;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchLogList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchLog(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObject = ParseRequestUtil.parseToJSONObject(request.getParameter("request"));
		StringBuilder stringBuilder = new StringBuilder();
		String sql = null;
		if((jsonObject.getString("searchstart")==null && jsonObject.getString("searchend")==null) || (jsonObject.getString("start")=="" && jsonObject.getString("end")=="")){

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
			sql = "SELECT l.user,l.date,l.content,o.name FROM LOG l,ORGANIZATION o  WHERE o.id in ("+str+") AND l.orgId = o.id ORDER BY o.id";
		}else{
			StringBuilder sBuilder  = new StringBuilder();
			sBuilder
				.append(jsonObject.getString("searchstart")!=null && !jsonObject.getString("searchstart").equals("")?" date >= '"+jsonObject.getString("searchstart")+"'":"")
				.append(!sBuilder.toString().equals("")?" AND ":"")
				.append(jsonObject.getString("searchend") !=null && !jsonObject.getString("searchend").equals("")?" date <= '"+jsonObject.getString("searchend")+"'":"")
				.insert(0, !sBuilder.toString().equals("")?" WHERE ":"")
				.insert(0, "SELECT l.user,l.date,l.content,o.name FROM LOG l,ORGANIZATION o");
			sql = sBuilder.toString();
		}
		List<Object> list = sqlDataManager.searchSqlList(sql)
				.setFirstResult(Integer.valueOf(jsonObject.getString("start")))
				.setMaxResults(Integer.valueOf(jsonObject.getString("end"))-Integer.valueOf(jsonObject.getString("start")))
				.list();
		List<LogList> tList = new ArrayList<>();
		LogList logList;
		//			List<Object> list = sqlDataManager.search("Select  u.id,u.userName,u.terminalId,u.terminalNum,o.name FROM User u,Organization o WHERE u.orgId = o.id AND row between "+jsonObject.getString("start")+" AND "+jsonObject.getString("end"));
		if(list!=null){
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			for(int i =0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				logList = new LogList((String)obj[0],sdf.format(obj[1]),(String)obj[2],(String)obj[3]);
				tList.add(logList);
			}
			String jsono = JSONObject.toJSON(tList).toString();
			return jsono;
		}
		return null;
	}

}
