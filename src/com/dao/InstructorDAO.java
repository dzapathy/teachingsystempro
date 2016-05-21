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

import com.bean.Instructor;

/**
 * A data access object (DAO) providing persistence and search support for
 * Instructor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Instructor
 * @author MyEclipse Persistence Tools
 */
public class InstructorDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(InstructorDAO.class);
	// property constants
	public static final String INAME = "iname";
	public static final String IPASSWORD = "ipassword";
	public static final String IEMAIL = "iemail";
	public static final String IPHONE = "iphone";
	public static final String IURL = "iurl";
	public static final String IINTRODUCTION = "iintroduction";
	public static final String ISTATUS = "istatus";

	protected void initDao() {
		// do nothing
	}

	public boolean save(Instructor transientInstance) {
		log.debug("saving Instructor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//注册
	public boolean saveRegister(final Instructor instructor){
		log.debug("saving Instructor instance");
		try {
			Instructor ins = findById(instructor.getIid());
			if(ins != null){
				return false;
			}
			getHibernateTemplate().save(instructor);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			log.error("save failed", re);
			throw re;
		}
	}
	
	//通过ID登录
	public List loginById(final Instructor instructor){
		try {
			List ins = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					List result = session.createSQLQuery("select i.iid,i.iname,i.iurl from instructor i where i.iid = ? and i.ipassword =?")
							.setParameter(0, instructor.getIid())
							.setParameter(1, instructor.getIpassword())
							.list();
					return result;
				}
			});
			return ins;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			throw re;
		}
	}
	
	//修改基本信息
	public boolean changeBasicInfo(final Instructor instructor){
		try{
			Instructor ins = getHibernateTemplate().get(Instructor.class, instructor.getIid());
			ins.setIemail(instructor.getIemail());
			ins.setIintroduction(instructor.getIintroduction());
			ins.setIname(instructor.getIname());
			ins.setIphone(instructor.getIphone());
			getHibernateTemplate().update(ins);
			return true;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			throw re;
		}
	}
	
	//修改密码
	public boolean changePassInfo(String iid, String ipassword,
			String newpassword) {
		try{
			Instructor ins = getHibernateTemplate().get(Instructor.class, iid);
			if(ins.getIpassword().equals(ipassword)){
				ins.setIpassword(newpassword);
				getHibernateTemplate().update(ins);
				return true;
			}
			return false;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			throw re;
		}
	}
	
	//修改头像
	public boolean changePicInfo(String iid, String iurl) {
		try{
			Instructor ins = getHibernateTemplate().get(Instructor.class, iid);
			ins.setIurl(iurl);
			getHibernateTemplate().update(ins);
			return true;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			throw re;
		}
	}
	
	

	public void delete(Instructor persistentInstance) {
		log.debug("deleting Instructor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Instructor findById(java.lang.String id) {
		log.debug("getting Instructor instance with id: " + id);
		try {
			Instructor instance = (Instructor) getHibernateTemplate().get(
					"com.bean.Instructor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Instructor instance) {
		log.debug("finding Instructor instance by example");
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
		log.debug("finding Instructor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Instructor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIname(Object iname) {
		return findByProperty(INAME, iname);
	}

	public List findByIpassword(Object ipassword) {
		return findByProperty(IPASSWORD, ipassword);
	}

	public List findByIemail(Object iemail) {
		return findByProperty(IEMAIL, iemail);
	}

	public List findByIphone(Object iphone) {
		return findByProperty(IPHONE, iphone);
	}

	public List findByIurl(Object iurl) {
		return findByProperty(IURL, iurl);
	}

	public List findByIintroduction(Object iintroduction) {
		return findByProperty(IINTRODUCTION, iintroduction);
	}

	public List findByIstatus(Object istatus) {
		return findByProperty(ISTATUS, istatus);
	}

	public List findAll() {
		log.debug("finding all Instructor instances");
		try {
			String queryString = "from Instructor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Instructor merge(Instructor detachedInstance) {
		log.debug("merging Instructor instance");
		try {
			Instructor result = (Instructor) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Instructor instance) {
		log.debug("attaching dirty Instructor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Instructor instance) {
		log.debug("attaching clean Instructor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InstructorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (InstructorDAO) ctx.getBean("InstructorDAO");
	}
}