package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.CoursePre;
import com.bean.Coursesystem;

/**
 * A data access object (DAO) providing persistence and search support for
 * Coursesystem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.Coursesystem
 * @author MyEclipse Persistence Tools
 */
public class CoursesystemDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CoursesystemDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	
	//获取课程体系
	public List<CoursePre> getCourseSys(final Integer cid){
		try{
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					ArrayList<CoursePre> list = new ArrayList<CoursePre>();
					Connection conn = session.connection();
					String sql = "{CALL getcoursesys(?)}";
					CallableStatement cs = conn.prepareCall(sql);
					cs.setObject(1, cid);
					ResultSet rs = cs.executeQuery();
					while(rs.next()){
						list.add(new CoursePre(rs.getInt("id"), rs.getString("name"),
								rs.getInt("pid"), rs.getString("pname")));						
					}
					return list;				    
				}
			});
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void save(Coursesystem transientInstance) {
		log.debug("saving Coursesystem instance");
		try {
			Transaction transaction= getSession().beginTransaction(); 
			getHibernateTemplate().save(transientInstance);
			transaction.commit();
			getSession().flush();
			getSession().close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void deleteByCid(final Integer cid){
		try{
			getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {					
					Transaction transaction = session.beginTransaction();
					transaction.begin();
					session.createSQLQuery("DELETE FROM coursesystem WHERE id = ?")
								.setParameter(0, cid)
								.executeUpdate();
					transaction.commit();				
					return null;
				}
			});
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(Coursesystem persistentInstance) {
		log.debug("deleting Coursesystem instance");
		try {
			Transaction transaction = getSession().beginTransaction();
			getHibernateTemplate().delete(persistentInstance);
			transaction.commit();
			getSession().flush();
			getSession().close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List findByCid(final Integer cid){
		try{			
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "select c.pid,course.cname from coursesystem c,course "
							+" where c.id = ? and c.pid = course.cid ";
					return session.createSQLQuery(sql).setParameter(0, cid).list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Coursesystem findById(com.bean.CoursesystemId id) {
		log.debug("getting Coursesystem instance with id: " + id);
		try {
			Coursesystem instance = (Coursesystem) getHibernateTemplate().get(
					"com.bean.Coursesystem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Coursesystem instance) {
		log.debug("finding Coursesystem instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Coursesystem instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Coursesystem as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Coursesystem instances");
		try {
			String queryString = "from Coursesystem";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Coursesystem merge(Coursesystem detachedInstance) {
		log.debug("merging Coursesystem instance");
		try {
			Coursesystem result = (Coursesystem) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Coursesystem instance) {
		log.debug("attaching dirty Coursesystem instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Coursesystem instance) {
		log.debug("attaching clean Coursesystem instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CoursesystemDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CoursesystemDAO) ctx.getBean("CoursesystemDAO");
	}
}