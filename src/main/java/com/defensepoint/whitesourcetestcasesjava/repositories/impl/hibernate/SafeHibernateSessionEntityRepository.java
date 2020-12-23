package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class SafeHibernateSessionEntityRepository implements SimpleEntityRepository {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        SimpleEntity result = session.find(SimpleEntity.class, entity.getField());
        if (result != null) {
            return result;
        }
        session.save(entity);
        result = session.find(SimpleEntity.class, entity.getField());
        session.close();
        return result;
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
