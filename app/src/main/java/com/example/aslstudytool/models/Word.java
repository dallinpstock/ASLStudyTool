package com.example.aslstudytool.models;

import android.support.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Objects;

public class Word implements SortedListAdapter.ViewModel{
    private String word;

    private String url;


    public Word(String word, String url) {
        this.word = word;

        this.url = url;
    }

    public String getWord() {
        return word;
    }


    public String getUrl() {
        return url;
    }



    @Override
    public <T> boolean isSameModelAs(@NonNull T item) {
        if(item instanceof Word){
            final Word word = (Word) item;
            return word.getWord().equals(getWord());
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T item) {
        if(item instanceof Word){
            final Word other = (Word) item;
            return word.equals(other.word);
        }
        return false;
    }
}
