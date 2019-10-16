package com.example.exe_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class profile extends AppCompatActivity {

    public SqlLiteDbHelper dbHelper = null;
    Button edit_update;
    EditText profile_username, profile_email, profile_pwd;
    SharedPreferences sp;

    String username = "", email = "", pwd = "";
    String checkemail = "", checkusername = "";
    String loadusername = "", loademail = "", loadpwd = "";
    String finalusername = "", finalemail = "", finalpwd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edit_update = findViewById(R.id.edit_update);
        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        profile_pwd = findViewById(R.id.profile_pwd);

        dbHelper = new SqlLiteDbHelper(this);

        sp = getSharedPreferences("exe_1_sp", 0);
        final String useremail = sp.getString("email", null);

        final Usr usr = dbHelper.usrdetail(useremail);

        profile_username.setText(usr.getUsername());
        loadusername = profile_username.getText().toString();
        profile_email.setText(usr.getEmail());
        loademail = profile_email.getText().toString();
        profile_pwd.setText(usr.getPwd());
        loadpwd = profile_pwd.getText().toString();

        final List<Usr> usrs = dbHelper.getAllContacts();
        for (Usr emailchk : usrs) {
            if (profile_email.getText().toString().equals(emailchk.getEmail())) {
                checkemail = emailchk.getEmail();
            }
            if (profile_username.getText().toString().equals(emailchk.getUsername())) {
                checkusername = emailchk.getUsername();
            }

        }
        edit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = "edit";
                String update = "update";
                final String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (edit_update.getText().toString().equals(edit)) {
                    profile_username.setEnabled(true);
                    profile_email.setEnabled(true);
                    profile_pwd.setEnabled(true);
                    profile_email.setSingleLine(true);
                    edit_update.setText("update");
                } else if (edit_update.getText().toString().equals(update)) {
                    username = profile_username.getText().toString();
                    email = profile_email.getText().toString();
                    pwd = profile_pwd.getText().toString();

                    if (username.length() > 0) {
                        for (Usr emailchk : usrs) {
                            if (username.equals(emailchk.getUsername())) {
                                checkusername = emailchk.getUsername();
                            }
                        }
                        if (loadusername.equals(username)) {
                            profile_username.setText(username);
                            finalusername = username;
                        } else if (username.equals(checkusername)) {
                            profile_username.requestFocus();
                            profile_username.setError("user already taken");

                            finalusername = null;
                        } else {
                            profile_username.setText(username);
                            finalusername = username;
                        }
                    } else {
                        profile_username.requestFocus();
                        profile_username.setError("enter username");
                        finalusername = null;
                    }

                    if (email.length() > 0) {
                        for (Usr emailchk : usrs) {
                            if (email.equals(emailchk.getEmail())) {
                                checkemail = emailchk.getEmail();
                            }
                        }
                        if (loademail.equals(email)) {
                            profile_email.setText(email);
                            finalemail = email;
                        } else if (email.equals(checkemail)) {
                            profile_email.requestFocus();
                            profile_email.setError("user already taken");
                            finalemail = null;
                        } else {
                            if (email.matches(pattern)) {
                                profile_email.setText(email);
                                finalemail = email;
                            } else {
                                profile_email.requestFocus();
                                profile_email.setError("invalid email");
                                finalemail = null;
                            }
                        }
                    } else {
                        profile_email.requestFocus();
                        profile_email.setError("enter username");
                        finalemail = null;
                    }

                    if (pwd.length() > 0) {
                        if (pwd.length() == 8) {
                            profile_pwd.setText(pwd);
                            finalpwd = pwd;
                        } else {
                            profile_pwd.requestFocus();
                            profile_pwd.setError("enter 8 character password");
                        }
                    } else {
                        profile_pwd.requestFocus();
                        profile_pwd.setError("enter password");
                    }

                    if (finalusername != null) {

                        if (finalemail != null) {

                            if (finalpwd != null) {

                                sp = getSharedPreferences("exe_1_sp", 0);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("email", profile_email.getText().toString());
                                editor.commit();

                                Usr usr1 = new Usr();
                                usr1.setId(usr.getId());
                                usr1.setUsername(finalusername);
                                usr1.setEmail(finalemail);
                                usr1.setPwd(finalpwd);
                                dbHelper.updateuser(usr1);

                                profile_username.setEnabled(false);
                                profile_email.setEnabled(false);
                                profile_pwd.setEnabled(false);
                                profile_email.setSingleLine(false);
                                edit_update.setText("edit");

                                Intent intent=new Intent(profile.this,home.class);
                                startActivity(intent);

                            }
                        }
                    }



                } else {
                    // the else is edit and update
                }
            }
        });
    }
}
