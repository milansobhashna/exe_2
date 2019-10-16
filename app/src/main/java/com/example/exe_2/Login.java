package com.example.exe_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Login extends AppCompatActivity {

    Button btn_login, btn_signup;
    EditText login_email, login_pwd;
    SharedPreferences sp;
    public SqlLiteDbHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_pwd = findViewById(R.id.login_pwd);

        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        dbHelper = new SqlLiteDbHelper(this);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login_email.getText().toString().length() > 0) {

                    if (login_pwd.getText().toString().length() > 0) {

                        if (login_pwd.getText().toString().length() == 8) {

                            String email = "", pwd = "";
                            List<Usr> usrs = dbHelper.getAllContacts();
                            for (Usr emailchk : usrs) {
                                if (login_email.getText().toString().equals(emailchk.getEmail())) {
                                    email = emailchk.getEmail();
                                }
                                if (login_pwd.getText().toString().equals(emailchk.getPwd())) {
                                    pwd = emailchk.getPwd();
                                }

                            }
                            if (login_email.getText().toString().equals(email)) {

                                if (login_pwd.getText().toString().equals(pwd)) {

                                    sp = getSharedPreferences("exe_1_sp", 0);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("email", login_email.getText().toString());
                                    editor.commit();

                                    Intent intent = new Intent(Login.this, home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    login_pwd.requestFocus();
                                    login_pwd.setError("invalid password");
                                }
                            } else {
                                login_email.requestFocus();
                                login_email.setError("email not exist");
                            }
                        } else {
                            login_pwd.requestFocus();
                            login_pwd.setError("enter 8 charactor password");
                        }
                    } else {
                        login_pwd.requestFocus();
                        login_pwd.setError("enter password");
                    }
                } else {
                    login_email.requestFocus();
                    login_email.setError("enter email");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
