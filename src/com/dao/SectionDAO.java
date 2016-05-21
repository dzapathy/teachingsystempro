package com.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Section;
import com.bean.SectionId;

/**
 * A data access object (DAO) providing persistence and search support for
 * Section entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Section
 * @author MyEclipse Persistence Tools
 */
public class SectionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(SectionDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Section transientInstance) {
		log.debug("saving Section instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Section persistentInstance) {
		log.debug("deleting Section instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Section findById(com.bean.SectionId id) {
		log.debug("getting Section instance with id: " + id);
		try {
			Section instance = (Section) getHibernateTemplate().get(
					"com.bean.Section", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Section findById2(SectionId id){
		log.debug("getting Section instance with id: " + id);
		try {
			String hql ="from Section s where s.id.cid ="+id.getCid()+" and s.id.seid = "+id.getSeid();
			return (Section)getHibernateTemplate().find(hql).listIterator().next();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Section instance) {
		log.debug("finding Section instance by example");
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
		log.debug("finding Section instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Section as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Section instances");
		try {
			String queryString = "from Section";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Section merge(Section detachedInstance) {
		log.debug("merging Section instance");
		try {
			Section result = (Section) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Section instance) {
		log.debug("attaching dirty Section instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Section instance) {
		log.debug("attaching clean Section instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SectionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SectionDAO) ctx.getBean("SectionDAO");
	}
}