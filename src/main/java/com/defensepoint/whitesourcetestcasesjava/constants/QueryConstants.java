package com.defensepoint.whitesourcetestcasesjava.constants;

public class QueryConstants {
    private QueryConstants(){
    }
    public static final String SELECT_SQL_SIMPLE_ENTITY_QUERY = "SELECT * FROM entities WHERE field = '%s'";
    public static final String INSERT_SQL_SIMPLE_ENTITY_QUERY = "INSERT INTO entities values ('%s')";
    public static final String SELECT_SQL_SIMPLE_ENTITY_NAMED_QUERY = "SELECT * FROM entities WHERE field = :field";
    public static final String INSERT_SQL_SIMPLE_ENTITY_NAMED_QUERY = "INSERT INTO entities(field) values (:field)";
}
