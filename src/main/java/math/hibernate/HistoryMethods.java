package math.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import math.model.User;
import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import math.model.History;
import math.utils.HibernateUtil;

public class HistoryMethods {
	private History history;

	private static final String FIND_EXPRESSION = "from History where expression=:exp";
	
	public HistoryMethods(){}
	
	public HistoryMethods(History history){
		this.history = history;
	}



	public void saveHistory(History history){
	      Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	      session.beginTransaction();
	      session.save(history);
	      session.getTransaction().commit();
	}
	
	public boolean doesExpExistInHistory(String exp){
	      boolean result = false;  
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	      session.beginTransaction();
	      Query q =  session.createQuery(FIND_EXPRESSION);
	      q.setString("exp", exp);
	      List list = q.list();
	      session.getTransaction().commit();
	      if (list.size() > 0){
	    	  result = true;
	      }
	      return result;
	}

	public History getExpressionResult(String exp) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
		Criteria cri = session.createCriteria(History.class);
		cri = cri.add(Restrictions.eq("expression", exp));
		List list = cri.list();
	    session.getTransaction().commit();
		if (list.size() > 0){
			History history = (History) list.get(0);
			return history;
		}
		return null;
	}
	
	public JSONArray getHistoryList(int rows, int valid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
		Criteria cri = session.createCriteria(History.class);
		if ( rows > 0 ){
			cri.setMaxResults(rows);
		}
		if (valid == 0){
			cri = cri.add(Restrictions.eq("isValid", Boolean.FALSE));
		}else if(valid == 1){
			cri = cri.add(Restrictions.eq("isValid", Boolean.TRUE));
		}
		cri.addOrder(Order.desc("date"));
		List list = cri.list();
	    session.getTransaction().commit();
		JSONArray json = JSONArray.fromObject(list);
		return json;
	}

    public JSONArray getUserHistory( int valid, User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria cri = session.createCriteria(History.class);
        cri.add(Restrictions.eq("user",user));
        if (valid == 0){
            cri = cri.add(Restrictions.eq("isValid", Boolean.FALSE));
        }else if(valid == 1){
            cri = cri.add(Restrictions.eq("isValid", Boolean.TRUE));
        }
        List list = cri.list();
        JsonConfig jc = new JsonConfig();
        jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONArray json = JSONArray.fromObject(list,jc);
        return json;
    }
}
