package com.example.exe_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t=new Thread(){
            public void run() {
                try {
                    sleep(1000);

                    sp = getSharedPreferences("exe_1_sp", 0);

                    String a = sp.getString("email", null);


                    if (a == null) {

                        Intent i = new Intent(MainActivity.this, Login.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent intent = new Intent(MainActivity.this, home.class);
                        startActivity(intent);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
