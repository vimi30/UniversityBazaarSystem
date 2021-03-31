package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ClubsPage extends AppCompatActivity {

    private ImageView clubImage;
    private TextView clubName, clubDescription;
    private Button createclubButton;
    final int galleryPick = 101;
    Uri imgUri;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club);

        clubName = findViewById(R.id.enterClubName);
        clubDescription = findViewById(R.id.clubDescription);
        clubImage = findViewById(R.id.clubImage);
        createclubButton = findViewById(R.id.createButton);
        dbHelper = new DatabaseHelper(this);

        clubImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        createclubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = "1001000000";
                String club_name = clubName.getText().toString();
                String club_descp = clubDescription.getText().toString();

                if(club_name == "" || club_descp == ""){
                    Toast.makeText(ClubsPage.this,"Please fill the details",Toast.LENGTH_SHORT).show();
                }else{
                    if(dbHelper.insertClubData(studentId,club_name,club_descp,imageViewToByte(clubImage))){
                        Toast.makeText(ClubsPage.this,"Created",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ClubsPage.this, ClubsListingPage.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(ClubsPage.this,"Failed! Try again",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, galleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == galleryPick){
            imgUri = data.getData();
            clubImage.setImageURI(imgUri);
        }
    }
}