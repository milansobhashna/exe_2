package com.example.exe_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class signup extends AppCompatActivity {

    public SqlLiteDbHelper dbHelper = null;
    EditText signup_username, signup_email, signup_pwd;
    Button btn_signup;
    Usr usr = new Usr();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = new SqlLiteDbHelper(this);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_username = findViewById(R.id.signup_username);
                signup_email = findViewById(R.id.signup_email);
                signup_pwd = findViewById(R.id.signup_pwd);

                final String e1 = signup_email.getText().toString();
                final String epattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                Intent intent = new Intent(signup.this, Login.class);
                String chkemail = "", chkusername = "";
                List<Usr> usrs = dbHelper.getAllContacts();

                for (Usr emailchk : usrs) {
                    if (signup_email.getText().toString().equals(emailchk.getEmail())) {
                        chkemail = emailchk.getEmail();
                    }
                    if (signup_username.getText().toString().equals(emailchk.getUsername())) {
                        chkusername = emailchk.getUsername();
                    }

                }

                if (signup_username.getText().toString().length() > 0) {
                    if (signup_username.getText().toString().equals(chkusername)) {
                        signup_username.requestFocus();
                        signup_username.setError("username already taken");
                    } else {
                        if (signup_email.getText().toString().length() > 0) {

                            if (e1.matches(epattern)) {

                                if (signup_email.getText().toString().equals(chkemail)) {

                                    signup_email.requestFocus();
                                    signup_email.setError("email already taken");
                                } else {
                                    if (signup_pwd.getText().toString().length() > 0) {

                                        if (signup_pwd.getText().toString().length() == 8) {
                                            String username = signup_username.getText().toString();
                                            String email = signup_email.getText().toString();
                                            String pwd = signup_pwd.getText().toString();
                                            usr.setUsername(username);
                                            usr.setEmail(email);
                                            usr.setPwd(pwd);
                                            dbHelper.adduserdetail(usr);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            signup_pwd.requestFocus();
                                            signup_pwd.setError("enter 8 character password");
                                        }
                                    } else {
                                        signup_pwd.requestFocus();
                                        signup_pwd.setError("enter password");
                                    }
                                }

                            } else {
                                signup_email.requestFocus();
                                signup_email.setError("enter valid email");
                            }
                        } else {
                            signup_email.requestFocus();
                            signup_email.setError("enter email");
                        }
                    }

                } else {
                    signup_username.requestFocus();
                    signup_username.setError("enter username");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(signup.this,Login.class);
        startActivity(intent);
        finish();
    }
}
