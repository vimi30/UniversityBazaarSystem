package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText registerName;
    private EditText registerUtaid;
    private EditText registerUtaMail;
    private EditText registerPassword;
    private EditText registerConfirmPassword;
    private Button registrationButton;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        registerName = findViewById(R.id.registerName);
        registerUtaid = findViewById(R.id.registerUtaId);
        registerUtaMail  = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.registerConfirmpassword);
        registrationButton = findViewById(R.id.register_Button);
        dbHelper = new DatabaseHelper(this);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = registerName.getText().toString();
                String utaId = registerUtaid.getText().toString();
                String utaMail = registerUtaMail.getText().toString();
                String password = registerPassword.getText().toString();
                String ConfirmPassword = registerConfirmPassword.getText().toString();


                if(Name.equals("") || utaId.equals("") || utaMail.equals("") || password.equals("") || ConfirmPassword.equals("")){
                    Toast.makeText(RegistrationActivity.this,"enter all the details",Toast.LENGTH_LONG).show();
                }else if(!utaId.startsWith("100")){
                    Toast.makeText(RegistrationActivity.this,"Please Enter a valid ID",Toast.LENGTH_SHORT).show();
                }else if(utaId.length()!=10){
                    Toast.makeText(RegistrationActivity.this,"Your UTA ID should contain 10 digits",Toast.LENGTH_SHORT).show();
                }else if(!utaMail.endsWith("@mavs.uta.edu")){

                    Toast.makeText(RegistrationActivity.this,"You must provide your UTA mail Id ",Toast.LENGTH_SHORT).show();

                }else if(password.length()<8){

                    Toast.makeText(RegistrationActivity.this,"Password must be at least 8 character long",Toast.LENGTH_SHORT).show();

                }else if(password.equals(ConfirmPassword)){

                    boolean checkuser = dbHelper.checkUser(utaId);
                    if(!checkuser){
                        boolean insert = dbHelper.insertData(Name,utaId,utaMail,password);
                        if(insert){
                            Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistrationActivity.this,"User already exists",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegistrationActivity.this,"Passwords Does Not Match",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}