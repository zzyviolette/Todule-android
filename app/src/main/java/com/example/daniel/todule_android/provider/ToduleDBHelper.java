package com.example.daniel.todule_android.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.daniel.todule_android.provider.ToduleDBContract.TodoEntry;

/**
 * Created by danieL on 7/31/2017.
 */

public class ToduleDBHelper extends SQLiteOpenHelper{
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                    TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TodoEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TodoEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TodoEntry.COLUMN_NAME_CREATED_DATE + " INTEGER," +
                    TodoEntry.COLUMN_NAME_DUE_DATE + " INTEGER," +
                    TodoEntry.COLUMN_NAME_TASK_DONE + " INTEGER DEFAULT 0," +
                    TodoEntry.COLUMN_NAME_COMPLETED_DATE + " INTEGER DEFAULT NULL, " +
                    TodoEntry.COLUMN_NAME_ARCHIVED + " INTEGER DEFAULT 0" +
                    ");";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "Todule.db";

    public ToduleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        if (oldVersion < 6) {
            db.execSQL("ALTER TABLE " + TodoEntry.TABLE_NAME + " ADD COLUMN " + TodoEntry.COLUMN_NAME_COMPLETED_DATE + " INTEGER");
        } else if (oldVersion < 7) {
            db.execSQL("ALTER TABLE " + TodoEntry.TABLE_NAME + " ADD COLUMN " + TodoEntry.COLUMN_NAME_ARCHIVED + " INTEGER DEFAULT 0 ");
        }
        else {
            db.execSQL(SQL_DELETE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES);
        }
    }

}
