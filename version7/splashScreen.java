package com.example.android.version7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;            //splash screen stays for time(in milliseconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            public void run() {
                // Creating an Intent to start the homePage activity after SPLASH_DISPLAY_LENGTH time
                Intent mainIntent = new Intent(splashScreen.this,homePage.class);
                splashScreen.this.startActivity(mainIntent);
                splashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
