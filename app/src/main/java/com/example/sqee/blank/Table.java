package com.example.sqee.blank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class Table {
    static private String name;
    private static List<TableColumn> columns;
    private static List<TableItem> items;
    public static Map<String, TableItem> ITEM_MAP = new HashMap<String, TableItem>();
    public static DatabaseHelper db;

    public String createCreationString()
    {
        String creationString ="CREATE TABLE "+ name + " (";
        String delim = "";
        for (TableColumn tableColumn : columns)
        {
            String columnName = tableColumn.getName();
            String type = tableColumn.getType().toString();
            creationString=creationString.concat(delim +columnName+" "+type.replace("_"," "));
            delim = ", ";
        }
        creationString=creationString.concat(")");
        Log.d("test",creationString);
        return creationString;
    }
    public String createDeletionString()
    {
        String deletionString = "DROP TABLE IF EXISTS "+ name;
        return deletionString;
    }

    static public String getName(){return name;}
    public void setName(String nameToSet){
        name = nameToSet;
    }

    public static List getTableColumns(){
        return columns;
    }
    public void setColumns(List columnsToSet){
        columns = columnsToSet;
    }

    public static List<TableItem> queryItems(Context c) {
        items = new ArrayList<TableItem>();
        ITEM_MAP = new HashMap<String, TableItem>();
        if (db == null) db = new DatabaseHelper(c); // SQLiteOpenHelper + SQLiteDatabase manager
        SQLiteDatabase actualdb = db.getReadableDatabase();
        String[] tableColumns = new String[] {
                "name"
        };
        String whereClause = "personid = ?";
        String[] whereArgs = new String[] {
                "bla"
        };
        String orderBy = "name";
        Cursor cursor = actualdb.query("Persons", tableColumns, null, null,
                null, null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                TableItem item = new TableItem(cursor.getString(0),cursor.getString(0));
                addItem(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    public static List<TableItem> getItems(){
        return items;
    }

    private static void addItem(TableItem item) {
        items.add(item);
        ITEM_MAP.put(item.itemid, item);
    }




}
