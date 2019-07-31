package com.example.aslstudytool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.EntryViewHolder> {
    ArrayList words;
    Context context;
    ListListener listListener;
    Activity activity;
    public interface ListListener{

        void processResult(String text);

    }

    public ListAdapter(Context context, ArrayList words, ListListener listListener){

        this.words = words;
        activity = (Activity)context;
        this.context = context;

        this.listListener = listListener;
    }
    public static final int CACHE_SIZE = 15;







    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_word_list, parent, false);
        return new EntryViewHolder(wordView);
    }





    @Override
    public void onBindViewHolder(@NonNull final EntryViewHolder entryViewHolder, int position) {
        entryViewHolder.wordText.setText(MainActivity.words.get(position));

    }





    @Override
    public int getItemCount() {
        return MainActivity.words.size();
    }

    class EntryViewHolder extends RecyclerView.ViewHolder {

        View parentView;
        TextView wordText;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView.findViewById(R.id.list_parent);
            wordText = itemView.findViewById(R.id.word_text_view);




        }

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }





}
