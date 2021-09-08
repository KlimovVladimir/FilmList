package com.example.filmlist.list_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.Random;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ListViewHolder> {
    private Random random;


    public AdapterList(int seed) {
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_textview;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_textview, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(String.valueOf(random.nextInt()));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.genre);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.fTrans = MainActivity.getInstance().getSupportFragmentManager().beginTransaction();
                    MainActivity.fTrans.replace(R.id.ListFragment,  MainActivity.filmFragment);
                    MainActivity.fTrans.addToBackStack(null);
                    MainActivity.fTrans.commit();
                }
            });
        }

        public void bind(String text) {
            textView.setText(text);
        }
    }
}
