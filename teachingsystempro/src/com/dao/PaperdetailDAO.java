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

import com.bean.Paperdetail;

/**
 * A data access object (DAO) providing persistence and search support for
 * Paperdetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.Paperdetail
 * @author MyEclipse Persistence Tools
 */
public class PaperdetailDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PaperdetailDAO.class);
	// property constants
	public static final String CID = "cid";
	public static final String CHAPTER = "chapter";
	public static final String QID = "qid";
	public static final String QUESTIONTYPE = "questiontype";
	public static final String NUMBER = "number";
	public static final String STANSWER = "stanswer";
	public static final String PSCORE = "pscore";
	public static final String PCHECKED = "pchecked";
	public static final String PSTATUS = "pstatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Paperdetail transientInstance) {
		log.debug("saving Paperdetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//存储
	public void saveP(final Paperdetail paperdetail) {
		try {
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String sql = " INSERT INTO paperdetail(pfid,cid,chapter,qid,questiontype,number)  "
								+" VALUES(?,?,?,?,?,?)";
					session.createSQLQuery(sql)
						.setInteger(0, paperdetail.getPaperform().getPfid())
						.setInteger(1, paperdetail.getCid())
						.setShort(2, paperdetail.getChapter())
						.setInteger(3, paperdetail.getQid())
						.setShort(4, paperdetail.getQuestiontype())
						.setShort(5, paperdetail.getNumber())
						.executeUpdate();
					return null;
				}
			});
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//获取总分
	public List getTotalGrade(final String stid , final Integer pfid){
		try{	
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT SUM(p.pscore) FROM paperdetail p "
							+" WHERE p.pfid = ? AND p.stid = ? ";
					return session.createSQLQuery(sql)
							.setInteger(0, pfid)
							.setString(1, stid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			System.out.println("error:"+re);
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取选择题
	public List getChoices(final Integer pfid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT DISTINCT c.chquestion , c.chchoices , c.chanswer , c.chexplain ,  c.chscore , c.chmediaurl "
								+" FROM paperdetail pd , choice c "
								+" WHERE pd.cid = c.cid AND pd.chapter = c.chchapter AND pd.qid = c.chid AND pd.questiontype = 1 AND pd.pfid = ? "
								+" GROUP BY pd.number ";
					return session.createSQLQuery(sql)
							.setInteger(0, pfid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取填空题
	public List getBlanks(final Integer pfid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT DISTINCT b.bquestion , b.banswer , b.bexplain , b.bscore , b.bmediaurl "
								+" FROM paperdetail pd , blank b "
								+" WHERE pd.cid = b.cid AND pd.chapter = b.bchapter AND pd.qid = b.bid AND pd.questiontype = 2 AND pd.pfid = ? "
								+" GROUP BY pd.number";
					return session.createSQLQuery(sql)
							.setInteger(0, pfid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取主观题
	public List getSubQues(final Integer pfid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT DISTINCT s.suquestion , s.suanswer , s.suexplain , s.suscore , s.sumediaurl "
								+" FROM paperdetail pd , subques s "
								+" WHERE pd.cid = s.cid AND pd.chapter = s.suchapter AND pd.qid = s.suid AND pd.questiontype = 3 AND pd.pfid = ? "
								+" GROUP BY pd.number";
					return session.createSQLQuery(sql)
							.setInteger(0, pfid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取学生主观题信息
	public List getStuAns(final String stid , final Integer pfid){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = " SELECT p.pid , s.suquestion , p.stanswer , p.pscore , s.suscore"
								+" FROM paperdetail p NATURAL JOIN student_basic sb , subques s "
								+" WHERE  p.cid = s.cid AND p.chapter = s.suchapter AND p.qid = s.suid "
								+" AND p.questiontype = 3 "
								+" AND sb.stid = ? AND p.pfid = ?"
								+" ORDER BY p.number ";
					return session.createSQLQuery(sql)
							.setString(0, stid)
							.setInteger(1, pfid)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//判分
	public void chageScore(final Integer pid , final Short score){
		try{
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "UPDATE paperdetail p SET p.pscore = ? ,p.pstatus = 1 ,p.pchecked = 1  "
								+" WHERE p.pid= ? ";
					session.createSQLQuery(sql)
						.setShort(0, score)
						.setInteger(1, pid)
						.executeUpdate();
					return null;
				}
			});
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//获取选择题总数
	public Long getCChoCount( Integer cid){
		try{
			String hql = "select count(*) from Choice c where c.course.cid="+cid;
			return (Long) getHibernateTemplate().find(hql).listIterator().next();			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取填空题总数
	public Long getCBlaCount ( Integer cid) {
		try{
			String hql = "select count(*) from Blank b where b.course.cid="+cid;
			return (Long) getHibernateTemplate().find(hql).listIterator().next();			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//获取主观题总数
	public Long getCSubCount (Integer cid){
		try{
			String hql = "select count(*) from Subques s where s.course.cid="+cid;
			return (Long) getHibernateTemplate().find(hql).listIterator().next();			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//抽取选择题
	public List getCChoQues(final Integer cid , final Short pftype,
			final Short startChapter ,final Short endChapter, final Integer  maxNum){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql  =" SELECT c.cid , c.chchapter , c.chid , c.chquestion , c.chchoices , c.chanswer ,c.chscore ,c.chmediaurl "
								+" FROM choice c "
								+" WHERE c.cid = ? AND (c.chchapter BETWEEN ? AND ?) "
								+" ORDER BY RAND() ";
					return session.createSQLQuery(sql)
							.setInteger(0, cid)
							.setShort(1, startChapter)
							.setShort(2, endChapter)
							.setMaxResults(maxNum)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//抽取填空题
	public List getCBlaQues(final Integer cid , final Short pftype,
			final Short startChapter ,final Short endChapter, final Integer  maxNum){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql  =" SELECT b.cid , b.bchapter , b.bid , b.bquestion , b.banswer , b.bscore , b.bmediaurl "
								+" FROM blank b "
								+" WHERE b.cid = ? AND (b.bchapter BETWEEN ? AND ?) "
								+" ORDER BY RAND() ";
					return session.createSQLQuery(sql)
							.setInteger(0, cid)
							.setShort(1, startChapter)
							.setShort(2, endChapter)
							.setMaxResults(maxNum)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//抽取主观题
	public List getCSubQues(final Integer cid , final Short pftype,
			final Short startChapter ,final Short endChapter, final Integer  maxNum){
		try{
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql  =" SELECT s.cid , s.suchapter , s.suid , s.suquestion , s.suanswer ,s.suscore ,s.sumediaurl "
								+" FROM subques s  "
								+" WHERE s.cid = ? AND (s.suchapter BETWEEN ? AND ?) "
								+" ORDER BY RAND() ";
					return session.createSQLQuery(sql)
							.setInteger(0, cid)
							.setShort(1, startChapter)
							.setShort(2, endChapter)
							.setMaxResults(maxNum)
							.list();
				}
			});
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void delete(Paperdetail persistentInstance) {
		log.debug("deleting Paperdetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Paperdetail findById(java.lang.Integer id) {
		log.debug("getting Paperdetail instance with id: " + id);
		try {
			Paperdetail instance = (Paperdetail) getHibernateTemplate().get(
					"com.bean.Paperdetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Paperdetail instance) {
		log.debug("finding Paperdetail instance by example");
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
		log.debug("finding Paperdetail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Paperdetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCid(Object cid) {
		return findByProperty(CID, cid);
	}

	public List findByChapter(Object chapter) {
		return findByProperty(CHAPTER, chapter);
	}

	public List findByQid(Object qid) {
		return findByProperty(QID, qid);
	}

	public List findByQuestiontype(Object questiontype) {
		return findByProperty(QUESTIONTYPE, questiontype);
	}

	public List findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List findByStanswer(Object stanswer) {
		return findByProperty(STANSWER, stanswer);
	}

	public List findByPscore(Object pscore) {
		return findByProperty(PSCORE, pscore);
	}

	public List findByPchecked(Object pchecked) {
		return findByProperty(PCHECKED, pchecked);
	}

	public List findByPstatus(Object pstatus) {
		return findByProperty(PSTATUS, pstatus);
	}

	public List findAll() {
		log.debug("finding all Paperdetail instances");
		try {
			String queryString = "from Paperdetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Paperdetail merge(Paperdetail detachedInstance) {
		log.debug("merging Paperdetail instance");
		try {
			Paperdetail result = (Paperdetail) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Paperdetail instance) {
		log.debug("attaching dirty Paperdetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Paperdetail instance) {
		log.debug("attaching clean Paperdetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PaperdetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PaperdetailDAO) ctx.getBean("PaperdetailDAO");
	}
}