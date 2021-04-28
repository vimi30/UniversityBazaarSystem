package com.example.universitybazaarsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("create Table orders(buyerName TEXT, buyerId TEXT, buyerAddress TEXT, buyerPhoneNumber TEXT ,productId TEXT, productName TEXT, productImage BLOB, productPrice TEXT, sellerId TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists userInfo");
        db.execSQL("drop Table if exists sellProductInfo");
        db.execSQL("drop Table if exists clubsInfo ");
        db.execSQL("drop Table if exists discussion_record");
        db.execSQL("drop table if exists orders");

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

    public Cursor getCursorComments(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from discussion_record", null);
    }



    public List<String> getProductNamesList(){

        List<String> listOfProductNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {

            e.printStackTrace();

        }
        Cursor cursor = db.query("sellProductInfo", new String[]{"rowid","*"},null,null,null,null,null);
//        mySQLiteHelper.getReadableDatabase().query(sellProductInfo, new String[] { "ROWID", "*" }, where, null, null, null, null);
//        return db.rawQuery("select * from sellProductInfo",null);

        if(cursor.moveToFirst()){

            do{
                listOfProductNames.add(cursor.getString(cursor.getColumnIndex("productName")));
            }while (cursor.moveToNext());

        }

        return listOfProductNames;

    }

    public Cursor getCursorForProductList(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("sellProductInfo", new String[]{"rowid","*"},null,null,null,null,null);
//        mySQLiteHelper.getReadableDatabase().query(sellProductInfo, new String[] { "ROWID", "*" }, where, null, null, null, null);
//        return db.rawQuery("select * from sellProductInfo",null);

        return cursor;

    }

    public Cursor getProductById(Long productId){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM sellProductInfo WHERE ROWID=?",new String[]{String.valueOf(productId)});
        return cursor;

    }

//    public Cursor getLatestProduct(){
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor lastRow =  db.rawQuery("SELECT * FROM sellProductInfo WHERE ROWID=(SELECT max(ROWID) FROM sellProductInfo)",null);
//        lastRow.moveToLast();
//        System.out.println(lastRow.getCount());
//        System.out.println(lastRow.getString(1));
//        System.out.println(lastRow.getString(2));
//
//        return lastRow;
//
//    }

    public Cursor getCursorClubs(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from clubsInfo",null);
    }



    public Cursor ViewData(String username) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from userInfo where utaID = ?", new String[]{username});


        return cursor;
    }

    public boolean insertOrder(String buyer_name, String buyer_id, String buyer_address, String buyer_phone_number ,String product_id, String product_name,byte[] product_image, String product_price, String seller_id){

//        "buyerName TEXT, buyerId TEXT, buyerAddress TEXT, productId TEXT, productName TEXT, productImage BLOB, productPrice TEXT, sellerId TEXT, sellerName TEXT"
//buyerPhoneNumber
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("buyerName", buyer_name);
        cv.put("buyerId", buyer_id);
        cv.put("buyerAddress", buyer_address);
        cv.put("buyerPhoneNumber", buyer_phone_number);
        cv.put("productId", product_id);
        cv.put("productName", product_name);
        cv.put("productPrice", product_price);
        cv.put("productImage", product_image);
        cv.put("sellerId", seller_id);

        long inserted = db.insert("orders",null,cv);

        if(inserted==-1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor getOrdersByUserId(String userID){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from orders where buyerId = ?", new String[]{userID});

        return cursor;


    }

    public List<Product> getSearchResult(String name){

        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("sellProductInfo", new String[]{"rowid","*"},"productName LIKE ?",new String[]{name},null,null,null);

        if(cursor.moveToFirst()){

            do{

                products.add(new Product(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getLong(0),cursor.getBlob(5)));

            }while (cursor.moveToNext());

        }


        return products;


    }



}
