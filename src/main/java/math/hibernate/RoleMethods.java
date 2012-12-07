package math.hibernate;

import math.model.Role;
import math.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

public class RoleMethods
{
    public void saveRole(Role role){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
    }

    public Role getUserRole( ){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q  = session.createQuery("from Role where id = 1");
        Role role =(Role) q.uniqueResult();
        session.getTransaction().commit();
        return   role;
    }

    public Role getAdminRole(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q  = session.createQuery("from Role where id = 2");
        Role role =(Role) q.uniqueResult();
        session.getTransaction().commit();
        return   role;
    }

    public List<Role> getAllRoles(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria cri = session.createCriteria(Role.class);
        List list = cri.list();
        return list;
    }
}
