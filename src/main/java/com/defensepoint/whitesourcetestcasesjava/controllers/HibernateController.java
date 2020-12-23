package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hibernate")
public class HibernateController {
    private final SimpleEntityRepository safeEntityHibernateRepository;
    private final SimpleEntityRepository unsafeEntityHibernateRepository;
    private final SimpleEntityRepository safeHibernateSessionEntityRepository;
    private final SimpleEntityRepository unsafeHibernateSessionEntityRepository;
    private final SimpleEntityRepository unsafeSQLQueryHibernateSessionRepository;
    private final SimpleEntityRepository hibernateCriteriaRestrictionRepository;

    public HibernateController(SimpleEntityRepository safeEntityHibernateRepository
            , SimpleEntityRepository unsafeEntityHibernateRepository
            , SimpleEntityRepository safeHibernateSessionEntityRepository
            , SimpleEntityRepository unsafeHibernateSessionEntityRepository, SimpleEntityRepository unsafeSQLQueryHibernateSessionRepository, SimpleEntityRepository hibernateCriteriaRestrictionRepository) {
        this.safeEntityHibernateRepository = safeEntityHibernateRepository;
        this.unsafeEntityHibernateRepository = unsafeEntityHibernateRepository;
        this.safeHibernateSessionEntityRepository = safeHibernateSessionEntityRepository;
        this.unsafeHibernateSessionEntityRepository = unsafeHibernateSessionEntityRepository;
        this.unsafeSQLQueryHibernateSessionRepository = unsafeSQLQueryHibernateSessionRepository;
        this.hibernateCriteriaRestrictionRepository = hibernateCriteriaRestrictionRepository;
    }

    @PostMapping("/safe")
    public ResponseEntity<String> saveHibernate(@RequestBody String field) {
        SimpleEntity entity = safeEntityHibernateRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/session/safe")
    public ResponseEntity<String> saveHibernateSession(@RequestBody String field) {
        SimpleEntity entity = safeHibernateSessionEntityRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/unsafe")
    public ResponseEntity<String> saveHibernateUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafeEntityHibernateRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/session/unsafe")
    public ResponseEntity<String> saveHibernateSessionUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafeHibernateSessionEntityRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/session/SQLQuery/unsafe")
    public ResponseEntity<String> saveHibernateSessionSQLQueryUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafeSQLQueryHibernateSessionRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/unsafe")
    public ResponseEntity<String> selectHibernateUnsafe(@RequestParam String field) {
        SimpleEntity entity = unsafeEntityHibernateRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }
    @GetMapping("/session/unsafe")
    public ResponseEntity<String> selectHibernateSessionUnsafe(@RequestParam String field) {
        SimpleEntity entity = unsafeHibernateSessionEntityRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/session/criteria")
    public ResponseEntity<String> selectHibernateSessionCriteria(@RequestParam String field) {
        SimpleEntity entity = hibernateCriteriaRestrictionRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }
}
