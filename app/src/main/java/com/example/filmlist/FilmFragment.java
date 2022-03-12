package com.example.filmlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filmlist.items.Film;
import com.example.filmlist.list_screen.AdapterList;
import com.squareup.picasso.Picasso;

public class FilmFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        TextView name, year, rating, descripton;
        ImageView img;

        Film film = null;
        for (int i = 0; i < App.getInstance().films.size(); i++) {
            film = (Film) App.getInstance().films.get(i);
            if (film.getID() == App.getInstance().selectedFilm) {
                break;
            }
        }
        MainActivity.getInstance().setTitle(film.getLocalizedName());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        name = view.findViewById(R.id.textViewName);
        year = view.findViewById(R.id.textViewYear);
        rating = view.findViewById(R.id.textViewRating);
        descripton = view.findViewById(R.id.textViewDescription);
        img = view.findViewById(R.id.imageViewFrame);

        name.setText(film.getName());
        year.setText("Год: " + Integer.toString(film.getYear()));
        rating.setText("Рейтинг: " + Double.toString(film.getRating()));
        descripton.setText(film.getDescription());

        Picasso.get()
                .load(film.getImage_url())
                .placeholder(R.drawable.ic_no_poster)
                .error(R.drawable.ic_no_poster)
                .into(img);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                MainActivity.fTrans.replace(R.id.ListFragment, MainActivity.listFragment);
                MainActivity.fTrans.addToBackStack(null);
                MainActivity.fTrans.commit();
        }
        return super.onOptionsItemSelected(item);
    }
}