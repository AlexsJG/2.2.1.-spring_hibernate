package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserByCarModelAndSeries() {
        String hql = "Select u FROM User u JOIN FETCH u.car c WHERE c.model = :paramModel AND c.series = :paramSeries";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramModel", "Lada");
        query.setParameter("paramSeries", 100);
        List<User> users = query.getResultList();
        return users;

    }

}
