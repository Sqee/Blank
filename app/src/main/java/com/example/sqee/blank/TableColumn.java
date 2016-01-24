package com.example.sqee.blank;

public class TableColumn {
    private String name;
    private Type datatype;

    TableColumn(String nameToSet, String datatypeToSet)
    {
        name=nameToSet;
        datatype=Type.valueOf(datatypeToSet.toUpperCase());
    }


    public enum Type {
        CHAR, DATE, INT, DOUBLE, INTEGER_PRIMARY_KEY
    }

    public String getName()
    {
        return name;
    }
    public Type getType()
    {
        return datatype;
    }
}
