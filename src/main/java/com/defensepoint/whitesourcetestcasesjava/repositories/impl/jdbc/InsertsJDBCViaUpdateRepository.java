package com.defensepoint.whitesourcetestcasesjava.repositories.impl.jdbc;

import com.defensepoint.whitesourcetestcasesjava.constants.QueryConstants;
import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class InsertsJDBCViaUpdateRepository implements SimpleEntityRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public InsertsJDBCViaUpdateRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = safeFind(entity);
        if (result != null) {
            return result;
        }
        jdbcTemplate.update(String.format(QueryConstants.INSERT_SQL_SIMPLE_ENTITY_QUERY, entity.getField()));
        return safeFind(entity);
    }

    private SimpleEntity safeFind(SimpleEntity entity) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        try {
            return namedParameterJdbcTemplate.queryForObject(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_NAMED_QUERY, namedParameters, SimpleEntity.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
