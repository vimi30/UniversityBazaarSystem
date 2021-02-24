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
    private EditText user;
    private Button submitNewPasswordButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_page);

        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        submitNewPasswordButton = findViewById(R.id.submitNewPass);
        user = findViewById(R.id.forgotUTAId);
        dbHelper = new DatabaseHelper(this);

        submitNewPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = newPassword.getText().toString();
                String confirmPass = confirmNewPassword.getText().toString();
                String userId = user.getText().toString();

                if(newPass.equals("") || confirmPass.equals("")){
                    Toast.makeText(ResetPasswordPage.this,"Please fill all the details",Toast.LENGTH_SHORT);
                }else if(!newPass.equals(confirmPass)){
                    Toast.makeText(ResetPasswordPage.this,"Passwords does not match!", Toast.LENGTH_SHORT);
                }else{

                    boolean updated = dbHelper.updatePassword(userId,newPass);
                    if(updated){
                        Toast.makeText(ResetPasswordPage.this,"Your password has been updated",Toast.LENGTH_SHORT);
                        Intent intent = new Intent(ResetPasswordPage.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ResetPasswordPage.this,"Could not update your password. Try Again!",Toast.LENGTH_SHORT);
                    }



                }
            }
        });
    }
}