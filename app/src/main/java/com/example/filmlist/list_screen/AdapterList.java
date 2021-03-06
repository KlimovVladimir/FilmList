package com.example.filmlist.list_screen;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.App;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.items.Film;
import com.example.filmlist.items.Genre;
import com.example.filmlist.items.Header;
import com.example.filmlist.items.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ListViewHolder> {
    private final int HEADER = 0;
    private final int GENRE = 1;
    private final int FILM = 2;
    private ArrayList<Item> items;


    public AdapterList(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_genre, parent, false);
        // return new ListViewHolder(view);
        View view;
        switch (viewType) {
            case GENRE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_genre, parent, false);
                break;
            case FILM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_film, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_header, parent, false);
        }
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case HEADER:
                Header header = (Header) items.get(position);
                holder.header.setText(header.getTitle());
                break;
            case GENRE:
                Genre genre = (Genre) items.get(position);
                holder.genre.setText(genre.getGenre());
                holder.genre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.selectedGenre = position;
                        notifyDataSetChanged();
                        //data.subList(startIndex, endIndex).clear();
                        //adapter.notifyItemRangeRemoved(startIndex, count);
                        ArrayList<Film> insert_films = new ArrayList<>();
                        int count = App.end_films - App.start_films;
                        App.getInstance().items.subList(App.start_films, App.end_films).clear();
                        MainActivity.listFragment.adapter.notifyItemRangeRemoved(App.start_films, count);

                        for (int i = 0; i < App.getInstance().films.size(); i++) {
                            for (int j = 0; j < App.getInstance().films.get(i).getGenres().size(); j++) {
                                if (App.getInstance().films.get(i).getGenres().get(j).equals(genre.getGenre())) {
                                    insert_films.add(App.getInstance().films.get(i));
                                    break;
                                }
                            }
                        }
                        App.getInstance().items.addAll(App.start_films, insert_films);
                        MainActivity.listFragment.adapter.notifyItemRangeInserted(App.start_films, App.getInstance().items.size());
                        App.end_films = App.getInstance().items.size();
                    }
                });
                holder.genre_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.selectedGenre = position;
                        notifyDataSetChanged();
                        ArrayList<Film> insert_films = new ArrayList<>();
                        int count = App.end_films - App.start_films;
                        App.getInstance().items.subList(App.start_films, App.end_films).clear();
                        MainActivity.listFragment.adapter.notifyItemRangeRemoved(App.start_films, count);

                        for (int i = 0; i < App.getInstance().films.size(); i++) {
                            for (int j = 0; j < App.getInstance().films.get(i).getGenres().size(); j++) {
                                if (App.getInstance().films.get(i).getGenres().get(j).equals(genre.getGenre())) {
                                    insert_films.add(App.getInstance().films.get(i));
                                    break;
                                }
                            }
                        }
                        App.getInstance().items.addAll(App.start_films, insert_films);
                        MainActivity.listFragment.adapter.notifyItemRangeInserted(App.start_films, App.getInstance().items.size());
                        App.end_films = App.getInstance().items.size();
                    }
                });
                if (App.selectedGenre == position)
                    holder.genre_back.setBackground(ContextCompat.getDrawable(MainActivity.getInstance().getApplicationContext(), R.drawable.shape_blue));
                else
                    holder.genre_back.setBackground(ContextCompat.getDrawable(MainActivity.getInstance().getApplicationContext(), R.drawable.shape));
                break;
            case FILM:
                Film film = (Film) items.get(position);
                String locname = film.getLocalizedName();
                if (locname.length() > 27) {
                    locname = locname.substring(0, 27) + "..";
                }
                    holder.film_name.setText(locname);
                Picasso.get()
                        .load(film.getImage_url())
                        .placeholder(R.drawable.ic_no_poster)
                        .error(R.drawable.ic_no_poster)
                        .into(holder.img);
                holder.film_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.getInstance().selectedFilm = film.getID();
                        MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                        MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.filmFragment);
                        MainActivity.fTrans.addToBackStack(null);
                        MainActivity.fTrans.commit();
                    }
                });
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.getInstance().selectedFilm = film.getID();
                        MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                        MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.filmFragment);
                        MainActivity.fTrans.addToBackStack(null);
                        MainActivity.fTrans.commit();
                    }
                });
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.getInstance().selectedFilm = film.getID();
                        MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                        MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.filmFragment);
                        MainActivity.fTrans.addToBackStack(null);
                        MainActivity.fTrans.commit();
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = items.get(position).getItemType();
        if (type == 0) return HEADER;
        else if (type == 1) return GENRE;
        else return FILM;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        TextView header, genre, film_name;
        ImageView img;
        View view;
        FrameLayout genre_back;

        public ListViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            genre = itemView.findViewById(R.id.genre);
            film_name = itemView.findViewById(R.id.film_name);
            img = itemView.findViewById(R.id.image);
            view = itemView.findViewById(R.id.view);
            genre_back = itemView.findViewById(R.id.genre_back);
            /*film_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                    MainActivity.fTrans.replace(R.id.ListFragment,  MainActivity.filmFragment);
                    MainActivity.fTrans.addToBackStack(null);
                    MainActivity.fTrans.commit();
                }
            });*/
        }
    }
}
