package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {
    Button logoutButton;
    //Button editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        DatabaseHelper dbHeplper = new DatabaseHelper(this);
        TextView uname = findViewById(R.id.userName);
        TextView uid = findViewById(R.id.userUtaId);
        TextView umail = findViewById(R.id.userUtaEmail);

        String username = getIntent().getStringExtra("username");

        Cursor cursor = dbHeplper.ViewData(username);


        if(cursor.moveToNext()){
            uname.setText(cursor.getString(0));
            uid.setText(cursor.getString(1));
            umail.setText(cursor.getString(2));


        }
        cursor.close();

//        editprofile = (Button)findViewById(R.id.Edit);
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfilePage.this, EditProfile.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//            }
//        });


        logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    public void logout(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}