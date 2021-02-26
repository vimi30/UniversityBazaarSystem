package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.mbms.StreamingServiceInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordPage extends AppCompatActivity {

    private EditText newPassword;
    private EditText confirmNewPassword;
    //private String user;
    private Button submitNewPasswordButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_page);

        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        submitNewPasswordButton = findViewById(R.id.submitNewPass);

        dbHelper = new DatabaseHelper(this);

        submitNewPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = newPassword.getText().toString();
                String confirmPass = confirmNewPassword.getText().toString();

                Intent intent = getIntent();
                String userId = intent.getStringExtra("userId");

                if(newPass.equals("") || confirmPass.equals("")){
                    Toast.makeText(ResetPasswordPage.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                }else if(newPass.length()<8){

                    Toast.makeText(ResetPasswordPage.this,"Password should be at least 8 character long",Toast.LENGTH_SHORT).show();

                }else if(!newPass.equals(confirmPass)){
                    Toast.makeText(ResetPasswordPage.this,"Passwords does not match!", Toast.LENGTH_SHORT).show();
                }else{

                    boolean updated = dbHelper.updatePassword(userId,newPass);
                    if(updated){
                        Toast.makeText(ResetPasswordPage.this,"Your password has been updated",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ResetPasswordPage.this,MainActivity.class);
                        startActivity(in);
                    }else{
                        Toast.makeText(ResetPasswordPage.this,"Could not update your password. Try Again!",Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });
    }
}