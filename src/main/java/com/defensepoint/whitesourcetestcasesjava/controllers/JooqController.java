package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jooq")
public class JooqController {
    private final SimpleEntityRepository jooqRepository;

    public JooqController(SimpleEntityRepository jooqRepository) {
        this.jooqRepository = jooqRepository;
    }

    @PostMapping("/unsafe")
    public ResponseEntity<String> saveJooqUnsafe(@RequestBody String field) {
        SimpleEntity entity = jooqRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/unsafe")
    public ResponseEntity<String> selectJooqUnsafe(@RequestParam String field) {
        SimpleEntity entity = jooqRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

}
