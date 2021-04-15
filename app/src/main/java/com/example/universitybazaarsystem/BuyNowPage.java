package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BuyNowPage extends AppCompatActivity {
    private EditText Address;
    private EditText PhoneNumber;
    private EditText CardNumber;
    private EditText et_cvv;
    private TextView tv_pname,tv_pprice;
    private ImageView iv_pimage;
    long pid;
    String product_Name,product_Price,buyer,buyerID,sellerID;
    byte[] image;

    DatabaseHelper dbHelper = new DatabaseHelper(BuyNowPage.this);
    Button purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now_page);

        Address = findViewById(R.id.enterAddress);
        PhoneNumber = findViewById(R.id.enterPhoneNumber);
        CardNumber = findViewById(R.id.enterCardNumber);
        purchase = findViewById(R.id.purchase);
        et_cvv = findViewById(R.id.et_cvv);
        tv_pname = findViewById(R.id.buyProductName);
        tv_pprice = findViewById(R.id.buyProductPrice);
        iv_pimage = findViewById(R.id.buyProductImage);

        SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);
        buyer = usrLoginInfo.getString("userName","");
        buyerID = usrLoginInfo.getString("userId","");


        Intent i = getIntent();
        pid = i.getLongExtra("productId",0);

        Cursor c = dbHelper.getProductById(pid);
        System.out.println("cursor Size: "+c.getCount());

        c.moveToLast();

        image = c.getBlob(4);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        iv_pimage.setImageBitmap(bitmap);
        sellerID = c.getString(0);
        product_Name = c.getString(1);
        tv_pname.setText(product_Name);
        product_Price = c.getString(2);
        tv_pprice.setText(product_Price);

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = Address.getText().toString();
                String phone = PhoneNumber.getText().toString();
                String card = CardNumber.getText().toString();
                String cvv = et_cvv.getText().toString();



                if(address.equals("") || phone.equals("") || card.equals("") || cvv.equals("")){
                    Toast.makeText(BuyNowPage.this, "Enter All Details", Toast.LENGTH_LONG).show();
                }
                else if(phone.length() !=10){
                    Toast.makeText(BuyNowPage.this, "Enter 10 digit phone number", Toast.LENGTH_LONG).show();
                }
                else if(card.length() != 16){
                    Toast.makeText(BuyNowPage.this, "Enter 16 digit card number", Toast.LENGTH_LONG).show();
                }else if(cvv.length()<3 || cvv.length()>3){
                    Toast.makeText(BuyNowPage.this,"Enter a valid cvv",Toast.LENGTH_SHORT).show();
                }
                else {

                    boolean inserted = dbHelper.insertOrder(buyer,buyerID,address,phone,String.valueOf(pid),product_Name,image,product_Price,sellerID);

                    if(inserted){

                        Toast.makeText(BuyNowPage.this, "Purchase Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(BuyNowPage.this, HomeActivity.class);
                        startActivity(i);
                    }else{

                        Toast.makeText(BuyNowPage.this,"Could Not Place Your Order, Try Again!",Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });
    }
}