package com.defensepoint.whitesourcetestcasesjava.repositories.impl.jooq;

import com.defensepoint.whitesourcetestcasesjava.constants.QueryConstants;
import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.springframework.stereotype.Repository;

@Repository
public class JooqRepository implements SimpleEntityRepository {
    private final DSLContext dsl;

    public JooqRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = find(entity);
        if (result!=null){
            return result;

        }
        dsl.query(String.format(QueryConstants.INSERT_SQL_SIMPLE_ENTITY_QUERY,entity.getField())).execute();
        return find(entity);
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        ResultQuery<Record> resultQuery = dsl.resultQuery(String.format(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_QUERY,entity.getField()));
        try {
            return resultQuery.fetchOne().into(SimpleEntity.class);
        }catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
