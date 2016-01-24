package com.example.sqee.blank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String logCatTag = DatabaseHelper.class.getName();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Blank";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        List<Table> tableList = createTableList();
        for (Table table:tableList) {
            db.execSQL(table.createCreationString());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        List<Table> tableList = createTableList();
        for (Table table:tableList) {
            db.execSQL(table.createDeletionString());
        }
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List createTableList(){
        List<Table> tableList = new LinkedList<Table>();
        tableList.add(new Group());
        return tableList;
    }

}

