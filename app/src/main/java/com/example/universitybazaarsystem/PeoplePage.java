package com.example.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PeoplePage extends AppCompatActivity {

    private EditText query_text;
    private Button post_query_button;
    private ListView myListView;
    RecyclerView viewComments_list;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    DatabaseHelper dbHelper;
    Context context;
    List<String> display_list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_page);


        this.context = getApplicationContext();
        post_query_button = findViewById(R.id.post_query);
        query_text = findViewById(R.id.discussion__query);
        myListView = (ListView)findViewById(R.id.rView);
        display_list = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);


        post_query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = "1111111111";
                String comment = query_text.getText().toString();
                query_text.setText("");
//                display_list.add(comment);
//                String[] arr = new String[display_list.size()];
//                display_list.toArray(arr);

                String[] ran = new String[]{"Rajat","Arpan","qwerty"};
                boolean insert = dbHelper.insertComments(studentId, comment);
                if(insert){
                    display_list.add(comment);


                    ListAdapter ladap = new ArrayAdapter<String>(PeoplePage.this,android.R.layout.simple_list_item_1, display_list);
                    myListView.setAdapter(ladap);


                }


//                ListAdapter myAdapter = new PeoplesAdapter(context, ran);
//                myListView.setAdapter(myAdapter);
//                ListAdapter ladap = new ArrayAdapter<String>(PeoplePage.this,android.R.layout.simple_list_item_1, arr);
//                myListView.setAdapter(ladap);




            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor latestComment = dbHelper.getCursorComments();
        while(latestComment.moveToNext() ){
            display_list.add(latestComment.getString(1));
        }
        ListAdapter ladap = new ArrayAdapter<String>(PeoplePage.this,android.R.layout.simple_list_item_1, display_list);
        myListView.setAdapter(ladap);

    }
}