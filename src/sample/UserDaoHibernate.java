package sample;

import javafx.scene.control.TableView;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.config.HibernateSessionFactoryUtil;

import java.util.List;


public class UserDaoHibernate {

    public void save(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<User> findAll() {
        Session session = null;
        List<User> users = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            users = session.createQuery("From User order by id").list();
            tx1.commit();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return users;
    }

    public UserDaoHibernate() {
    }

    public boolean checkIn(String login, String password) {
        Session session = null;
        List check = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            check = session.createNativeQuery("select users.login, users.password from users WHERE login='" + login + "' and password='" + password + "'").list();
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        if (check.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    //  public User findById(int id) {
    //      return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(SOAPBinding.User.class, id);
    //  }

    /*public Mobile findByMobile(Mobile mobile) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Mobile.class, mobile);
    }*/
}