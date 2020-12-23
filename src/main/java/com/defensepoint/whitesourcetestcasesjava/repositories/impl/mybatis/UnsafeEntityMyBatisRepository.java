package com.defensepoint.whitesourcetestcasesjava.repositories.impl.mybatis;


import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import com.defensepoint.whitesourcetestcasesjava.repositories.UnsafeEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UnsafeEntityMyBatisRepository implements SimpleEntityRepository {
    private final UnsafeEntityMapper unsafeEntityMapper;

    public UnsafeEntityMyBatisRepository(UnsafeEntityMapper unsafeEntityMapper) {
        this.unsafeEntityMapper = unsafeEntityMapper;
    }

    @Override
    public SimpleEntity save(SimpleEntity entity) {
        SimpleEntity result = unsafeEntityMapper.find(entity.getField());
        if (result != null) {
            return result;
        }
        unsafeEntityMapper.save(entity);
        return unsafeEntityMapper.find(entity.getField());
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
