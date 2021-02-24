package com.example.universitybazaarsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME =  "loginInfo.db";

    public DatabaseHelper(Context context) {
        super(context, "loginInfo", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table userInfo(userName TEXT, utaID TEXT primary key, utaMail TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists userInfo");

    }

    public boolean insertData(String user_Name, String uta_ID, String uta_Mail, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName", user_Name);
        cv.put("utaID", uta_ID);
        cv.put("utaMail",uta_Mail);
        cv.put("password",password);

        long inserted = db.insert("userInfo",null,cv);

        if(inserted==-1){
            return false;
        } else{
            return true;
        }
    }


    public boolean checkUser(String uta_ID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userInfo where utaID = ?", new String[] {uta_ID});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkCredentials(String utaId,String pass){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userInfo where utaID = ? and password = ?", new String[]{utaId,pass});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }


    }

    public boolean updatePassword(String utaId,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",password);

        long updated = db.update("userInfo",cv,"utaID = ?", new String[]{utaId});

        if(updated==-1){
            return false;
        }else{
            return true;
        }

    }

}
