package com.defensepoint.whitesourcetestcasesjava.repositories;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UnsafeEntityMapper {
    @Select("SELECT * FROM entities WHERE field = '${field}'")
    @Results(value = {
            @Result(property = "field", column = "field")
    })
    SimpleEntity find(@Param("field") String field);

    @Insert("Insert into entities(field) values ('${field}')")
    int save(SimpleEntity entity);
}
