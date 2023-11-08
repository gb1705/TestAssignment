package com.gb.testassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button viewProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"XB1234","ProductVChannel","Product viewed Description", NotificationManager.IMPORTANCE_MAX,true);



        //Button Click to send user profile data and Event data
        viewProductButton=(Button) findViewById(R.id.button_productv);
        viewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //User Profile Data
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", "Gaurav B");
                profileUpdate.put("Email", "gauravbhoyar08@gmail.com");
                clevertapDefaultInstance.onUserLogin(profileUpdate);

                //Event Data
                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Name", "CleverTap");
                prodViewedAction.put("Product Image", "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg");
                prodViewedAction.put("Product ID", 1);
                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);

                Toast.makeText(MainActivity.this,"Data Sent successfully!!",Toast.LENGTH_SHORT).show();

            }
        });




    }


}