package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Component
public class SafeEntityHibernateRepository implements SimpleEntityRepository {
    private final EntityManager entityManager;

    public SafeEntityHibernateRepository(EntityManager sessionFactory) {
        this.entityManager = sessionFactory;
    }

    @Override
    @Transactional
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = entityManager.find(SimpleEntity.class, entity.getField());
        if (result != null) {
            return result;
        }
        entityManager.persist(entity);
        return entityManager.find(SimpleEntity.class, entity.getField());
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        return null;
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
