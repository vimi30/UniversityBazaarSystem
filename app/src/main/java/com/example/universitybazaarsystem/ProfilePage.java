package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {
    Button logoutButton;
    ArrayList<Orders> ordersArrayList = new ArrayList<>();
    TextView tv_viewOrderHistory;
    RecyclerView history;
    OrderHistoryAdapter orderHistoryAdapter;
    //Button editprofile;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);
        String loggedIn = usrLoginInfo.getString("alreadyLoggedIn","");

        if(!loggedIn.equals("true")){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        TextView uname = findViewById(R.id.userName);
        TextView uid = findViewById(R.id.userUtaId);
        TextView umail = findViewById(R.id.userUtaEmail);

        tv_viewOrderHistory = findViewById(R.id.tv_viewOrderHistory);
        history = findViewById(R.id.orderRecyclerView);


        SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);

        uname.setText(usrLoginInfo.getString("userName",""));
        String user = usrLoginInfo.getString("userId","");
        uid.setText(user);
        umail.setText(usrLoginInfo.getString("userEmail",""));

        getOrderHistory(user);



//        editprofile = (Button)findViewById(R.id.Edit);
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfilePage.this, EditProfile.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//            }
//        });


        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        tv_viewOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history.setVisibility(View.VISIBLE);

            }
        });
    }

    private void getOrderHistory(String user) {

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Cursor cursor  = dbHelper.getOrdersByUserId(user);

        while (cursor.moveToNext()){

            String productName = cursor.getString(5);
            

            String productPrice = cursor.getString(7);

            String seller_id = cursor.getString(8);


            Cursor cursor1 = dbHelper.ViewData(seller_id);

            cursor1.moveToLast();

            String seller_name = cursor1.getString(0);

            byte[] productImage = cursor.getBlob(6);

            ordersArrayList.add(new Orders(seller_name,seller_id,null,user,null,productName,productImage,productPrice,null));

        }

        orderHistoryAdapter = new OrderHistoryAdapter(ProfilePage.this,ordersArrayList);
        history.setAdapter(orderHistoryAdapter);
        history.setLayoutManager(new LinearLayoutManager(ProfilePage.this));

    }

    public void logout(){
        SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = usrLoginInfo.edit();
        editor.putString("alreadyLoggedIn","false");
        editor.apply();
        Toast.makeText(ProfilePage.this,"Logging Out",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}