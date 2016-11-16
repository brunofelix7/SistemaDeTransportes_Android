package br.com.sisbus.sisbusapp;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.Permissions;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splashScreen = new Thread(){

            @Override
            public void run(){
                try{
                    super.run();
                    sleep(3000);    //  3 segundos
                }catch(Exception e){
                    Log.e("Splash", "ALGO DEU ERRADO!!!");
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        splashScreen.start();

    }
}
