package com.example.aslstudytool;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.aslstudytool.databinding.WordItemBinding;
import com.example.aslstudytool.models.Word;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import java.util.Comparator;

public class WordAdapter extends SortedListAdapter<Word> {

    public interface Listener{
        void onWordClicked(Word word);

    }
    private final Listener mListener;

    public WordAdapter( Context context, Comparator<Word> comparator, Listener listener) {
        super(context, Word.class, comparator);
        mListener= listener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends Word> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final WordItemBinding binding = WordItemBinding.inflate(inflater, parent, false);
       return new WordViewHolder(binding, mListener);
    }
}
