package com.example.filmlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

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

    private static final String TAG = "DEBUGGG";

    public static ListFragment listFragment;
    public static FilmFragment filmFragment;
    public static FragmentTransaction fTrans;
    private static MainActivity instance;
    public Retrofit retrofit;
    public MessagesApi messagesApi;
    public Call<Films> messages;

    boolean loading = false;

    public ArrayList <Item> items = new ArrayList();
    public ArrayList <Message> response_json = new ArrayList();

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messagesApi = retrofit.create(MessagesApi.class);
        messages = messagesApi.messages();

        messages.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                //response_json.addAll(response.body());
                //response.body().getFilms().get(0).getLocalizedName()
                response_json.addAll(response.body().getFilms());
                Log.i(TAG,  Integer.toString(response.code()) + " " +response_json.size() + response_json.get(16).getLocalizedName());
                MainActivity.getInstance().loading = true;
                init();
                listFragment = new ListFragment();
                filmFragment = new FilmFragment();
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.add(R.id.ListFragment,  MainActivity.listFragment);
                fTrans.commit();
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.i(TAG, "kapec" );
            }
        });

        /*while(!loading) {

        }
        init();
        listFragment = new ListFragment();
        filmFragment = new FilmFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.ListFragment,  MainActivity.listFragment);
        fTrans.commit();*/

    }

    private void init() {

        //Header header1 = new Header("Жанры", 0, 1);
        //Header header2 = new Header("Фильмы", 0, 2);
        //items.add(header1);
        for (int i=0; i < 1; i++){
            //Message message = response_json.get(i);
            Header header = new Header("header ", 0, i);
            Genre genre = new Genre("genre " + i, 1, i );
            //Film film = new Film(2, i,  response_json.get(i).getLocalizedName(),"genre ", 1, 1, "genre ","genre ", "genre ");
            //Film film = new Film(2, i, response_json.get(i).getLocalizedName(), "name", 1, 1, "message.getImage_url()", "message.getDescription()", "message.getGenres()");

            items.add(header);
            items.add(genre);
            items.add(genre);
            items.add(header);
            //items.add(film);
           // items.add(film);

            /*  Collections.sort(items, new Comparator<Item>() {
                public int compare(Item o1, Item o2) {

                    if (o1.getID() == o2.getID()) return o1.getItemType() - o2.getItemType();
                    else return (int) (o1.getID() - o2.getID());
                }
            });*/
        }
        //items.add(header2);
        for (int i=0; i < response_json.size(); i++){
            Film film = new Film(2, i, response_json.get(i).getLocalizedName(), response_json.get(i).getName(), response_json.get(i).getYear(), response_json.get(i).getRating(),
                    response_json.get(i).getImageUrl(), response_json.get(i).getDescription(), "message.getGenres()");
            items.add(film);
        }
    }
}