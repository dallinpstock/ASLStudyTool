package com.example.aslstudytool;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class TempListViewer extends AppCompatActivity {
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView wordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_list_viewer);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView = new RecyclerView(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(listAdapter);


        listAdapter =  new ListAdapter(this, MainActivity.words, new ListAdapter.ListListener() {
            @Override
            public void processResult(String text) {
                wordView = findViewById(R.id.word_text_view);
                wordView.setText(text);

            }
        });




        listAdapter.notifyDataSetChanged();


    }
}
