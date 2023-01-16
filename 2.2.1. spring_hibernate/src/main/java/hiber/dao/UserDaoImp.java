package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUserByCar(String model, int series){
   Query carQuary = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series");
   carQuary.setParameter("model", model);
   carQuary.setParameter("series", series);
   Car car = (Car) carQuary.getSingleResult();

   Query userQuary = sessionFactory.getCurrentSession().createQuery("from User where car_id = :car_id");
   userQuary.setParameter("car_id", car.getId());
   return (User)userQuary.getSingleResult();


   }
}
