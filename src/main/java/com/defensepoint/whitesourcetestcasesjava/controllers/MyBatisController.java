package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis")
public class MyBatisController {
    private final SimpleEntityRepository safeEntityMyBatisRepository;

    private final SimpleEntityRepository unsafeEntityMyBatisRepository;

    public MyBatisController(SimpleEntityRepository safeEntityMyBatisRepository, SimpleEntityRepository unsafeEntityMyBatisRepository) {
        this.safeEntityMyBatisRepository = safeEntityMyBatisRepository;
        this.unsafeEntityMyBatisRepository = unsafeEntityMyBatisRepository;
    }

    @PostMapping("/safe")
    public ResponseEntity<String> saveMybatis(@RequestBody String field) {
        SimpleEntity entity = safeEntityMyBatisRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/unsafe")
    public ResponseEntity<String> saveMybatisUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafeEntityMyBatisRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }
}
