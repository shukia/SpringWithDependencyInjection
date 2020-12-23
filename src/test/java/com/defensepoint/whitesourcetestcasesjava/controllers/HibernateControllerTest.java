package com.defensepoint.whitesourcetestcasesjava.controllers;

import com.defensepoint.whitesourcetestcasesjava.WhitesourceTestcasesJavaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = WhitesourceTestcasesJavaApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HibernateControllerTest {
    @Autowired
    private HibernateController controller;
    private final String insertInputWithInjection = "'";
    private final String insertInputWithoutInjection = "Some text";
    private final String selectInputWithInjectionJPQL = "not existing value' or e.field='" + insertInputWithoutInjection;
    private final String selectInputWithInjectionSQL = "not existing value' or field='" + insertInputWithoutInjection;
    private final String selectInputWithoutInjection = "Some text";

    @Test
    void saveHibernateWithInjection() {
        String result = controller.saveHibernate(insertInputWithInjection).getBody();
        assertEquals(result, insertInputWithInjection);
    }


    @Test
    void saveHibernateSessionWithInjection() {
        String result = controller.saveHibernateSession(insertInputWithInjection).getBody();
        assertEquals(result, insertInputWithInjection);
    }

    @Test
    void saveHibernateUnsafeWithoutInjection() {
        String result = controller.saveHibernateUnsafe(insertInputWithoutInjection).getBody();
        assertEquals(result, insertInputWithoutInjection);
    }

    @Test
    void saveHibernateUnsafeWithInjection() {
        assertThrows(PersistenceException.class,() ->controller.saveHibernateUnsafe(insertInputWithInjection));
    }

    @Test
    void saveHibernateSessionUnsafeWithInjection() {
        assertThrows(PersistenceException.class,() ->controller.saveHibernateSessionUnsafe(insertInputWithInjection));
    }

    @Test
    void saveHibernateSessionUnsafeWithoutInjection() {
        String result = controller.saveHibernateSessionUnsafe(insertInputWithoutInjection).getBody();
        assertEquals(result, insertInputWithoutInjection);
    }

    @Test
    void saveHibernateSessionSQLQueryUnsafeWithInjection() {
        assertThrows(PersistenceException.class,() ->controller.saveHibernateUnsafe(insertInputWithInjection));
    }

    @Test
    void selectHibernateUnsafe() {
        controller.saveHibernate(insertInputWithoutInjection);
        String result = controller.selectHibernateUnsafe(selectInputWithInjectionJPQL).getBody();
        assertEquals(selectInputWithoutInjection,result);
    }

    @Test
    void selectHibernateSessionUnsafe() {
        controller.saveHibernate(insertInputWithoutInjection);
        String result = controller.selectHibernateSessionUnsafe(selectInputWithInjectionJPQL).getBody();
        assertEquals(selectInputWithoutInjection,result);
    }

    @Test
    void selectHibernateSessionCriteria() {
        controller.saveHibernate(insertInputWithoutInjection);
        String result = controller.selectHibernateSessionCriteria(selectInputWithInjectionSQL).getBody();
        assertEquals(selectInputWithoutInjection,result);
    }
}