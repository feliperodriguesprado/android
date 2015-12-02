package br.com.smom.smommobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.entities.ReleaseEntity;

public class ReleaseDAOLocal {

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    public ReleaseDAOLocal(Context context) {
        this.context = context;
        this.database = new Database(this.context);
    }

    public void save(ReleaseEntity release) {

        ContentValues values;

        values = new ContentValues();
        values.put("_id", release.getId());
        values.put("type", release.getType());
        values.put("peopleId", release.getPeopleId());
        values.put("accountId", release.getAccountId());
        values.put("accountTypeId", release.getAccountTypeId());
        values.put("dueDate", release.getDueDate().getTime());
        values.put("isPaid", release.isPaid() ? 1 : 0);
        values.put("description", release.getDescription());
        values.put("value", release.getValue());
        db = database.getWritableDatabase();

        if (get(release.getId()) == null) {
            db.insert("releases", null, values);
        } else {
            db.update("releases", values, "_id = " + release.getId(), null);
        }

        db.close();
    }

    public ReleaseEntity get(int id) {

        ReleaseEntity release = null;
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM releases where _id = '" + id + "'", null);

        while (cursor.moveToNext()) {
            release = new ReleaseEntity();
            release.setId(cursor.getInt(0));
            release.setType(cursor.getInt(1));
            release.setPeopleId(cursor.getInt(2));
            release.setAccountId(cursor.getInt(3));
            release.setAccountTypeId(cursor.getInt(4));
            release.setDueDate(new Date(cursor.getLong(5)));
            release.setIsPaid(cursor.getInt(6) == 1);
            release.setDescription(cursor.getString(7));
            release.setValue(cursor.getFloat(8));
        }

        cursor.close();
        return release;
    }

    public List<ReleaseEntity> getListAll() {

        List<ReleaseEntity> releaseList = new ArrayList<>();
        ReleaseEntity release;
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM releases", null);

        while (cursor.moveToNext()) {
            release = new ReleaseEntity();
            release.setId(cursor.getInt(0));
            release.setType(cursor.getInt(1));
            release.setPeopleId(cursor.getInt(2));
            release.setAccountId(cursor.getInt(3));
            release.setAccountTypeId(cursor.getInt(4));
            release.setDueDate(new Date(cursor.getLong(5)));
            release.setIsPaid(cursor.getInt(6) == 1);
            release.setDescription(cursor.getString(7));
            release.setValue(cursor.getFloat(8));
            releaseList.add(release);
        }

        cursor.close();
        return releaseList;
    }

    public List<ReleaseEntity> getListByCustomer(int customerId) {

        List<ReleaseEntity> releaseList = new ArrayList<>();
        ReleaseEntity release;
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM releases where peopleId = '" + customerId + "'", null);

        while (cursor.moveToNext()) {
            release = new ReleaseEntity();
            release.setId(cursor.getInt(0));
            release.setType(cursor.getInt(1));
            release.setPeopleId(cursor.getInt(2));
            release.setAccountId(cursor.getInt(3));
            release.setAccountTypeId(cursor.getInt(4));
            release.setDueDate(new Date(cursor.getLong(5)));
            release.setIsPaid(cursor.getInt(6) == 1);
            release.setDescription(cursor.getString(7));
            release.setValue(cursor.getFloat(8));
            releaseList.add(release);
        }

        cursor.close();
        return releaseList;
    }


}
