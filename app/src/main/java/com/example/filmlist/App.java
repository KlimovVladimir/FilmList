package com.example.filmlist;

import android.app.Application;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;
    private Retrofit retrofit;
    private MessagesApi messagesApi;
    private Call<Films> messages;

    private static final String TAG = "###DEBUG###";

    public static long selectedFilm = 0;
    public static long selectedGenre = -1;

    public static int start_films = 0;
    public static int end_films = 0;

    public static int start_genres = 0;
    public static int end_genres = 0;

    public ArrayList<Item> items = new ArrayList();
    public ArrayList<Film> films = new ArrayList();
    public ArrayList<String> genres = new ArrayList();
    public ArrayList<Message> response_json = new ArrayList();

    public boolean initFinish = false;

    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        retrofit = new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
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
                //Log.i(TAG, Integer.toString(response.code()) + " " + response_json.size() + response_json.get(16).getLocalizedName());
                init();
                initFinish = true;
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.i(TAG, "can't open URL");
            }
        });
    }

    public void init() {

        Header header1 = new Header("Жанры", 0, 1);
        Header header2 = new Header("Фильмы", 0, 2);
        items.add(header1);
        for (int i = 0; i < response_json.size(); i++) {
            for (int j = 0; j < response_json.get(i).getGenres().size(); j++) {
                if (!genres.contains(response_json.get(i).getGenres().get(j))) {
                    genres.add(response_json.get(i).getGenres().get(j));
                }
            }
        }
        start_genres = items.size();
        for (int i = 0; i < genres.size(); i++) {
            Genre genre = new Genre(genres.get(i), 1, i);
            items.add(genre);
        }
        end_genres = items.size();
        items.add(header2);
        start_films = items.size();
        for (int i = 0; i < response_json.size(); i++) {
            Film film = new Film(2, response_json.get(i).getId(), response_json.get(i).getLocalizedName(), response_json.get(i).getName(), response_json.get(i).getYear(), response_json.get(i).getRating(),
                    response_json.get(i).getImageUrl(), response_json.get(i).getDescription(), response_json.get(i).getGenres());
            //items.add(film);
            films.add(film);
        }
        Collections.sort(films, new Comparator<Film>() {
            public int compare(Film o1, Film o2) {
                return o1.getLocalizedName().compareTo(o2.getLocalizedName());
            }
        });
        for (int i = 0; i < films.size(); i++) {
            items.add(films.get(i));
        }
        end_films = items.size();
    }
}
