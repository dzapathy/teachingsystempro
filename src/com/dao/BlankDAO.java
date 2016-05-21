package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Blank;

/**
 * A data access object (DAO) providing persistence and search support for Blank
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Blank
 * @author MyEclipse Persistence Tools
 */
public class BlankDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(BlankDAO.class);
	// property constants
	public static final String BQUESTION = "bquestion";
	public static final String BANSWER = "banswer";
	public static final String BEXPLAIN = "bexplain";
	public static final String BSCORE = "bscore";
	public static final String BMEDIAURL = "bmediaurl";
	public static final String BSTATUS = "bstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Blank transientInstance) throws Exception {
		log.debug("saving Blank instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		/*log.debug("saving Blank instance");
		try{
			getHibernateTemplate().save(transientInstance);
		}catch(Exception e){
			throw e;
		}*/
	}

	public void delete(Blank persistentInstance) {
		log.debug("deleting Blank instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*public Blank findById(com.bean.BlankId id) {
		log.debug("getting Blank instance with id: " + id);
		try {
			Blank instance = (Blank) getHibernateTemplate().get(
					"com.bean.Blank", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}*/
	
	public Object findById(final com.bean.BlankId id){
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select cid,bchapter,bid,bquestion,banswer,bexplain,bscore,bmediaurl" +
						" from blank b where b.cid = ? and b.bchapter = ? and b.bid = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, id.getCid())
					.setParameter(1, id.getBchapter())
					.setParameter(2, id.getBid())
					.list();
				return result;
			}
		});
		return list.get(0);
	}

	public List findByExample(Blank instance) {
		log.debug("finding Blank instance by example");
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
		log.debug("finding Blank instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Blank as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBquestion(Object bquestion) {
		return findByProperty(BQUESTION, bquestion);
	}

	public List findByBanswer(Object banswer) {
		return findByProperty(BANSWER, banswer);
	}

	public List findByBexplain(Object bexplain) {
		return findByProperty(BEXPLAIN, bexplain);
	}

	public List findByBscore(Object bscore) {
		return findByProperty(BSCORE, bscore);
	}

	public List findByBmediaurl(Object bmediaurl) {
		return findByProperty(BMEDIAURL, bmediaurl);
	}

	public List findByBstatus(Object bstatus) {
		return findByProperty(BSTATUS, bstatus);
	}

	public List findAll() {
		log.debug("finding all Blank instances");
		try {
			String queryString = "from Blank";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Blank merge(Blank detachedInstance) {
		log.debug("merging Blank instance");
		try {
			Blank result = (Blank) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Blank instance) {
		log.debug("attaching dirty Blank instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Blank instance) {
		log.debug("attaching clean Blank instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BlankDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BlankDAO) ctx.getBean("BlankDAO");
	}

	public int findCountForSeri(final Integer cid,final short bchapter) {
		//String queryString = "from Blank b where b.cid = ? and b.bchapter = ?";
		//return getHibernateTemplate().findByNamedQuery(queryString).size();
	//	int i = 0;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select * from blank b where b.cid = ? and b.bchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, bchapter)
					.list();
				return result;
			}
		});
		return list.size();
	}

	public List findAllPage(final Integer cid, final short chapter,final Integer currentPage, final Integer pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				List result =  session.createSQLQuery("select * from blank where cid=? and bchapter=?")
			   .setParameter(0, cid).setParameter(1, chapter).setFirstResult((currentPage - 1)*pageSize).setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	//session.createSQLQuery("select * from blank where cid=? and bchapter=?");
	public void deleteById(final Integer cid, final short bchapter, final Integer bid) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("delete from blank where cid = ? and bchapter = ? and bid = ?")
				.setParameter(0, cid).setParameter(1, bchapter).setParameter(2, bid).executeUpdate();
				return null;
			}
		});
	}

	public void update(final Integer cid, final short bchapter, final Integer bid, final Blank blank) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("update blank " +
						"set bquestion = ?,banswer = ?,bexplain = ?,bscore = ?,bmediaurl = ?" +
						" where cid = ? and bchapter = ? and bid = ?")
				.setParameter(0, blank.getBquestion()).setParameter(1, blank.getBanswer())
				.setParameter(2, blank.getBexplain()).setParameter(3, blank.getBscore()).setParameter(4, blank.getBmediaurl())
				.setParameter(5, cid).setParameter(6, bchapter).setParameter(7, bid).executeUpdate();
				return null;
			}
		});
	}

	public int findMaxId(final Integer cid, final short bchapter) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select max(bid) from blank b where b.cid = ? and b.bchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, bchapter)
					.list();
				return result;
			}
		});
		return  list.get(0)==null?0:(Integer)list.get(0);
	}	
}