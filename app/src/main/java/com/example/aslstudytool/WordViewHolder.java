package com.example.aslstudytool;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.aslstudytool.WordAdapter;
import com.example.aslstudytool.databinding.WordItemBinding;
import com.example.aslstudytool.models.Word;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class WordViewHolder extends SortedListAdapter.ViewHolder<Word>{

    private final WordItemBinding mBinding;

    public WordViewHolder(WordItemBinding binding, WordAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);
        mBinding = binding;
    }



    @Override
    protected void performBind(@NonNull Word word) {
        mBinding.setWord(word);
    }
}
