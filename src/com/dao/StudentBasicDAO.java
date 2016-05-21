package com.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.StudentBasic;

/**
 * A data access object (DAO) providing persistence and search support for
 * StudentBasic entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.StudentBasic
 * @author MyEclipse Persistence Tools
 */
public class StudentBasicDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StudentBasicDAO.class);
	// property constants
	public static final String STNAME = "stname";
	public static final String STPASSWORD = "stpassword";
	public static final String STEMAIL = "stemail";
	public static final String STURL = "sturl";

	protected void initDao() {
		// do nothing
	}

	public void save(StudentBasic transientInstance) {
		log.debug("saving StudentBasic instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentBasic persistentInstance) {
		log.debug("deleting StudentBasic instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentBasic findById(java.lang.String id) {
		log.debug("getting StudentBasic instance with id: " + id);
		try {
			StudentBasic instance = (StudentBasic) getHibernateTemplate().get(
					"com.bean.StudentBasic", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StudentBasic instance) {
		log.debug("finding StudentBasic instance by example");
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
		log.debug("finding StudentBasic instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StudentBasic as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStname(Object stname) {
		return findByProperty(STNAME, stname);
	}

	public List findByStpassword(Object stpassword) {
		return findByProperty(STPASSWORD, stpassword);
	}

	public List findByStemail(Object stemail) {
		return findByProperty(STEMAIL, stemail);
	}

	public List findBySturl(Object sturl) {
		return findByProperty(STURL, sturl);
	}

	public List findAll() {
		log.debug("finding all StudentBasic instances");
		try {
			String queryString = "from StudentBasic";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentBasic merge(StudentBasic detachedInstance) {
		log.debug("merging StudentBasic instance");
		try {
			StudentBasic result = (StudentBasic) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentBasic instance) {
		log.debug("attaching dirty StudentBasic instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentBasic instance) {
		log.debug("attaching clean StudentBasic instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StudentBasicDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (StudentBasicDAO) ctx.getBean("StudentBasicDAO");
	}
}