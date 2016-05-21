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

import com.bean.Reply;

/**
 * A data access object (DAO) providing persistence and search support for Reply
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Reply
 * @author MyEclipse Persistence Tools
 */
public class ReplyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ReplyDAO.class);
	// property constants
	public static final String RCONTENT = "rcontent";
	public static final String RAUTHORID = "rauthorid";
	public static final String RRAUTHORID = "rrauthorid";
	public static final String RURL = "rurl";

	protected void initDao() {
		// do nothing
	}

	public void save(Reply transientInstance) {
		log.debug("saving Reply instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//查找学生回复
	public List findStudentReply(final Integer pid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql ="SELECT s.stid , s.stname ,s.stemail ,s.sturl ,r.rid, r.rcontent, r.rcreatetime "
								+"FROM reply r ,student_basic s "
								+"WHERE  r.rauthorid = s.stid "
								+"AND r.pid = ?";
					return session.createSQLQuery(sql)
							.setInteger(0, pid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}
	
	//查看教师回复
	public List findInstructorReply(final Integer pid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql ="SELECT i.iid , i.iname , i.iemail , i.iurl ,r.rid ,r.rcontent ,r.rcreatetime "
								+"FROM reply r , instructor i "
								+"WHERE r.rauthorid = i.iid "
								+"AND r.pid = ? ";
					return session.createSQLQuery(sql)
							.setInteger(0, pid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}
	
	public void delete(Reply persistentInstance) {
		log.debug("deleting Reply instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reply findById(java.lang.Integer id) {
		log.debug("getting Reply instance with id: " + id);
		try {
			Reply instance = (Reply) getHibernateTemplate().get(
					"com.dao.Reply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reply instance) {
		log.debug("finding Reply instance by example");
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
		log.debug("finding Reply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reply as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRcontent(Object rcontent) {
		return findByProperty(RCONTENT, rcontent);
	}

	public List findByRauthorid(Object rauthorid) {
		return findByProperty(RAUTHORID, rauthorid);
	}

	public List findByRrauthorid(Object rrauthorid) {
		return findByProperty(RRAUTHORID, rrauthorid);
	}

	public List findByRurl(Object rurl) {
		return findByProperty(RURL, rurl);
	}

	public List findAll() {
		log.debug("finding all Reply instances");
		try {
			String queryString = "from Reply";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reply merge(Reply detachedInstance) {
		log.debug("merging Reply instance");
		try {
			Reply result = (Reply) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reply instance) {
		log.debug("attaching dirty Reply instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reply instance) {
		log.debug("attaching clean Reply instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReplyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReplyDAO) ctx.getBean("ReplyDAO");
	}
}