package com.defensepoint.whitesourcetestcasesjava.repositories.impl.mybatis;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityMapper;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class SafeEntityMyBatisRepository implements SimpleEntityRepository {
    private final SimpleEntityMapper simpleEntityMapper;

    public SafeEntityMyBatisRepository(SimpleEntityMapper simpleEntityMapper) {
        this.simpleEntityMapper = simpleEntityMapper;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = simpleEntityMapper.find(entity.getField());
        if (result != null) {
            return result;
        }
        simpleEntityMapper.save(entity);
        return simpleEntityMapper.find(entity.getField());
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
