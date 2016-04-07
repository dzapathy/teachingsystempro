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

import com.bean.Ppt;

/**
 * A data access object (DAO) providing persistence and search support for Ppt
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Ppt
 * @author MyEclipse Persistence Tools
 */
public class PptDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PptDAO.class);
	// property constants
	public static final String PNAME = "pname";
	public static final String PURL = "purl";
	public static final String PSTATUS = "pstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Ppt transientInstance) {
		log.debug("saving Ppt instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	//获取某章某节PPT总数
	public Integer getCount(final Integer cid , final Short chapter){
		try{
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "select pid from ppt where cid=? and pchapter=?";
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
	
	public void delete(Ppt persistentInstance) {
		log.debug("deleting Ppt instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ppt findById(com.bean.PptId id) {
		log.debug("getting Ppt instance with id: " + id);
		try {
			Ppt instance = (Ppt) getHibernateTemplate().get("com.bean.Ppt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Ppt instance) {
		log.debug("finding Ppt instance by example");
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

	//更新status字段
	public void updatePptStatus(final Ppt ppt){
		try{
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql ="update ppt set pstatus =? where cid=? and pchapter=? and pid=?";
					session.createSQLQuery(sql)
						.setParameter(0, ppt.getPstatus())
						.setParameter(1, ppt.getId().getCid())
						.setParameter(2, ppt.getId().getPchapter())
						.setParameter(3, ppt.getId().getPid())
						.executeUpdate();			
					return null;
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Ppt instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ppt as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	 
	public List<Ppt> findByIdProperty(final String propertyName, final Object value ,final Boolean b) {
		log.debug("finding Ppt instance with property: " + propertyName
				+ ", value: " + value);
		try {
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql ="";
					if(b){
						hql= "FROM Ppt p WHERE p.id."+propertyName +"="+value;
					}else{
						hql= "FROM Ppt p WHERE p.id."+propertyName +"="+value+" AND p.pstatus =1";
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

	public List findByPname(Object pname) {
		return findByProperty(PNAME, pname);
	}

	public List findByPurl(Object purl) {
		return findByProperty(PURL, purl);
	}

	public List findByPstatus(Object pstatus) {
		return findByProperty(PSTATUS, pstatus);
	}

	public List findAll() {
		log.debug("finding all Ppt instances");
		try {
			String queryString = "from Ppt";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ppt merge(Ppt detachedInstance) {
		log.debug("merging Ppt instance");
		try {
			Ppt result = (Ppt) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ppt instance) {
		log.debug("attaching dirty Ppt instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ppt instance) {
		log.debug("attaching clean Ppt instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PptDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PptDAO) ctx.getBean("PptDAO");
	}
}