package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;

import com.example.diary.fragment.FragmentDiary;
import com.example.diary.fragment.FragmentMine;
import com.example.diary.fragment.FragmentTreehole;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;
    private MainActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myself = this;

        NavigationView view = findViewById(R.id.drawNavi);
        view.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.description_button:{
                    Intent intent = new Intent();
                    intent.setClass(myself, Description.class);
                    startActivity(intent);
                    break;
                }
                case R.id.setting:{
                    Intent intent = new Intent();
                    intent.setClass(myself, Setting.class);
                    startActivity(intent);
                    break;
                }
                case R.id.mine_button:{
                    Intent intent = new Intent();
                    intent.setClass(myself, Dontno.class);
                    startActivity(intent);
                    break;
                }
            }
            return true;
        });




        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,new FragmentDiary());
        transaction.commit();

        bottomNavigationView = findViewById(R.id.buttonNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.diary_button:{
                    transaction.replace(R.id.fragment,new FragmentDiary());
                    transaction.commit();
                    return true;
                }
                case R.id.treehole_button:{
                    transaction.replace(R.id.fragment,new FragmentTreehole());
                    transaction.commit();
                    return true;
                }
                case R.id.mine_button:{
                    transaction.replace(R.id.fragment,new FragmentMine());
                    transaction.commit();
                    return true;
                }
            }
            return false;
        });
    }
}

// TODO: enduring the data of the two Cycleview
// TODO: design the mine layout