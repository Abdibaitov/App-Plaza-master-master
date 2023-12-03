package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Application;
import peaksoft.model.User;
import peaksoft.model.enums.Role;
import peaksoft.service.ModelService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserService implements ModelService<User> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
//        if(ro
        List<User> users = findAll();
        if (users.isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        user.setCreateData(LocalDate.now());
        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("from User ").getResultList();
        return users;
    }

    @Override
    public void update(Long id, User user) {
        User oldUser = findById(id);
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setSubscriptionStatus(user.getSubscriptionStatus());
        entityManager.persist(oldUser);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    public User getUserByUserNameAndPassword(String email,String password){
        return (User) entityManager.createQuery("select u from User u where u.email=:em and u.password=:pass")
                .setParameter("em",email)
                .setParameter("pass",password).getSingleResult();
    }
    public void addApplicationByUser(Long userId,Long appId){
        User user = findById(userId);
        Application application = entityManager.find(Application.class,appId);
        if(user!=null && application != null){
            List<Application> myApplications = user.getApplications();
            if(!myApplications.contains(application)){
                myApplications.add(application);
                entityManager.persist(user);
            }
        }
    }
}
