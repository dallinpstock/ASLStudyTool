package com.example.aslstudytool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        TextView textView = findViewById(R.id.textview);
        String url = getString(R.string.BASE_URL);
        textView.setText(url);

        for(int i =0; i < 26;i++){


           url = new  StringBuilder(url).insert(39,(char)(((int)url.charAt(39))+1)).toString();
           url = new StringBuilder(url).deleteCharAt(40).toString();

           letterUrls.add(i,url);
           textView.setText(url);
        }
     new WebScraper().execute(new WebScraper.WebScraperCallback() {
         @Override
         public void handleResult() {
            int a = 1;
             System.out.println(wordUrls);
             System.out.println(words);
             Intent mIntent = new Intent(MainActivity.this, TempListViewer.class);
             MainActivity.this.startActivity(mIntent);
         }
     });






    }
}
