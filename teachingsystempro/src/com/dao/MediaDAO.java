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

import com.bean.Media;
import com.bean.MediaId;

/**
 * A data access object (DAO) providing persistence and search support for Media
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Media
 * @author MyEclipse Persistence Tools
 */
public class MediaDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(MediaDAO.class);
	// property constants
	public static final String MNAME = "mname";
	public static final String MURL = "murl";
	public static final String MTYPE = "mtype";
	public static final String MSTATUS = "mstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Media transientInstance) {
		log.debug("saving Media instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public Integer getCount(final Integer cid , final Short chapter){
		try{
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "select mid from media where cid=? and mchapter=?";
					return session.createSQLQuery(sql)
					.setParameter(0, cid)
					.setParameter(1, chapter)
					.list();
				}
			});
			return list.size();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Media persistentInstance) {
		log.debug("deleting Media instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Media findById(com.bean.MediaId id) {
		log.debug("getting Media instance with id: " + id);
		try {
			Media instance = (Media) getHibernateTemplate().get(
					"com.bean.Media", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void updateVideoStatus(final Media media){
		try{
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {	
					String sql="update media set mstatus =? where cid=? and mchapter=? and mid=?";
					session.createSQLQuery(sql)
						.setParameter(0, media.getMstatus())
						.setParameter(1, media.getId().getCid())
						.setParameter(2, media.getId().getMchapter())
						.setParameter(3, media.getId().getMid())
						.executeUpdate();
					return null;
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Media instance) {
		log.debug("finding Media instance by example");
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
		log.debug("finding Media instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Media as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Media> findByIdProperty(final String propertyName,final Object value ,final Boolean b){
		log.debug("finding Media instance with property: " + propertyName
				+ ", value: " + value);
		try {
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql ="";
					if(b){
						hql= "FROM Media m WHERE m.id."+propertyName +"="+value;
					}else{
						hql= "FROM Media m WHERE m.id."+propertyName +"="+value+" AND m.mstatus = 1";
					}
					List result = session.createQuery(hql).list();
					return result;
				}
			});
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByMname(Object mname) {
		return findByProperty(MNAME, mname);
	}

	public List findByMurl(Object murl) {
		return findByProperty(MURL, murl);
	}

	public List findByMtype(Object mtype) {
		return findByProperty(MTYPE, mtype);
	}

	public List findByMstatus(Object mstatus) {
		return findByProperty(MSTATUS, mstatus);
	}

	public List findAll() {
		log.debug("finding all Media instances");
		try {
			String queryString = "from Media";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Media merge(Media detachedInstance) {
		log.debug("merging Media instance");
		try {
			Media result = (Media) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Media instance) {
		log.debug("attaching dirty Media instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Media instance) {
		log.debug("attaching clean Media instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MediaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MediaDAO) ctx.getBean("MediaDAO");
	}
}