package com.example.universitybazaarsystem;

import android.os.Bundle;
import android.os.Message;
//import javax.mail.Session;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class CommunityPage extends AppCompatActivity {
    private TextView sendmail, message;
   private Button sendemailbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);

        sendmail = findViewById(R.id.sendmail);
        message = findViewById(R.id.message);
        sendemailbtn = findViewById(R.id.emailbutton);

        sendemailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /////enter you email id in username
                ///enter your password
                final String username = "rajatchaudhary2306@gmail.com";
                final String password = "heavyboozeflow123@#";

                String mail_id = sendmail.getText().toString();
                String messageCnt = message.getText().toString();

                String[] myArray = mail_id.split(",");
                List<String> myList = Arrays.asList(myArray);
                for(int i=0; i<myList.size(); i++) {

                    if (myList.get(i).equals("") || messageCnt.equals("")) {
                        Toast.makeText(CommunityPage.this, "Complete the empty fields", Toast.LENGTH_LONG).show();
                    } else {
                Properties prop = new Properties();
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host","smtp.gmail.com");
                prop.put("mail.smtp.port","587");

                Authenticator auth = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                };

                Session session;
                session = Session.getInstance(prop, auth);


                try{
                    Transport transport = session.getTransport();
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(myList.get(i)));
                    msg.setSubject("Received from UnivBazSys");
                    MimeBodyPart msgBody = new MimeBodyPart();
                    msgBody.setContent(message, messageCnt);
                    msg.setText(messageCnt);
                    transport.connect();
                    Transport.send(msg);
                    transport.close();
                    Toast.makeText(getApplicationContext(),"E-mail sent successfully",Toast.LENGTH_LONG).show();

                }catch(MessagingException e){
                    throw new RuntimeException(e);
                }
                    }
                }

            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
}