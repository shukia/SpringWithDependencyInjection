package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.WhitesourceTestcasesJavaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WhitesourceTestcasesJavaApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class JdbcControllerTest {
    @Autowired
    private JdbcController controller;
    private final String insertInputWithInjection = "'";
    private final String insertInputWithoutInjection = "Some text";
    private final String selectInputWithInjectionSQL = "not existing value' or field='" + insertInputWithoutInjection;
    private final String selectInputWithoutInjection = "Some text";

    @Test
    void saveJdbcWithInjection() {
        String result = controller.saveJdbc(insertInputWithInjection).getBody();
        assertEquals(result, insertInputWithInjection);
    }

    @Test
    void saveJdbcWithoutInjection() {
        String result = controller.saveJdbc(insertInputWithoutInjection).getBody();
        assertEquals(result, insertInputWithoutInjection);
    }

    @Test
    void saveJdbcUnsafeWithInjection() {
        assertThrows(BadSqlGrammarException.class,() ->controller.saveJdbcUnsafe(insertInputWithInjection));
    }

    @Test
    void saveJdbcUnsafeWithoutInjection() {
        String result = controller.saveJdbcUnsafe(insertInputWithoutInjection).getBody();
        assertEquals(result, insertInputWithoutInjection);
    }

    @Test
    void saveJdbcViaUpdateWithInjection() {
        assertThrows(BadSqlGrammarException.class,() ->controller.saveJdbcViaUpdate(insertInputWithInjection));
    }

    @Test
    void saveJdbcViaUpdateWithoutInjection() {
        String result = controller.saveJdbcViaUpdate(insertInputWithoutInjection).getBody();
        assertEquals(result, insertInputWithoutInjection);
    }

    @Test
    void selectJdbcUnsafe() {

    }

    @Test
    void selectPureJdbcUnsafe() {
    }

    @Test
    void savePureJdbcUnsafe() {
    }

    @Test
    void savePureJdbcViaUpdate() {
    }

    @Test
    void savePureJdbc() {
    }

    @Test
    void savePreparedPureJdbcUnsafe() {
    }

    @Test
    void savePreparedPureJdbcViaExecute() {
    }

    @Test
    void savePureJdbcViaExecuteBatch() {
    }

    @Test
    void savePreparedPureJdbcViaExecuteBatch() {
    }

    @Test
    void selectPreparedPureJdbcUnsafe() {
    }
}