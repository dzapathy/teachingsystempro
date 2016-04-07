package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Takes;

/**
 * A data access object (DAO) providing persistence and search support for Takes
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Takes
 * @author MyEclipse Persistence Tools
 */
public class TakesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TakesDAO.class);
	// property constants
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public List findStuList(final Integer cid,final String seid,final Integer page,final Integer pageSize){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql =" SELECT s.stid,s.stname,s.stemail,t.seid "
								+" FROM student_basic s NATURAL JOIN takes t "
								+" WHERE t.cid= ? AND t.seid in ( "+seid+" ) ";
					return session.createSQLQuery(sql)
							.setParameter(0, cid)
							.setFirstResult((page-1)*pageSize)
							.setMaxResults(pageSize)
							.list();
				}				
			});
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//获取总页数
	public Integer getStudentTotal(final Integer cid,final String seid){
		try{
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql =" SELECT s.stid"
								+" FROM student_basic s NATURAL JOIN takes t "
								+" WHERE t.cid= ? AND t.seid in ( "+seid+" ) ";
					return session.createSQLQuery(sql)
							.setParameter(0, cid)
							.list();
				}				
			});
			return list.size();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void save(Takes transientInstance) {
		log.debug("saving Takes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Takes persistentInstance) {
		log.debug("deleting Takes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	

	public Takes findById(com.bean.TakesId id) {
		log.debug("getting Takes instance with id: " + id);
		try {
			Takes instance = (Takes) getHibernateTemplate().get(
					"com.bean.Takes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Takes instance) {
		log.debug("finding Takes instance by example");
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
		log.debug("finding Takes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Takes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Takes instances");
		try {
			String queryString = "from Takes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Takes merge(Takes detachedInstance) {
		log.debug("merging Takes instance");
		try {
			Takes result = (Takes) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Takes instance) {
		log.debug("attaching dirty Takes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Takes instance) {
		log.debug("attaching clean Takes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TakesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TakesDAO) ctx.getBean("TakesDAO");
	}
}