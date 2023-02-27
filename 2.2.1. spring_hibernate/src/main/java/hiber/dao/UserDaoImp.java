package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserFromCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Car where model = :model and series = : series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        long userId = (long) query.getSingleResult();
        query = sessionFactory.getCurrentSession().createQuery("from User where id = :userId");
        query.setParameter("userId", userId);

        return (User) query.getSingleResult();
    }
}
