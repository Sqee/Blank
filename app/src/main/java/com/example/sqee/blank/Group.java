package com.example.sqee.blank;

import android.provider.BaseColumns;
import java.util.LinkedList;

public final class Group extends Table implements BaseColumns {
        public Group() {
            setName("Main");
            setColumns(new LinkedList<TableColumn>());
            getTableColumns().add(new TableColumn("id", "Integer_primary_key"));
            getTableColumns().add(new TableColumn("personid", "Char"));
            getTableColumns().add(new TableColumn("name","Char"));
        }
    }



