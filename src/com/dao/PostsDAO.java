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

import com.bean.Posts;

/**
 * A data access object (DAO) providing persistence and search support for Posts
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Posts
 * @author MyEclipse Persistence Tools
 */
public class PostsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PostsDAO.class);
	// property constants
	public static final String PTITLE = "ptitle";
	public static final String PCONTENT = "pcontent";

	protected void initDao() {
		// do nothing
	}

	public void save(Posts transientInstance) {
		log.debug("saving Posts instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//获取posts总数
	public Long getPostsCount(Integer cid){
		try{
			String hql = "select count(*) from Posts p where p.course.cid="+cid;
			return (Long) getHibernateTemplate().find(hql).listIterator().next();			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取未回复post总数
	public Long getNoReplyPosts(final Integer cid){
		try{
			String hql = "select count(*) from Posts po where po.course.cid="+cid +" and po.replies is empty";
			return (Long) getHibernateTemplate().find(hql).listIterator().next();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取post列表
	public List getPostsList(final Integer cid, final Integer page, final Integer pageSize){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "select p.pid,p.ptitle,p.pcontent,p.pcreatetime "
								+" from posts p where p.cid = ? order by p.pcreatetime desc";
					return session.createSQLQuery(sql)
							.setParameter(0, cid)
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
	
	//获取未评论post列表
	public List getNoReplyList(final Integer cid, final Integer page, final Integer pageSize){
		try{
//			String hql = "from Posts po where po.course.cid="+cid +" and po.replies is empty";
//			return getHibernateTemplate().find(hql).subList((page-1)*pageSize,page* pageSize);
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "from Posts po where po.course.cid="+cid +" and po.replies is empty "
								+"order by po.pcreatetime desc";
					return session.createQuery(sql)
							//.setParameter(0, cid)
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

	//获取发帖信息和发帖人信息
	public List findSender(final Integer pid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "SELECT s.stid ,s.stname ,s.stemail ,s.sturl , p.ptitle ,p.pcontent , p.pcreatetime "
								+"FROM posts p ,student_basic s "
								+"WHERE s.stid = p.stid "
								+"AND p.pid = ?";
					return session.createSQLQuery(sql)
							.setInteger(0, pid)
							.list();
				}
			});
		}catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void delete(Posts persistentInstance) {
		log.debug("deleting Posts instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Posts findById(java.lang.Integer id) {
		log.debug("getting Posts instance with id: " + id);
		try {
			Posts instance = (Posts) getHibernateTemplate().get(
					"com.bean.Posts", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Posts instance) {
		log.debug("finding Posts instance by example");
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
		log.debug("finding Posts instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Posts as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPtitle(Object ptitle) {
		return findByProperty(PTITLE, ptitle);
	}

	public List findByPcontent(Object pcontent) {
		return findByProperty(PCONTENT, pcontent);
	}

	public List findAll() {
		log.debug("finding all Posts instances");
		try {
			String queryString = "from Posts";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Posts merge(Posts detachedInstance) {
		log.debug("merging Posts instance");
		try {
			Posts result = (Posts) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Posts instance) {
		log.debug("attaching dirty Posts instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Posts instance) {
		log.debug("attaching clean Posts instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PostsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PostsDAO) ctx.getBean("PostsDAO");
	}
}