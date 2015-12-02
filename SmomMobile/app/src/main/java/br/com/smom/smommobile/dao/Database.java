package br.com.smom.smommobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "smom";
    private static int VERSION = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE customers (" +
                "_id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "cpfCnpj TEXT);");

        db.execSQL("CREATE TABLE releases (" +
                "_id INTEGER PRIMARY KEY, " +
                "type INTEGER, " +
                "peopleId INTEGER, " +
                "accountId INTEGER, " +
                "accountTypeId INTEGER, " +
                "dueDate INTEGER, " +
                "isPaid INTEGER, " +
                "description TEXT, " +
                "value REAL, " +
                "FOREIGN KEY (peopleId) REFERENCES customers (_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
