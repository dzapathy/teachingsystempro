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

import com.bean.Paperform;

/**
 * A data access object (DAO) providing persistence and search support for
 * Paperform entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Paperform
 * @author MyEclipse Persistence Tools
 */
public class PaperformDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PaperformDAO.class);
	// property constants
	public static final String PFNAME = "pfname";
	public static final String IID = "iid";
	public static final String PFTYPE = "pftype";
	public static final String PFSTATUS = "pfstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Paperform transientInstance) {
		log.debug("saving Paperform instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Paperform persistentInstance) {
		log.debug("deleting Paperform instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Paperform findById(java.lang.Integer id) {
		log.debug("getting Paperform instance with id: " + id);
		try {
			Paperform instance = (Paperform) getHibernateTemplate().get(
					"com.bean.Paperform", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Paperform instance) {
		log.debug("finding Paperform instance by example");
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
		log.debug("finding Paperform instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Paperform as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPfname(Object pfname) {
		return findByProperty(PFNAME, pfname);
	}

	public List findByIid(Object iid) {
		return findByProperty(IID, iid);
	}

	public List findByPftype(Object pftype) {
		return findByProperty(PFTYPE, pftype);
	}

	public List findByPfstatus(Object pfstatus) {
		return findByProperty(PFSTATUS, pfstatus);
	}

	public List findAll() {
		log.debug("finding all Paperform instances");
		try {
			String queryString = "from Paperform";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Paperform merge(Paperform detachedInstance) {
		log.debug("merging Paperform instance");
		try {
			Paperform result = (Paperform) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Paperform instance) {
		log.debug("attaching dirty Paperform instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Paperform instance) {
		log.debug("attaching clean Paperform instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PaperformDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PaperformDAO) ctx.getBean("PaperformDAO");
	}
	
	//save,返回主键
	public Paperform saveId(Paperform paperform){
		log.debug("saving Paperform instance");
		try {
			getHibernateTemplate().save(paperform);
			log.debug("save successful");
			return paperform;			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//获取paper总数
	public Long getPapersCount(Integer cid, String iid){
		String hql = "select count(*) from Paperform p where p.course.id = " +cid + " AND p.iid = "+iid;
		return (Long)getHibernateTemplate().find(hql).listIterator().next();
	}
	
	//获取paper列表
	public List getPapers(final Integer cid ,final String iid,final Integer page ,final Integer pageSize){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "SELECT p.pfid , p.pfname , DATE_FORMAT(p.pfcreatetime,'%Y/%m/%d %H:%i') , p.pfstatus , p.pftype "
								+" FROM paperform p "
								+" WHERE p.cid = ? AND p.iid = ?"
								+" ORDER BY p.pfcreatetime DESC";
					return session.createSQLQuery(sql)
							.setInteger(0, cid)
							.setString(1, iid)
							.setFirstResult((page-1)*pageSize)
							.setMaxResults(pageSize)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void update(Paperform paperform){
		log.debug("update Paperform instance");
		try {
			getHibernateTemplate().update(paperform);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
}