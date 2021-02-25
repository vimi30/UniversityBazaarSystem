package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassordPage extends AppCompatActivity {


    private EditText forgottenUtaId;
    private Button  submitButton;
    DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passord_page);

        forgottenUtaId = findViewById(R.id.forgotUTAId);
        submitButton = findViewById(R.id.submitbutton);
        dbHelper = new DatabaseHelper(this);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgottenUTAID = forgottenUtaId.getText().toString();

                if(forgottenUTAID.equals("")){
                    Toast.makeText(ForgotPassordPage.this,"Please Enter your UTA ID",Toast.LENGTH_SHORT).show();
                }else{

                    boolean userFound = dbHelper.checkUser(forgottenUTAID);
                    if(userFound){
                        Intent intent = new Intent(ForgotPassordPage.this,ResetPasswordPage.class);
                        intent.putExtra("userId",forgottenUTAID);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ForgotPassordPage.this,"User does not exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}