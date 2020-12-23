package com.defensepoint.whitesourcetestcasesjava.repositories.impl.jdbc;

import com.defensepoint.whitesourcetestcasesjava.constants.QueryConstants;
import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Component
@Slf4j
public class SafePureJdbcEntityRepository implements SimpleEntityRepository {
    private static final String INSERT_QUERY = "INSERT INTO entities(field) values (?1)";
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SafePureJdbcEntityRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = find(entity);
        if (result != null) {
            return result;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, entity.getField());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return find(entity);
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("field",entity.getField());
        try {
            return jdbcTemplate.queryForObject(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_NAMED_QUERY, namedParameters, SimpleEntity.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
