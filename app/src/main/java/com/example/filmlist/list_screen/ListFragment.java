package com.example.filmlist.list_screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.items.Film;
import com.example.filmlist.items.Genre;
import com.example.filmlist.items.Header;

public class ListFragment extends Fragment {

    private final int HEADER = 0;
    private final int GENRE = 1;
    private final int FILM = 2;

    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = MainActivity.getInstance().items.get(position).getItemType();
                if (type == 0) position = 2;
                else if (type == 1) position = 2;
                else position = 1;

                return position;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AdapterList(MainActivity.getInstance().items));
        return view;
    }
}