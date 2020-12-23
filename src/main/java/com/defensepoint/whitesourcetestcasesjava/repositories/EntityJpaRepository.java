package com.defensepoint.whitesourcetestcasesjava.repositories;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityJpaRepository extends JpaRepository<SimpleEntity,String> {
    @Query(nativeQuery=true
            , value = "select * from entities \n-- #pageable\n"
            , countQuery = "select count(*) from entities")
    Page<SimpleEntity> findAllUnsafe(Pageable pageable);
}