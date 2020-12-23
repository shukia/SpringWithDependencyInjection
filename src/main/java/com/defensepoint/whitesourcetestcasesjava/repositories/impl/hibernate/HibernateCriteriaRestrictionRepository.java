package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HibernateCriteriaRestrictionRepository implements SimpleEntityRepository {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private static final String SELECT_QUERY = "field='%s'";

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        return null;
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Criteria criteria = session.createCriteria(SimpleEntity.class);
        criteria.add(Restrictions.sqlRestriction(String.format(SELECT_QUERY,entity.getField())));
        return (SimpleEntity) criteria.list().get(0);
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
