package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class UnsafeEntityHibernateRepository implements SimpleEntityRepository {
    private final EntityManager entityManager;
    private static final String INSERT_QUERY = "INSERT INTO entities(field) values ('%s')";
    private static final String SELECT_QUERY = "select e from SimpleEntity e where e.field='%s'";

    public UnsafeEntityHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = safeFind(entity);
        if (result != null) {
            return result;
        }
        entityManager.createNativeQuery(String.format(INSERT_QUERY, entity.getField())).executeUpdate();
        return safeFind(entity);
    }

    private SimpleEntity safeFind(SimpleEntity entity){
        return entityManager.find(SimpleEntity.class, entity.getField());
    }

    @Override
    @Transactional
    public SimpleEntity find(SimpleEntity entity) {
        Query query = entityManager.createQuery(String.format(SELECT_QUERY,entity.getField()),SimpleEntity.class);
        return (SimpleEntity) query.getSingleResult();
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}