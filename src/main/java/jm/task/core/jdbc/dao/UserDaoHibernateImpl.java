package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {}


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL, " +
                "PRIMARY KEY (id)" +
                ")";

        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            tx.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null){
                session.delete(user);
            }
            tx.commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        }

    }
}
