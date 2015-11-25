package br.com.smom.smommobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "smom";
    private static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO: terminar criação das tabelas:
        db.execSQL("CREATE TABLE customers (_id INTEGER PRIMARY KEY);");
        db.execSQL("CREATE TABLE financial (_id INTEGER PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
