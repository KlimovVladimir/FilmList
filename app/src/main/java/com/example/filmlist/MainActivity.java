package com.example.filmlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.example.filmlist.list_screen.ListFragment;

public class MainActivity extends AppCompatActivity {

    public static ListFragment listFragment;
    public static FilmFragment filmFragment;
    public static FragmentTransaction fTrans;
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        listFragment = new ListFragment();
        filmFragment = new FilmFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.ListFragment,  MainActivity.listFragment);
        fTrans.commit();

    }
}