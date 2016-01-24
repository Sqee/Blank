package com.example.sqee.blank;

public class TableItem<T> {
    public String itemid;
    public String content;



    public TableItem(){}
    public TableItem(String id, String content) {
        this.itemid = id;
        this.content = content;
    }
    @Override
    public String toString() {
        return content;
    }
}