package com.example.filmlist.list_screen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    boolean isSecondItem = false;
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
                isSecondItem = false;
                break;
            case GENRE:
                Genre genre = (Genre) items.get(position);
                holder.genre.setText(genre.getGenre());
                holder.genre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data.subList(startIndex, endIndex).clear();
                        //adapter.notifyItemRangeRemoved(startIndex, count);
                        ArrayList<Film> insert_films = new ArrayList<>();
                        int count = MainActivity.end_films - MainActivity.start_films;
                        MainActivity.getInstance().items.subList(MainActivity.start_films, MainActivity.end_films).clear();
                        MainActivity.listFragment.adapter.notifyItemRangeRemoved(MainActivity.start_films, count);

                        for (int i = 0; i < MainActivity.getInstance().films.size(); i++) {
                            for (int j = 0; j < MainActivity.getInstance().films.get(i).getGenres().size(); j++) {
                                if (MainActivity.getInstance().films.get(i).getGenres().get(j).equals(genre.getGenre())) {
                                    insert_films.add(MainActivity.getInstance().films.get(i));
                                    break;
                                }
                            }
                        }
                        MainActivity.getInstance().items.addAll(MainActivity.start_films, insert_films);
                        MainActivity.listFragment.adapter.notifyItemRangeInserted(MainActivity.start_films, MainActivity.getInstance().items.size());
                        MainActivity.end_films = MainActivity.getInstance().items.size();
                    }
                });
                break;
            case FILM:
                Film film = (Film) items.get(position);
                //holder.header2.setVisibility(isSecondItem == false? View.VISIBLE: View.GONE);
                holder.film_name.setText(film.getLocalizedName());
                Picasso.get()
                        .load(film.getImage_url())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.img);
                //holder.img.loadUrl(film.getImage_url());
                //holder.img.getSettings().setJavaScriptEnabled(true);
                //holder.img.loadUrl("https://i.picsum.photos/id/977/200/300.jpg?hmac=YYtcm39X8v9y0KYAb_9s-ufIz_R0Kgbt_EP0F8-jkFU");
                isSecondItem = true;
                holder.film_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getInstance().selectedFilm = film.getID();
                        MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                        MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.filmFragment);
                        MainActivity.fTrans.addToBackStack(null);
                        MainActivity.fTrans.commit();
                    }
                });
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getInstance().selectedFilm = film.getID();
                        MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                        MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.filmFragment);
                        MainActivity.fTrans.addToBackStack(null);
                        MainActivity.fTrans.commit();
                    }
                });
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getInstance().selectedFilm = film.getID();
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

        public ListViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            genre = itemView.findViewById(R.id.genre);
            film_name = itemView.findViewById(R.id.film_name);
            img = itemView.findViewById(R.id.image);
            view = itemView.findViewById(R.id.view);
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
