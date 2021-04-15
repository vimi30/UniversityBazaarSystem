package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText login_UserName;
    private EditText login_Userpassword;
    private Button login_Button;
    private TextView registrationLink;
    private TextView forgotPasswordLink;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_UserName = findViewById(R.id.login_userId);
        login_Userpassword = findViewById(R.id.login_password);
        login_Button = findViewById(R.id.login_button);
        registrationLink = findViewById(R.id.register_Pagelink);
        forgotPasswordLink = findViewById(R.id.forgotPasswordlink);
        dbHelper = new DatabaseHelper(this);

        SharedPreferences loginStatus = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);

        String alreadyLoggedIn = loginStatus.getString("alreadyLoggedIn","");

        if(alreadyLoggedIn.equals("true")){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }


        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgotPassordPage.class);
                startActivity(intent);

            }
        });


        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUser = login_UserName.getText().toString();
                String loginPass = login_Userpassword.getText().toString();

                if(loginUser.equals("") || loginPass.equals("")){
                    Toast.makeText(MainActivity.this,"Enter all the details",Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkCred  = dbHelper.checkCredentials(loginUser,loginPass);
                    if(checkCred){
                        Toast.makeText(MainActivity.this,"login Successful",Toast.LENGTH_SHORT).show();

                        Cursor cursor = dbHelper.ViewData(loginUser);
                        SharedPreferences usrLoginInfo = getSharedPreferences("loggedInUserInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = usrLoginInfo.edit();
                        if(cursor.moveToNext()){
                            editor.putString("alreadyLoggedIn","true");
                            editor.putString("userName",cursor.getString(0));
                            editor.putString("userId",cursor.getString(1));
                            editor.putString("userEmail",cursor.getString(2));
                            editor.apply();

                        }
                        cursor.close();

                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);


                    }else{
                        Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        registrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterPage();
            }
        });
    }

    public void openRegisterPage(){
        Intent in = new Intent(this,RegistrationActivity.class);
        startActivity(in);
    }
}