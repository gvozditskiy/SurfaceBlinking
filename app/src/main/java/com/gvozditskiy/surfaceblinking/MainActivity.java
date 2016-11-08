package com.gvozditskiy.surfaceblinking;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = new MainFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment).commit();
    }
}
