package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.EntityJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa")
public class SimpleController {
    private final EntityJpaRepository jpaRepository;

    public SimpleController(EntityJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @PostMapping("/safe")
    public ResponseEntity<String> saveJpa(@RequestBody String field) {
        SimpleEntity entity = jpaRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/unsafe")
    public ResponseEntity<Page<SimpleEntity>> findWithUnsafeSorting(@RequestParam("sort") String sort) {
        Pageable pageable = PageRequest.of(0, 3, JpaSort.unsafe(sort));
        Page<SimpleEntity> entities = jpaRepository.findAllUnsafe(pageable);
        return ResponseEntity.ok(entities);
    }
}
