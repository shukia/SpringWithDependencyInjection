package com.defensepoint.whitesourcetestcasesjava.repositories.impl.hibernate;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class UnsafeHibernateSessionEntityRepository implements SimpleEntityRepository {
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
        session.createNativeQuery(String.format(INSERT_QUERY, entity.getField())).executeUpdate();
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
        // 1. Create the SQL Select query
        // 2. Change user inputs to named parameters
        String sql = "select e from SimpleEntity e where e.field=:field";
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        // 3. Create a Query object with the SQL Select query
        Query query = session.createQuery(sql);

        // 4. Bind user input using the setParameter() method
        query.setParameter("field", entity.getField());

        // 5. Execute the Query object with the getSingleResult() method
        return (SimpleEntity) query.getSingleResult();
    }
}