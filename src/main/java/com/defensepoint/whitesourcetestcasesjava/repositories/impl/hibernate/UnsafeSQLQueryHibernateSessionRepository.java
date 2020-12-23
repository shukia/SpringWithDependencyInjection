package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class UnsafeSQLQueryHibernateSessionRepository implements SimpleEntityRepository {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private static final String INSERT_QUERY = "INSERT INTO entities(field) values ('%s')";
    private static final String SELECT_QUERY = "select e from SimpleEntity e where e.field='%s'";

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        SimpleEntity result = session.find(SimpleEntity.class, entity.getField());
        if (result != null) {
            return result;
        }
        session.beginTransaction();
        session.createSQLQuery(String.format(INSERT_QUERY, entity.getField())).executeUpdate();
        result = session.find(SimpleEntity.class, entity.getField());
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return (SimpleEntity) session.createQuery(String.format(SELECT_QUERY,entity.getField())).getSingleResult();
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
