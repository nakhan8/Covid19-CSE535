package com.example.Assign1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class UserInfoDatabase extends SQLiteOpenHelper implements BaseColumns {
    String TAG = "DB";
    MainActivity mainActivity;
    public String respVal = "0";
    public String heartVal = "0";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Harsh.db";
    public static final String TABLE_NAME = "covid_symptoms";
    public static final String COL_1 = "heart_rate";
    public static final String COL_2 = "respiratory_rate";
    public static final String COL_3 = "feeling_tired";
    public static final String COL_4 = "shortness_of_breath";
    public static final String COL_5 = "cough";
    public static final String COL_6 = "loss_of_smell_or_taste";
    public static final String COL_7 = "muscle_ache";
    public static final String COL_8 = "fever";
    public static final String COL_9 = "nausea";
    public static final String COL_10 = "headache";
    public static final String COL_11 = "diarrhea";
    public static final String COL_12 = "soar_throat";


    public UserInfoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        if (db.getPath()!=null) {
            String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+TABLE_NAME+"'";
            try (Cursor cursor = db.rawQuery(query, null)) {
                if(cursor!=null) {
                    if(!(cursor.getCount()>0)) {
                        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, " + COL_1 + " TEXT, " + COL_2 + " TEXT, "
                                + COL_3 + " INT, " + COL_4 + " INT, " + COL_5 + " INT, " + COL_6 + " INT, "
                                + COL_7 + " INT, " + COL_8 + " INT, " + COL_9 + " INT, " + COL_10 + " INT, "
                                + COL_11 + " INT, " + COL_12 + " INT)");
                    }
                }
            }
        }



    }

    public void setRespiratoryRateValue(String respVal){
        this.respVal = respVal;
    }

    public void setHeartRateValue(String heartVal){
        this.heartVal = heartVal;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertData(int[] ratingList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < ratingList.length; i++) {
            Log.i(TAG, "insertData: " + ratingList[i]);

        }
//        Log.i(TAG, "rating list inside insertData: "+ratingList.toString());
        values.put(COL_1, respVal);
        values.put(COL_2, heartVal);
        values.put(COL_3, ratingList[0]);
        values.put(COL_4, ratingList[1]);
        values.put(COL_5, ratingList[2]);
        values.put(COL_6, ratingList[3]);
        values.put(COL_7, ratingList[4]);
        values.put(COL_8, ratingList[5]);
        values.put(COL_9, ratingList[6]);
        values.put(COL_10, ratingList[7]);
        values.put(COL_11, ratingList[8]);
        values.put(COL_12, ratingList[9]);
        Log.i("TAG", values.toString());
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }


}