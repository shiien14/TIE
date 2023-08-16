package CornSalad.TIE.repository;

import CornSalad.TIE.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select m from Member m", User.class).getResultList();
    }

    public List<User> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", User.class).setParameter("name",name).getResultList();

    }
}
