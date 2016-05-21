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

import com.bean.Grade;

/**
 * A data access object (DAO) providing persistence and search support for Grade
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Grade
 * @author MyEclipse Persistence Tools
 */
public class GradeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(GradeDAO.class);
	// property constants
	public static final String GRADE = "grade";

	protected void initDao() {
		// do nothing
	}

	public void save(Grade transientInstance) {
		log.debug("saving Grade instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Grade persistentInstance) {
		log.debug("deleting Grade instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	//获取成绩列表
	public List getGradeList(final Integer pfid ,final Integer cid, final Integer page){
		try{			
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT t.seid ,s.stid , s.stname , (SELECT g.grade FROM grade g WHERE g.stid = s.stid AND g.pfid = ?) as score "
								+" FROM student_basic s NATURAL JOIN takes t "
								+" WHERE t.cid = ? ";
					return session.createSQLQuery(sql)
							.setInteger(0, pfid)
							.setInteger(1, cid)
//							.setFirstResult((page-1)*10)
//							.setMaxResults(10)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Grade findById(com.bean.GradeId id) {
		log.debug("getting Grade instance with id: " + id);
		try {
			Grade instance = (Grade) getHibernateTemplate().get(
					"com.bean.Grade", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Grade instance) {
		log.debug("finding Grade instance by example");
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
		log.debug("finding Grade instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Grade as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findAll() {
		log.debug("finding all Grade instances");
		try {
			String queryString = "from Grade";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Grade merge(Grade detachedInstance) {
		log.debug("merging Grade instance");
		try {
			Grade result = (Grade) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Grade instance) {
		log.debug("attaching dirty Grade instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grade instance) {
		log.debug("attaching clean Grade instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GradeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GradeDAO) ctx.getBean("GradeDAO");
	}
}