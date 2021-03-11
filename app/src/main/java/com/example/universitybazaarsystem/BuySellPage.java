package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;

public class BuySellPage extends AppCompatActivity {

    private Button postButton;
    RecyclerView viewListOfProducts;
    ArrayList<Product> listOfProducts;
    DatabaseHelper dbHelper;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_page);
        postButton = findViewById(R.id.addPostButton);
        viewListOfProducts = findViewById(R.id.listRecyclerView);
        listOfProducts = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);
        //pla = new ProductListAdapter(this,R.layout.product_items_layout,listOfProducts);
        //gridView.setAdapter(pla);


       try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {

                e.printStackTrace();

        }



        Cursor cursor = dbHelper.getCursorForProductList();

        //listOfProducts.clear();

        while (cursor.moveToNext()){
            String stuId = cursor.getString(0);
            System.out.println(stuId);
            String productName = cursor.getString(1);
            System.out.println(productName);
            String productPrice = cursor.getString(2);
            System.out.println(productPrice);
            String productDescription = cursor.getString(3);
            System.out.println(productDescription);
            byte[] productImage = cursor.getBlob(4);

            listOfProducts.add(new Product(stuId,productName,productPrice,productDescription,productImage));

        }

        customAdapter = new CustomAdapter(BuySellPage.this,listOfProducts);
        viewListOfProducts.setAdapter(customAdapter);
        viewListOfProducts.setLayoutManager(new LinearLayoutManager(BuySellPage.this));




        //pla.notifyDataSetChanged();



        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BuySellPage.this,AddProductActivity.class);
                startActivity(intent);

            }
        });
    }
}