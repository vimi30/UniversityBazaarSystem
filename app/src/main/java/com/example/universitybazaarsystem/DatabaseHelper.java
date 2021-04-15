package com.example.universitybazaarsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME =  "loginInfo.db";

    public DatabaseHelper(Context context) {
        super(context, "loginInfo", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table userInfo(userName TEXT, utaID TEXT primary key, utaMail TEXT, password TEXT )");
        db.execSQL("create Table sellProductInfo(utaID TEXT, productName TEXT, productPrice TEXT, productDescription TEXT, productImage BLOB )");
        db.execSQL("create Table clubsInfo(utaID TEXT, c_name TEXT, c_desc TEXT, club_img BLOB)");
        db.execSQL("create table discussion_record(utaID TEXT, comment_post TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists userInfo");
        db.execSQL("drop Table if exists sellProductInfo");
        db.execSQL("drop Table if exists clubsInfo ");
        db.execSQL("drop Table if exists discussion_record");

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

    public boolean insertProductData(String uta_ID, String product_name, String product_price, String product_description, byte[] product_image){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("utaID", uta_ID);
        cv.put("productName",product_name);
        cv.put("productPrice",product_price);
        cv.put("productDescription",product_description);
        cv.put("productImage",product_image);


        long inserted = db.insert("sellProductInfo",null,cv);

        return inserted != -1;
    }

    public boolean insertClubData(String uta_ID, String club_name, String club_desc, byte[] club_image){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("utaID", uta_ID);
        cv.put("c_name",club_name);
        cv.put("c_desc",club_desc);
        cv.put("club_img",club_image);

        long inserted = db.insert("clubsInfo",null,cv);

        return inserted != -1;

    }

    public boolean insertComments(String uta_ID, String comment){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("utaId", uta_ID);
        cv.put("comment_post",comment);

        long inserted = db.insert("discussion_record", null,cv);

        return inserted != -1;
    }


    public Cursor getCursorForProductList(){

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from sellProductInfo",null);

    }

    public Cursor getCursorClubs(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from clubsInfo",null);
    }

    public Cursor getCursorComments(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from discussion_record", null);
    }



    public Cursor ViewData(String username) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from userInfo where utaID = ?", new String[]{username});


        return cursor;
    }



}
