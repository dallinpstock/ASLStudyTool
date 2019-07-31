package com.example.aslstudytool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.aslstudytool.models.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    static ArrayList<String> letterUrls;
    static ArrayList<String> wordUrls;
    static ArrayList<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letterUrls = new ArrayList<>(Arrays.asList(new String[0]));
        wordUrls = new ArrayList<>(Arrays.asList(new String[0]));
        words = new ArrayList<>(Arrays.asList(new String[0]));
        String url = getString(R.string.BASE_URL);

        for (int i = 0; i < 26; i++) {


            url = new StringBuilder(url).insert(39, (char) (((int) url.charAt(39)) + 1)).toString();
            url = new StringBuilder(url).deleteCharAt(40).toString();

            letterUrls.add(i, url);
        }
        new WebScraper().execute(new WebScraper.WebScraperCallback() {
            @Override
            public void handleResult() {
                int a = 1;
                System.out.println(wordUrls);
                System.out.println(words);

                final ArrayList<Word> wordlist = new ArrayList<>();

                for (int i = 0; i < words.size(); ++i) {
                    wordlist.add(new Word(words.get(i),words.get(i).charAt(0),wordUrls.get(i)));
                }
                //Do something with values

                final RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                // use a linear layout manager
                final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setLayoutManager(layoutManager);
                        RecyclerView.Adapter mAdapter = new ListAdapter(wordlist);
                        recyclerView.setAdapter(mAdapter);

                    }
                });

                // specify an adapter (see also next example)
            }
        });


    }
}
