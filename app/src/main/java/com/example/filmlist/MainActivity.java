package com.example.filmlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.example.filmlist.items.Film;
import com.example.filmlist.items.Genre;
import com.example.filmlist.items.Header;
import com.example.filmlist.items.Item;
import com.example.filmlist.list_screen.ListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public static ListFragment listFragment;
    public static FilmFragment filmFragment;
    public static FragmentTransaction fTrans;
    private static MainActivity instance;

    public ArrayList <Item> items = new ArrayList();

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        init();
        listFragment = new ListFragment();
        filmFragment = new FilmFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.ListFragment,  MainActivity.listFragment);
        fTrans.commit();

    }

    private void init() {

        for (int i=0; i < 1; i++){
            Header header = new Header("header " + i, 0, i);
            Genre genre = new Genre("genre " + i, 1, i );
            Film film = new Film("film " + i, 2, i);

            items.add(header);
            items.add(genre);
            items.add(genre);
            items.add(header);
            items.add(film);
            items.add(film);
            items.add(film);
            items.add(film);
            items.add(film);
            items.add(film);



            /*  Collections.sort(items, new Comparator<Item>() {
                public int compare(Item o1, Item o2) {

                    if (o1.getID() == o2.getID()) return o1.getItemType() - o2.getItemType();
                    else return (int) (o1.getID() - o2.getID());
                }
            });*/
        }
    }
}