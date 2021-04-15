package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    private TextView nameOfTheProduct,priceOfTheProduct,descriptionOfTheProject;
    private ImageView imageOfTheProject;
    private Button buyButton;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imageOfTheProject = findViewById(R.id.productPic);
        nameOfTheProduct = findViewById(R.id.nameOfproduct);
        priceOfTheProduct = findViewById(R.id.priceOfproduct);
        descriptionOfTheProject = findViewById(R.id.descriptionOfProduct);
        buyButton = findViewById(R.id.buyButton);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        long productId = intent.getLongExtra("productId",0);
        Cursor c = dbHelper.getProductById(productId);
        System.out.println("cursor Size: "+c.getCount());

        c.moveToLast();

        byte[] image = c.getBlob(4);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageOfTheProject.setImageBitmap(bitmap);
        nameOfTheProduct.setText(c.getString(1));
        priceOfTheProduct.setText(c.getString(2));
        descriptionOfTheProject.setText(c.getString(3));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyNow(productId);
            }
        });



    }
    public void buyNow(long productId){
        Intent i = new Intent(this, BuyNowPage.class);
        i.putExtra("productId",productId);
        startActivity(i);
    }
}