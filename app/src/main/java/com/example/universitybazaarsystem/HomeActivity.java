 package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

 public class HomeActivity extends AppCompatActivity {
    private Button profileButton;
    private CardView people;
    private CardView community;
    private CardView clubs;
    private CardView buy_sell;
    private TextView announcePN,announcePP;

     DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        set_advertisements();


        people = findViewById(R.id.peopleTab);
        community = findViewById(R.id.communityTab);
        clubs = findViewById(R.id.clubTab);
        buy_sell = findViewById(R.id.buy_sellTab);
        announcePN = findViewById(R.id.announceProductName);
        announcePP = findViewById(R.id.announceProductPrice);

        profileButton = findViewById(R.id.profile);
        dbHelper = new DatabaseHelper(this);
        String username = getIntent().getStringExtra("username");

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, ProfilePage.class);
                intent.putExtra("username",username);
                startActivity(intent);


            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PeoplePage.class);
                startActivity(intent);

            }
        });

        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CommunityPage.class);
                startActivity(intent);
            }
        });

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ClubsListingPage.class);
                startActivity(intent);
            }
        });

        buy_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BuySellPage.class);
                startActivity(intent);
            }
        });

    }

     @Override
     protected void onStart() {
         super.onStart();
         Cursor latestRow = dbHelper.getCursorForProductList();
         if(latestRow.getCount() > 0) {
             latestRow.moveToLast();
             announcePN.setText(latestRow.getString(2));

             announcePP.setText("For: " + latestRow.getString(3));
         }

     }
    public void openProfilePage(){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    private void set_advertisements(){

        ImageSlider imageSlider = findViewById(R.id.slidingwindow);

        List<SlideModel> slide_models = new ArrayList<>();
        List<Integer> new_list = new ArrayList<>();
        new_list.add(R.drawable.default_image);
        //new_list.add(R.drawable.pickachu);
        new_list.add(R.drawable.image2);
        new_list.add(R.drawable.image3);
        new_list.add(R.drawable.image4);
        slide_models.add(new SlideModel(new_list.get(0)));
        slide_models.add(new SlideModel(new_list.get(1)));
        slide_models.add(new SlideModel(new_list.get(2)));
        slide_models.add(new SlideModel(new_list.get(3)));

        imageSlider.setImageList(slide_models,true);

    }
}

//Commit