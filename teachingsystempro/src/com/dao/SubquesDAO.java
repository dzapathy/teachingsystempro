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

import com.bean.Subques;

/**
 * A data access object (DAO) providing persistence and search support for
 * Subques entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Subques
 * @author MyEclipse Persistence Tools
 */
public class SubquesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(SubquesDAO.class);
	// property constants
	public static final String SUQUESTION = "suquestion";
	public static final String SUANSWER = "suanswer";
	public static final String SUEXPLAIN = "suexplain";
	public static final String SUSCORE = "suscore";
	public static final String SUMEDIAURL = "sumediaurl";
	public static final String SUSTATUS = "sustatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Subques transientInstance) {
		log.debug("saving Subques instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Subques persistentInstance) {
		log.debug("deleting Subques instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*public Subques findById(com.bean.SubquesId id) {
		log.debug("getting Subques instance with id: " + id);
		try {
			Subques instance = (Subques) getHibernateTemplate().get(
					"com.bean.Subques", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}*/
	
	public Object findById(final com.bean.SubquesId id){
	List list = getHibernateTemplate().executeFind(new HibernateCallback() {

		@Override
		public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			
			String hql = "select cid,suchapter,suid,suquestion,suanswer,suexplain,suscore,sumediaurl" +
					" from subques b where b.cid = ? and b.suchapter = ? and b.suid = ?";
			List result = session.createSQLQuery(hql)
				.setParameter(0, id.getCid())
				.setParameter(1, id.getSuchapter())
				.setParameter(2, id.getSuid())
				.list();
			return result;
		}
	});
	return list.get(0);
}

	public List findByExample(Subques instance) {
		log.debug("finding Subques instance by example");
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
		log.debug("finding Subques instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Subques as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySuquestion(Object suquestion) {
		return findByProperty(SUQUESTION, suquestion);
	}

	public List findBySuanswer(Object suanswer) {
		return findByProperty(SUANSWER, suanswer);
	}

	public List findBySuexplain(Object suexplain) {
		return findByProperty(SUEXPLAIN, suexplain);
	}

	public List findBySuscore(Object suscore) {
		return findByProperty(SUSCORE, suscore);
	}

	public List findBySumediaurl(Object sumediaurl) {
		return findByProperty(SUMEDIAURL, sumediaurl);
	}

	public List findBySustatus(Object sustatus) {
		return findByProperty(SUSTATUS, sustatus);
	}

	public List findAll() {
		log.debug("finding all Subques instances");
		try {
			String queryString = "from Subques";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Subques merge(Subques detachedInstance) {
		log.debug("merging Subques instance");
		try {
			Subques result = (Subques) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Subques instance) {
		log.debug("attaching dirty Subques instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subques instance) {
		log.debug("attaching clean Subques instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SubquesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SubquesDAO) ctx.getBean("SubquesDAO");
	}

	public int findCountForSeri(final Integer cid, final short suchapter) {
		int i = 0;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select * from subques b where b.cid = ? and b.suchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, suchapter)
					.list();
				return result;
			}
		});
		return list.size();
	}

	public List findAllPage(final Integer cid, final Short chapter,final Integer currentPage, final Integer pagesize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				List result =  session.createSQLQuery("select * from subques where cid=? and suchapter=?")
			   .setParameter(0, cid).setParameter(1, chapter).setFirstResult((currentPage - 1)*pagesize).setMaxResults(pagesize).list();
				return result;
			}
		});
		return list;
	}

	public void deleteById(final Integer cid, final short suchapter, final Integer suid) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("delete from subques where cid = ? and suchapter = ? and suid = ?")
				.setParameter(0, cid).setParameter(1, suchapter).setParameter(2, suid).executeUpdate();
				return null;
			}
		});
	}

	public void update(final Integer cid, final short suchapter, final Integer suid,final Subques subques) {
		getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery("update subques " +
						"set suquestion = ?,suanswer = ?,suexplain = ?,suscore = ?,sumediaurl = ?" +
						" where cid = ? and suchapter = ? and suid = ?")
				.setParameter(0, subques.getSuquestion()).setParameter(1, subques.getSuanswer())
				.setParameter(2, subques.getSuexplain()).setParameter(3, subques.getSuscore()).setParameter(4, subques.getSumediaurl())
				.setParameter(5, cid).setParameter(6, suchapter).setParameter(7, suid).executeUpdate();
				return null;
			}
		});
	}

	public int findMaxId(final Integer cid, final short suchapter) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select max(suid) from subques b where b.cid = ? and b.suchapter = ?";
				List result = session.createSQLQuery(hql)
					.setParameter(0, cid)
					.setParameter(1, suchapter)
					.list();
				return result;
			}
		});
		return (Integer) list.get(0);
	}
}