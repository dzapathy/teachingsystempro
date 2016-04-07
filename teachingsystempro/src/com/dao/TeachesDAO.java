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

import com.bean.Teaches;
import com.bean.TeachesId;

/**
 * A data access object (DAO) providing persistence and search support for
 * Teaches entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Teaches
 * @author MyEclipse Persistence Tools
 */
public class TeachesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TeachesDAO.class);
	// property constants
	public static final String TCHARGE = "tcharge";

	protected void initDao() {
		// do nothing
	}

	public void save(Teaches transientInstance) {
		log.debug("saving Teaches instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Teaches persistentInstance) {
		log.debug("deleting Teaches instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Teaches findById(com.bean.TeachesId id) {
		log.debug("getting Teaches instance with id: " + id);
		try {
			Teaches instance = (Teaches) getHibernateTemplate().get(
					"com.bean.Teaches", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//根据cid，iid获取teaches信息
	public List findCharge(final TeachesId teachesId ){
		try{			
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql ="select t.seid,t.tcharge from teaches t where t.iid=? and t.cid=?";
					List result = session.createSQLQuery(sql)
							.setParameter(0, teachesId.getIid())
							.setParameter(1, teachesId.getCid())
							.list();
					return result;
				}				
			});
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//根据cid获取iid信息
	public List findIid(final Integer cid){
		try{
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "select t.iid from teaches t where t.cid=?";
					return session.createSQLQuery(sql)
							.setParameter(0, cid)
							.list();
				}				
			});
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Teaches instance) {
		log.debug("finding Teaches instance by example");
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
		log.debug("finding Teaches instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Teaches as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTcharge(Object tcharge) {
		return findByProperty(TCHARGE, tcharge);
	}

	public List findAll() {
		log.debug("finding all Teaches instances");
		try {
			String queryString = "from Teaches";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Teaches merge(Teaches detachedInstance) {
		log.debug("merging Teaches instance");
		try {
			Teaches result = (Teaches) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Teaches instance) {
		log.debug("attaching dirty Teaches instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Teaches instance) {
		log.debug("attaching clean Teaches instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TeachesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TeachesDAO) ctx.getBean("TeachesDAO");
	}
}