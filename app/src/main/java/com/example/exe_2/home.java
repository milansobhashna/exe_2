package com.example.exe_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.List;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    TextView username, email;
    SharedPreferences sp;
    public SqlLiteDbHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new SqlLiteDbHelper(this);
        sp = getSharedPreferences("exe_1_sp", 0);
        String a = sp.getString("email", null);
        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        viewPager = (ViewPager) findViewById(R.id.fmain);
        toolbar = findViewById(R.id.tb1);
        DrawerLayout drawer = findViewById(R.id.hone_drowerlayout);
        NavigationView navigationView = findViewById(R.id.nv1);
        View view = navigationView.getHeaderView(0);

        username = (TextView) view.findViewById(R.id.drawer_header_username);
        email = (TextView) view.findViewById(R.id.drawer_header_email);

        final Usr usr = dbHelper.usrdetail(a);
        username.setText(usr.getUsername());
        email.setText(usr.getEmail());


        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("IMAGE"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        final MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.a) {
            int i = 1;
            viewPager.setCurrentItem(i);
        } else if (id == R.id.b) {
            Intent intent = new Intent(home.this, profile.class);
            startActivity(intent);

        } else if (id == R.id.c) {
            sp.edit().clear().commit();
            Intent intent=new Intent(home.this,Login.class);
            startActivity(intent);
            finish();

        } else {

        }


        DrawerLayout drawer = findViewById(R.id.hone_drowerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.hone_drowerlayout);
        drawer.closeDrawer(GravityCompat.START);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);

        }
        else {
            super.onBackPressed();
        }


    }
}
