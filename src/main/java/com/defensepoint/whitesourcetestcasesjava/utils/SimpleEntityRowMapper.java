package com.defensepoint.whitesourcetestcasesjava.utils;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SimpleEntityRowMapper implements RowMapper<SimpleEntity> {
    @Override
    public SimpleEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return SimpleEntity.builder()
                .field(resultSet.getString("field"))
                .build();
    }
}
