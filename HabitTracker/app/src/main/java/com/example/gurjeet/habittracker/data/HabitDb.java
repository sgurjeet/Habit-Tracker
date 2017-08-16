package com.example.gurjeet.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gurjeet.habittracker.data.HabitContract.HabitEntry;
public class HabitDb extends SQLiteOpenHelper {

    private static final String dbName="habits.db";
    private static final int dbVersion=1;

    public HabitDb(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_TASK + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_DURATION + " INTEGER not null);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //do nothing for now
    }
}
