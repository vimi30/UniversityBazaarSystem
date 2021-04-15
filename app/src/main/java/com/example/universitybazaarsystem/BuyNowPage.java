package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyNowPage extends AppCompatActivity {
    private EditText Address;
    private EditText PhoneNumber;
    private EditText CardNumber;
    Button purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now_page);

        Address = findViewById(R.id.enterAddress);
        PhoneNumber = findViewById(R.id.enterPhoneNumber);
        CardNumber = findViewById(R.id.enterCardNumber);
        purchase = findViewById(R.id.purchase);

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = Address.getText().toString();
                String phone = PhoneNumber.getText().toString();
                String card = CardNumber.getText().toString();

                if(address.equals("") || phone.equals("") || card.equals("")){
                    Toast.makeText(BuyNowPage.this, "Enter All Details", Toast.LENGTH_LONG).show();
                }
                else if(phone.length() !=10){
                    Toast.makeText(BuyNowPage.this, "Enter 10 digit phone number", Toast.LENGTH_LONG).show();
                }
                else if(card.length() != 16){
                    Toast.makeText(BuyNowPage.this, "Enter 16 digit card number", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(BuyNowPage.this, "Purchase Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(BuyNowPage.this, HomeActivity.class);
                    startActivity(i);

                }
            }
        });
    }
}