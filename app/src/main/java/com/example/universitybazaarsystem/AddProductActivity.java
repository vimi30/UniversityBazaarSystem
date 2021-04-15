package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription;
    private Button uploadButton;
    final int galleryPick = 101;
    Uri imageUri;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.enterName);
        productDescription = findViewById(R.id.productDescription);
        productImage = findViewById(R.id.product_image);
        uploadButton = findViewById(R.id.addPostButton);
        dbHelper = new DatabaseHelper(this);


        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();

            }

        });



        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);

                String studentId = usrLoginInfo.getString("userId","");
                String pName = productName.getText().toString();
                String pPrice = productPrice.getText().toString();
                String pDescription = productDescription.getText().toString();

                if(pName.equals("") || pPrice.equals("") || pDescription.equals("")){
                    Toast.makeText(AddProductActivity.this,"Please Enter All the details",Toast.LENGTH_SHORT).show();
                }else{

                    boolean insert = dbHelper.insertProductData(studentId,pName,pPrice,pDescription,imageViewToByte(productImage));

                    if(insert){

                        Toast.makeText(AddProductActivity.this,"Product Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddProductActivity.this, BuySellPage.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(AddProductActivity.this,"Upload failed!!  Please try again", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, galleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == galleryPick){
            imageUri = data.getData();
            productImage.setImageURI(imageUri);
        }
    }
}