package com.example.universitybazaarsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClubDetailsJoin extends AppCompatActivity {

    private TextView club_name,club_descp,totalmembers;
    private ImageView club_image;
    private Button join_club;
    private Button leave_club;
    private int count = 0;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        club_image = findViewById(R.id.clubspic);
        club_name = findViewById(R.id.clubsname);
        club_descp = findViewById(R.id.clubdescp);
        totalmembers = findViewById(R.id.totalmembers);
        join_club = findViewById(R.id.joinClub);
        leave_club = findViewById(R.id.leaveClub);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        byte[] img = intent.getByteArrayExtra("club_img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        club_image.setImageBitmap(bitmap);
        club_name.setText(intent.getStringExtra("c_name"));
        club_descp.setText(intent.getStringExtra("c_desc"));
        totalmembers.setText("To join press JOIN CLUB & to leave press LEAVE CLUB");

        join_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                join_club.setEnabled(false);
                totalmembers.setText("You are now member of this club");
                leave_club.setEnabled(true);

            }
        });

        leave_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leave_club.setEnabled(false);
                totalmembers.setText("You are no longer member of this club");
                join_club.setEnabled(true);
            }
        });


    }
}
