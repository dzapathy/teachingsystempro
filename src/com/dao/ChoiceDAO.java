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

import com.bean.Choice;

/**
 * A data access object (DAO) providing persistence and search support for
 * Choice entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Choice
 * @author MyEclipse Persistence Tools
 */
public class ChoiceDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ChoiceDAO.class);
	// property constants
	public static final String CHQUESTION = "chquestion";
	public static final String CHCHOICES = "chchoices";
	public static final String CHANSWER = "chanswer";
	public static final String CHEXPLAIN = "chexplain";
	public static final String CHSCORE = "chscore";
	public static final String CHMEDIAURL = "chmediaurl";
	public static final String CHSTATUS = "chstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Choice transientInstance) {
		log.debug("saving Choice instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Choice persistentInstance) {
		log.debug("deleting Choice instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*public Choice findById(com.bean.ChoiceId id) {
		log.debug("getting Choice instance with id: " + id);
		try {
			Choice instance = (Choice) getHibernateTemplate().get(
					"com.bean.Choice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}*/
	
	public Object findById(final com.bean.ChoiceId id){
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select cid,chchapter,chid,chquestion,chchoices,chanswer,chexplain,chscore,chmediaurl" +
						"     from choice b where b.cid = ? and b.chchapter = ? and b.chid = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, id.getCid())
					.setParameter(1, id.getChchapter())
					.setParameter(2, id.getChid())
					.list();
				return result;
			}
		});
		return list.get(0);
	}

	

	public List findByExample(Choice instance) {
		log.debug("finding Choice instance by example");
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
		log.debug("finding Choice instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Choice as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChquestion(Object chquestion) {
		return findByProperty(CHQUESTION, chquestion);
	}

	public List findByChchoices(Object chchoices) {
		return findByProperty(CHCHOICES, chchoices);
	}

	public List findByChanswer(Object chanswer) {
		return findByProperty(CHANSWER, chanswer);
	}

	public List findByChexplain(Object chexplain) {
		return findByProperty(CHEXPLAIN, chexplain);
	}

	public List findByChscore(Object chscore) {
		return findByProperty(CHSCORE, chscore);
	}

	public List findByChmediaurl(Object chmediaurl) {
		return findByProperty(CHMEDIAURL, chmediaurl);
	}

	public List findByChstatus(Object chstatus) {
		return findByProperty(CHSTATUS, chstatus);
	}

	public List findAll() {
		log.debug("finding all Choice instances");
		try {
			String queryString = "from Choice";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Choice merge(Choice detachedInstance) {
		log.debug("merging Choice instance");
		try {
			Choice result = (Choice) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Choice instance) {
		log.debug("attaching dirty Choice instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Choice instance) {
		log.debug("attaching clean Choice instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ChoiceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ChoiceDAO) ctx.getBean("ChoiceDAO");
	}

	public int findMaxId(final Integer cid, final short chchapter) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {				
				String hql = "select max(chid) from choice b where b.cid = ? and b.chchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, chchapter)
					.list();
				return result;
			}
		});		
		return  list.get(0)==null?0:(Integer)list.get(0);
	}

	public List findAllPage(final Integer cid, final Short chapter, final Integer currentPage,final Integer pagesize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				List result =  session.createSQLQuery("select * from choice where cid=? and chchapter=?")
			   .setParameter(0, cid).setParameter(1, chapter).setFirstResult((currentPage - 1)*pagesize).setMaxResults(pagesize).list();
				return result;
			}
		});
		return list;
	}

	public void deleteById(final Integer cid, final short chchapter, final Integer chid) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("delete from choice where cid = ? and chchapter = ? and chid = ?")
				.setParameter(0, cid).setParameter(1, chchapter).setParameter(2, chid).executeUpdate();
				return null;
			}
		});
	} 

	public void update(final Integer cid, final short chchapter, final Integer chid, final Choice choice) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("update choice " +
						"set chquestion = ?,chchoices = ?,chanswer = ?,chexplain = ?,chscore = ?,chmediaurl = ?" +
						" where cid = ? and chchapter = ? and chid = ?")
				.setParameter(0, choice.getChquestion()).setParameter(1, choice.getChchoices()).setParameter(2, choice.getChanswer())
				.setParameter(3, choice.getChexplain()).setParameter(4, choice.getChscore()).setParameter(5, choice.getChmediaurl())
				.setParameter(6, cid).setParameter(7, chchapter).setParameter(8, chid).executeUpdate();
				return null;
			}
		});
	}

	public int findCountForSeri(final Integer cid, final Short chapter) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select * from choice b where b.cid = ? and b.chchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, chapter)
					.list();
				return result;
			}
		});
		return list.size();
	}
}