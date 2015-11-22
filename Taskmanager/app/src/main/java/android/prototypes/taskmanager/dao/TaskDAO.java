package android.prototypes.taskmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.prototypes.taskmanager.entities.TaskEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public void setContext(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void create(TaskEntity task) {
        ContentValues values = new ContentValues();
        values.put("_id", task.getId());
        values.put("due_date", task.getDueDate().getTime());
        values.put("title", task.getTitle());
        values.put("description", task.getDescription());
        values.put("locality", task.getLocality());

        db = databaseHelper.getWritableDatabase();
        long result = db.insert("tasks", null, values);
        System.out.println(Long.toString(result));
    }

    public List<TaskEntity> getAll() {

        List<TaskEntity> taskList = new ArrayList<>();
        TaskEntity task;
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks", null);

        while (cursor.moveToNext()) {
            task = new TaskEntity();
            task.setId(cursor.getInt(0));
            task.setDueDate(new Date(cursor.getLong(1)));
            task.setTitle(cursor.getString(2));
            task.setDescription(cursor.getString(3));

            taskList.add(task);
        }

        cursor.close();
        return taskList;
    }

}
