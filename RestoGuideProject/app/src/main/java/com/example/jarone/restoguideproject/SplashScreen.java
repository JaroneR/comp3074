package com.example.jarone.restoguideproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();

        logoLauncher.start();
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);

            startActivity(intent);

            SplashScreen.this.finish();
        }
    }
}
