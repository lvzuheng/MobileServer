package com.byanda.mobilesystem.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Service
@Transactional
@Component
public class SqlDataManager {
	@Resource
	private SessionFactory sessionFactory;
	public Session Session() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(Object obj){
		try {
			Session().save(obj);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}


	public boolean update(String sql){
		try {
			Session().createQuery(sql).executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public <T> List<Object> search(Class<T> t){

		return Session().createQuery("FROM "+t.getSimpleName()).list();
	}

	public List<Object> search(String sql) {
		return Session().createQuery(sql).list();
	}
	public List<Integer> searchReturnId(String sql) {
		return Session().createQuery(sql).list();
	}

	public Query searchList(String sql) {
		return Session().createQuery(sql);
	}
	public SQLQuery searchSqlList(String sql) {
		return Session().createSQLQuery(sql);
	}

	public <T> void remove(T t){
		Session().delete(t);
	}
	public  void remove(String sql){
		Session().createQuery(sql).executeUpdate();
	}
}
