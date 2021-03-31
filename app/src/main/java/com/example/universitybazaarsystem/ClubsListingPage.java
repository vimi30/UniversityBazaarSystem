package com.example.universitybazaarsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ClubsListingPage extends AppCompatActivity implements CustomAdapterClubs.OnProductListenerClubs {

    private Button createClub;
    RecyclerView viewClubList;
    ArrayList<Clubs> club_list;
    CustomAdapterClubs customAdapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);
        createClub = findViewById(R.id.createClubButton);

        viewClubList = findViewById(R.id.recyclerView);
        club_list = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        createClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClubsListingPage.this,
                        ClubsPage.class);
                startActivity(intent);

            }
        });

        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {

            e.printStackTrace();

        }

        Cursor cursor = dbHelper.getCursorClubs();
        while(cursor.moveToNext()){
            String s_id = cursor.getString(0);
            String c_name = cursor.getString(1);
            String c_desc = cursor.getString(2);
            byte[] club_img = cursor.getBlob(3);

            club_list.add(new Clubs(s_id,c_name,c_desc,club_img));
        }

        customAdapter = new CustomAdapterClubs(ClubsListingPage.this,club_list,this);
        viewClubList.setAdapter(customAdapter);
        viewClubList.setLayoutManager(new LinearLayoutManager(ClubsListingPage.this));



    }

    @Override
    public void onProductClick(int position) {
        club_list.get(position);
        Intent intent = new Intent(ClubsListingPage.this,ClubDetailsJoin.class);
        intent.putExtra("c_name",club_list.get(position).getC_name());
        intent.putExtra("c_desc",club_list.get(position).getC_desc());
        intent.putExtra("club_img",club_list.get(position).getClub_img());
        startActivity(intent);

    }


}