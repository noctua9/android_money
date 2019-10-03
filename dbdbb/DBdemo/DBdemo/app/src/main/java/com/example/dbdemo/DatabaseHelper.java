package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE stud (id INTEGER, name TEXT);";
        db.execSQL(sql);

        ContentValues cv=new ContentValues();
        cv.put("id", 10);
        cv.put("name", "raju");

        ContentValues cv1=new ContentValues();
        cv1.put("id", 11);
        cv1.put("name", "aptech");

        db.insert("stud", null , cv);
        db.insert("stud", null , cv1);

        //db.insert(table, nullColumnHack, values)
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
