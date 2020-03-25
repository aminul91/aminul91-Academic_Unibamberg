package com.example.cowcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Imtiaz on 15.11.2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "cow.db";
    public static final String TABLE_NAME = "cowTable";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (id INTEGER, breed INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(CowDTO cowDTO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", cowDTO.getId());
        contentValues.put("breed", cowDTO.getBreed());
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public boolean alreadyExist(CowDTO cowDTO) {
        String[] args = { cowDTO.getId() + "", cowDTO.getBreed() + "" };
        long rowCount = DatabaseUtils.queryNumEntries(this.getWritableDatabase(), TABLE_NAME, "id = ? AND breed = ?", args);

        return rowCount > 0;
    }

    public Cursor getAllCows() {return this.getWritableDatabase().rawQuery("select * from " + TABLE_NAME, null);}

    public int removeAllCows() {
        return this.getWritableDatabase().delete(TABLE_NAME, "1", null);
    }
}
