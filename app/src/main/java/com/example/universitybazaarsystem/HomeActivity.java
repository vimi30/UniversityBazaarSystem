 package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class HomeActivity extends AppCompatActivity {
    private Button profileButton;
    private TextView people;
    private TextView community;
    private TextView clubs;
    private TextView buy_sell;
    //Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        people = findViewById(R.id.peopleTab);
        community = findViewById(R.id.comunityTab);
        clubs = findViewById(R.id.clubTab);
        buy_sell = findViewById(R.id.buy_sellTab);

        profileButton = findViewById(R.id.profile);
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
    public void openProfilePage(){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}

//Commit