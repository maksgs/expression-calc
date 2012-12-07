package math.hibernate;

import math.model.User;
import math.utils.HibernateUtil;
import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Skif
 * Date: 28.11.12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */

public class UserMethods
{
    private User user;

    public UserMethods(){}

    public UserMethods(User user){
        this.user = user;
    }

    public User findByLogin(String login ){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List user = session.createCriteria(User.class).add(Expression.like("name", login)).list();
        if (user.size()==1){
            session.getTransaction().commit();
            return (User)user.get(0);
        }
        //Query q  = session.createQuery("from User where name = :login");
        //User user =(User) q.uniqueResult();
        session.getTransaction().commit();
        return null;
    }

    public void updateUser(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    public void saveUser(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public JSONArray getUserList( int rows){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria cri = session.createCriteria(User.class);
        if ( rows > 0 ){
            cri.setMaxResults(rows);
        }
        cri.addOrder(Order.desc("date"));
        List list = cri.list();
        session.getTransaction().commit();
        JSONArray json = JSONArray.fromObject(list);
        return  json;
    }

    public void deleteUser(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }
}
