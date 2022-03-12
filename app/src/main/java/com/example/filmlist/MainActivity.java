package com.example.filmlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toolbar;

import com.example.filmlist.items.Film;
import com.example.filmlist.items.Genre;
import com.example.filmlist.items.Header;
import com.example.filmlist.items.Item;
import com.example.filmlist.json.Films;
import com.example.filmlist.json.Message;
import com.example.filmlist.json.MessagesApi;
import com.example.filmlist.list_screen.ListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    public static ListFragment listFragment;
    public static FilmFragment filmFragment;
    public static FragmentTransaction fTrans;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // ActionBar actionBar = getActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(false);
        listFragment = new ListFragment();
        filmFragment = new FilmFragment();

        Runnable runnable = new Runnable() {
            public void run() {
                while (!App.getInstance().initFinish) {SystemClock.sleep(100);}
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.add(R.id.ListFragment, MainActivity.listFragment);
                fTrans.commit();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}