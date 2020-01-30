package com.devildart.herokutestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity {

    private MainFrag container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        container = new MainFrag();
        transaction.add(R.id.container, container);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (container.onBackPress()) super.onBackPressed();
    }
}
