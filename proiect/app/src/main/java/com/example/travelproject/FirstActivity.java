package com.example.travelproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_europe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Europe()).commit();
                break;
            case R.id.nav_asia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Asia()).commit();
                break;
            case R.id.nav_america:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new America()).commit();
                break;
            case R.id.nav_africa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Africa()).commit();
                break;
            case R.id.nav_australia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Australia()).commit();
                break;
            case R.id.nav_antarctica:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Antarctica()).commit();
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Favorites()).commit();
                break;
            case R.id.nav_flight:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Flights()).commit();
                break;

            case R.id.nav_mail:
                Toast.makeText(this,"Send us a mail at: info@dianastravel.com", Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}
