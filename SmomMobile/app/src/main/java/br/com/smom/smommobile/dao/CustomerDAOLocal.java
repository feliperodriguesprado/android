package br.com.smom.smommobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.entities.CustomerEntity;

public class CustomerDAOLocal {

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    public CustomerDAOLocal(Context context) {
        this.context = context;
        this.database = new Database(this.context);
    }

    public void save(CustomerEntity customer) {

        ContentValues values;

        values = new ContentValues();
        values.put("_id", customer.getId());
        values.put("name", customer.getName());
        values.put("cpfCnpj", customer.getCpfCnpj());
        db = database.getWritableDatabase();

        if (get(customer.getId()) == null) {
            db.insert("customers", null, values);
        } else {
            db.update("customers", values, "_id = " + customer.getId(), null);
        }

        db.close();
    }

    public CustomerEntity get(int id) {

        CustomerEntity customer = null;
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM customers where _id = '" + id + "'", null);

        while (cursor.moveToNext()) {
            customer = new CustomerEntity();
            customer.setId(cursor.getInt(0));
            customer.setName(cursor.getString(1));
            customer.setCpfCnpj(cursor.getString(2));
        }

        cursor.close();
        return customer;
    }

    public List<CustomerEntity> getListAll() {

        List<CustomerEntity> customerList = new ArrayList<>();
        CustomerEntity customer;
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM customers", null);

        while (cursor.moveToNext()) {
            customer = new CustomerEntity();
            customer.setId(cursor.getInt(0));
            customer.setName(cursor.getString(1));
            customer.setCpfCnpj(cursor.getString(2));
            customerList.add(customer);
        }

        cursor.close();
        return customerList;
    }

}
