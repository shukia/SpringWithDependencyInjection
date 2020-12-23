package com.defensepoint.whitesourcetestcasesjava.repositories.impl.jdbc;

import com.defensepoint.whitesourcetestcasesjava.constants.QueryConstants;
import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SafeEntityJdbcRepository implements SimpleEntityRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SafeEntityJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = find(entity);
        if (result != null) {
            return result;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("field", entity.getField());
        jdbcTemplate.update(QueryConstants.INSERT_SQL_SIMPLE_ENTITY_NAMED_QUERY, params);
        return find(entity);
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        try {
            return jdbcTemplate.queryForObject(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_NAMED_QUERY, namedParameters, SimpleEntity.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) { return null; }
}
