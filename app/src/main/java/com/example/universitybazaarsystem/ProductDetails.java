package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    private TextView nameOfTheProduct,priceOfTheProduct,descriptionOfTheProject;
    private ImageView imageOfTheProject;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imageOfTheProject = findViewById(R.id.productPic);
        nameOfTheProduct = findViewById(R.id.nameOfproduct);
        priceOfTheProduct = findViewById(R.id.priceOfproduct);
        descriptionOfTheProject = findViewById(R.id.descriptionOfProduct);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();

        byte[] image = intent.getByteArrayExtra("productImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageOfTheProject.setImageBitmap(bitmap);
        nameOfTheProduct.setText(intent.getStringExtra("productName"));
        priceOfTheProduct.setText(intent.getStringExtra("productPrice"));
        descriptionOfTheProject.setText(intent.getStringExtra("productDescription"));




    }
}