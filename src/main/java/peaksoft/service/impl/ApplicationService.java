package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Application;
import peaksoft.model.Genre;
import peaksoft.service.ModelService;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
public class ApplicationService implements ModelService<Application> {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Application application) {
        Genre genre = entityManager.find(Genre.class,application.getGenreName());
        application.setGenreName(genre.getName());
        application.setGenreName(application.getGenre().getName());
        application.setCreateDate(LocalDate.now());
        entityManager.persist(application);

    }

    @Override
    public Application findById(Long id) {
        Application application =entityManager.find(Application.class,id);
        return application;
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications = entityManager.createQuery("from Application ").getResultList();
        return applications;
    }

    @Override
    public void update(Long id, Application application) {
        Application oldApplication =findById(id);
        oldApplication.setName(application.getName());
        oldApplication.setDescription(application.getDescription());
        oldApplication.setDeveloper(application.getDeveloper());
        oldApplication.setVersion(application.getVersion());
        oldApplication.setAppStatus(application.getAppStatus());
        oldApplication.setGenre(application.getGenre());
        oldApplication.setGenreName(application.getGenre().getName());
        entityManager.merge(oldApplication);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
