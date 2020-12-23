package com.defensepoint.whitesourcetestcasesjava.repositories;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;

public interface SimpleEntityRepository {
    SimpleEntity save(SimpleEntity entity);
    SimpleEntity find(SimpleEntity entity);
    SimpleEntity findRemediation(SimpleEntity entity);
}
