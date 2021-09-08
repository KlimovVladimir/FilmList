package com.example.filmlist.list_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.items.Film;
import com.example.filmlist.items.Genre;
import com.example.filmlist.items.Header;
import com.example.filmlist.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ListViewHolder> {
    private Random random;
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
                Header header = (Header)items.get(position);
                holder.header.setText(header.getTitle());
                isSecondItem = false;
                break;
            case GENRE:
                Genre genre = (Genre)items.get(position);
                holder.genre.setText(genre.getGenre());
                break;
            case FILM:
                Film film = (Film)items.get(position);
                //holder.header2.setVisibility(isSecondItem == false? View.VISIBLE: View.GONE);
                holder.film_name.setText(film.getLocalizedName());
                isSecondItem = true;
                holder.film_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

        public ListViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            genre = itemView.findViewById(R.id.genre);
            film_name = itemView.findViewById(R.id.film_name);

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
