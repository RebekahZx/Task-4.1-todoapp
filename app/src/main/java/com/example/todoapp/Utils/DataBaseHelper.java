package com.example.todoapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.todoapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "TODO_DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TASK";
    private static final String COL_3 = "STATUS";
    private static final String COL_4 = "DESCRIPTION";
    private static final String COL_5 = "DUE_DATE";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_2 + " TEXT, "
                + COL_3 + " INTEGER, "
                + COL_4 + " TEXT, "
                + COL_5 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Corrected insertTask method
    public void insertTask(ToDoModel model) {
        SQLiteDatabase db = this.getWritableDatabase();  // Get writable database here
        if (db == null) {
            Log.e("DatabaseHelper", "Database is null");
            return;  // Early return if database is null
        }
        ContentValues values = new ContentValues();
        values.put(COL_2, model.getTask());
        values.put(COL_3, 0); // Default status is not completed
        values.put(COL_4, model.getDescription());
        values.put(COL_5, model.getDueDate());

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert task");
        } else {
            Log.d("DatabaseHelper", "Task inserted successfully");
        }
        db.close();  // Always close the database after performing operations
    }

    public void updateTask(int id, String task, String description, String dueDate) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, task);
        values.put(COL_4, description);
        values.put(COL_5, dueDate);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateStatus(int id, int status) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, status);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});
    }

    public List<ToDoModel> getAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();  // Initialize db here
        Cursor cursor = null;
        List<ToDoModel> modelList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_5 + " DESC"); // Sorted by due date
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ToDoModel task = new ToDoModel();
                    task.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                    task.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
                    task.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
                    task.setDescription(cursor.getString(cursor.getColumnIndex(COL_4)));
                    task.setDueDate(cursor.getString(cursor.getColumnIndex(COL_5)));
                    modelList.add(task);
                } while (cursor.moveToNext());
            }
        } finally {
            db.endTransaction();
            if (cursor != null) cursor.close();
        }
        return modelList;
    }
}
