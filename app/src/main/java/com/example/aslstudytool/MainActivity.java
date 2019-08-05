package com.example.aslstudytool;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.databinding.DataBindingUtil;

import com.example.aslstudytool.databinding.ActivityMainBinding;
import com.example.aslstudytool.models.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;



public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SortedListAdapter.Callback{
    static ArrayList<String> letterUrls;
    static ArrayList<String> wordUrls;
    static ArrayList<String> words;
    private static final Comparator<Word> COMPARATOR = new SortedListAdapter.ComparatorBuilder<Word>()
            .setOrderForModel(Word.class, (a, b) -> Integer.signum(a.getWord().compareTo(b.getWord())))
            .build();

    private WordAdapter wAdapter;
    private ActivityMainBinding mBinding;
    private Animator mAnimator;

    final List<Word> wordlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letterUrls = new ArrayList<>(Arrays.asList(new String[0]));
        wordUrls = new ArrayList<>(Arrays.asList(new String[0]));
        words = new ArrayList<>(Arrays.asList(new String[0]));
        String url = getString(R.string.BASE_URL);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        wAdapter = new WordAdapter(this, COMPARATOR, model -> {
           //THIS SEEMS TO BE THE ON CLICK RESULTING FUNCTION
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(model.getUrl()));
            this.startActivity(i);

        });

        wAdapter.addCallback(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(wAdapter);
        words = new ArrayList<>();
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



                for (int i = 0; i < words.size(); ++i) {
                    wordlist.add(new Word(words.get(i),wordUrls.get(i)));
                }
                wAdapter.edit()
                        // TODO add the information model in here
                        .replaceAll(wordlist)
                        .commit();


                //Do something with values

                //final RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
               // recyclerView.setHasFixedSize(true);

                // use a linear layout manager
            //    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setLayoutManager(layoutManager);
                        RecyclerView.Adapter mAdapter = new ListAdapter(wordlist);
                        recyclerView.setAdapter(mAdapter);

                    }
                });*/

                // specify an adapter (see also next example)
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Word> filteredWordList = filter(wordlist, query);
        wAdapter.edit()
                .replaceAll(filteredWordList)
                .commit();
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<Word> filter(List<Word> models, String query){
        final String lowerCaseQuery = query.toLowerCase();
        final List<Word> filteredModeList = new ArrayList<>();
        for (Word model : models){
            final String text = model.getWord().toLowerCase();
            final String url = model.getUrl();
            if (text.contains(lowerCaseQuery)||url.contains(lowerCaseQuery)){
                filteredModeList.add(model);
            }
        }
    return filteredModeList;
    }


    @Override
    public void onEditStarted() {

    }

    @Override
    public void onEditFinished() {

    }
}
