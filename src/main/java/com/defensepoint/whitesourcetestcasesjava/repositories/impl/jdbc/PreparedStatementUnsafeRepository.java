package com.defensepoint.whitesourcetestcasesjava.repositories.impl.jdbc;

import com.defensepoint.whitesourcetestcasesjava.constants.QueryConstants;
import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@Slf4j
public class PreparedStatementUnsafeRepository implements SimpleEntityRepository {
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PreparedStatementUnsafeRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = safeFind(entity);
        if (result != null) {
            return result;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format(QueryConstants.INSERT_SQL_SIMPLE_ENTITY_QUERY, entity.getField())
             )
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return safeFind(entity);
    }

    private SimpleEntity safeFind(SimpleEntity entity) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        try {
            return jdbcTemplate.queryForObject(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_NAMED_QUERY, namedParameters, SimpleEntity.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public SimpleEntity find(SimpleEntity entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format(QueryConstants.SELECT_SQL_SIMPLE_ENTITY_QUERY, entity.getField())
             )
        ) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return SimpleEntity.builder()
                        .field(resultSet.getString(1))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleEntity findRemediation(SimpleEntity entity) {
        return null;
    }
}
