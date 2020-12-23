package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.entities.SimpleEntity;
import com.defensepoint.whitesourcetestcasesjava.repositories.SimpleEntityRepository;
import com.defensepoint.whitesourcetestcasesjava.repositories.impl.jdbc.UnsafePureJdbcEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    private final SimpleEntityRepository safeEntityJdbcRepository;
    private final SimpleEntityRepository unsafeEntityJdbcRepository;
    private final SimpleEntityRepository unsafePureJdbcEntityRepository;
    private final SimpleEntityRepository safePureJdbcEntityRepository;
    private final SimpleEntityRepository insertsPureJDBCViaUpdateRepository;
    private final SimpleEntityRepository insertsJDBCViaUpdateRepository;
    private final SimpleEntityRepository preparedStatementUnsafeRepository;
    private final SimpleEntityRepository preparedStatementInsertViaExecuteRepository;
    private final SimpleEntityRepository executeBatchJDBCRepository;
    private final SimpleEntityRepository preparedExecuteBatchJDBCRepository;

    public JdbcController(SimpleEntityRepository safeEntityJdbcRepository, SimpleEntityRepository unsafeEntityJdbcRepository, UnsafePureJdbcEntityRepository unsafePureJdbcEntityRepository, SimpleEntityRepository safePureJdbcEntityRepository, SimpleEntityRepository insertsPureJDBCViaUpdateRepository, SimpleEntityRepository insertsJDBCViaUpdateRepository, SimpleEntityRepository preparedStatementUnsafeRepository, SimpleEntityRepository preparedStatementInsertViaExecuteRepository, SimpleEntityRepository executeBatchJDBCRepository, SimpleEntityRepository preparedExecuteBatchJDBCRepository) {
        this.safeEntityJdbcRepository = safeEntityJdbcRepository;
        this.unsafeEntityJdbcRepository = unsafeEntityJdbcRepository;
        this.unsafePureJdbcEntityRepository = unsafePureJdbcEntityRepository;
        this.safePureJdbcEntityRepository = safePureJdbcEntityRepository;
        this.insertsPureJDBCViaUpdateRepository = insertsPureJDBCViaUpdateRepository;
        this.insertsJDBCViaUpdateRepository = insertsJDBCViaUpdateRepository;
        this.preparedStatementUnsafeRepository = preparedStatementUnsafeRepository;
        this.preparedStatementInsertViaExecuteRepository = preparedStatementInsertViaExecuteRepository;
        this.executeBatchJDBCRepository = executeBatchJDBCRepository;
        this.preparedExecuteBatchJDBCRepository = preparedExecuteBatchJDBCRepository;
    }


    @PostMapping("/safe")
    public ResponseEntity<String> saveJdbc(@RequestBody String field) {
        SimpleEntity entity = safeEntityJdbcRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/unsafe")
    public ResponseEntity<String> saveJdbcUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafeEntityJdbcRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/unsafe")
    public ResponseEntity<String> selectJdbcUnsafe(@RequestParam String field) {
        SimpleEntity entity = unsafeEntityJdbcRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }


    @PostMapping("/update")
    public ResponseEntity<String> saveJdbcViaUpdate(@RequestBody String field) {
        SimpleEntity entity = insertsJDBCViaUpdateRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/pure/unsafe")
    public ResponseEntity<String> selectPureJdbcUnsafe(@RequestParam String field) {
        SimpleEntity entity = unsafePureJdbcEntityRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/pure/unsafe")
    public ResponseEntity<String> savePureJdbcUnsafe(@RequestBody String field) {
        SimpleEntity entity = unsafePureJdbcEntityRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/pure/update")
    public ResponseEntity<String> savePureJdbcViaUpdate(@RequestBody String field) {
        SimpleEntity entity = insertsPureJDBCViaUpdateRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/pure/safe")
    public ResponseEntity<String> savePureJdbc(@RequestBody String field) {
        SimpleEntity entity = safePureJdbcEntityRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/prepared/unsafe")
    public ResponseEntity<String> savePreparedPureJdbcUnsafe(@RequestBody String field) {
        SimpleEntity entity = preparedStatementUnsafeRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/prepared/execute")
    public ResponseEntity<String> savePreparedPureJdbcViaExecute(@RequestBody String field) {
        SimpleEntity entity = preparedStatementInsertViaExecuteRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/pure/executeBatch")
    public ResponseEntity<String> savePureJdbcViaExecuteBatch(@RequestBody String field) {
        SimpleEntity entity = executeBatchJDBCRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @PostMapping("/prepared/executeBatch")
    public ResponseEntity<String> savePreparedPureJdbcViaExecuteBatch(@RequestBody String field) {
        SimpleEntity entity = preparedExecuteBatchJDBCRepository.save(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }

    @GetMapping("/prepared/unsafe")
    public ResponseEntity<String> selectPreparedPureJdbcUnsafe(@RequestParam String field) {
        SimpleEntity entity = preparedStatementUnsafeRepository.find(SimpleEntity.builder()
                .field(field)
                .build());
        return ResponseEntity.ok(entity.getField());
    }
}