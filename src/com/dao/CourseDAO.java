package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Course;

/**
 * A data access object (DAO) providing persistence and search support for
 * Course entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Course
 * @author MyEclipse Persistence Tools
 */
public class CourseDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CourseDAO.class);
	// property constants
	public static final String CNAME = "cname";
	public static final String CCHAPTER = "cchapter";
	public static final String CCREDIT = "ccredit";
	public static final String CCONTENT = "ccontent";
	public static final String CURL = "curl";

	protected void initDao() {
		// do nothing
	}

	public void save(Course transientInstance) {
		log.debug("saving Course instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	//查看已有课程
	public List<Course> getCourses(final String iid){
		try{
			List<Course> courses = new ArrayList<Course>();
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "SELECT distinct c.cid, c.cname,c.ccredit,c.cchapter,c.ccontent,c.curl "
								+"FROM instructor i NATURAL JOIN teaches t NATURAL JOIN section s NATURAL JOIN course c "
								+"WHERE i.iid=?";
					List result = session.createSQLQuery(sql)
									.setParameter(0, iid)
									.list();
					return result;
				}				
			});
			
			for(int i = 0 ; i < list.size() ; i++){
				if(list.get(i) instanceof Object[]){
					Course course =new Course();
					Object[] objects = (Object[])list.get(i);
					course.setCid((Integer)objects[0]);
					course.setCname(objects[1].toString());
					course.setCchapter((Short)objects[3]);
					course.setCcredit((Short)objects[2]);
					course.setCcontent(objects[4].toString());
					course.setCurl(objects[5].toString());
					courses.add(course);
				}
			}
			return courses;
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			throw re;
		}
	}
		
		
	//返回
	public Integer addCourse(Course course){
		log.debug("saving Course instance");
		try {
			getHibernateTemplate().save(course);
			getHibernateTemplate().flush();
			log.debug("save successful");
			return course.getCid();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	
	public void delete(Course persistentInstance) {
		log.debug("deleting Course instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Course findById(java.lang.Integer id) {
		log.debug("getting Course instance with id: " + id);
		try {
			Course instance = (Course) getHibernateTemplate().get(
					"com.bean.Course", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Course instance) {
		log.debug("finding Course instance by example");
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
		log.debug("finding Course instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Course as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List findByCchapter(Object cchapter) {
		return findByProperty(CCHAPTER, cchapter);
	}

	public List findByCcredit(Object ccredit) {
		return findByProperty(CCREDIT, ccredit);
	}

	public List findByCcontent(Object ccontent) {
		return findByProperty(CCONTENT, ccontent);
	}

	public List findByCurl(Object curl) {
		return findByProperty(CURL, curl);
	}

	public List findAll() {
		log.debug("finding all Course instances");
		try {
			String queryString = "from Course";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Course merge(Course detachedInstance) {
		log.debug("merging Course instance");
		try {
			Course result = (Course) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Course instance) {
		log.debug("attaching dirty Course instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Course instance) {
		log.debug("attaching clean Course instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CourseDAO) ctx.getBean("CourseDAO");
	}
}