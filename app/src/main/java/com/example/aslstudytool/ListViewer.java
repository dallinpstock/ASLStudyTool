package com.example.aslstudytool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aslstudytool.ui.listviewer.ListViewerFragment;

public class ListViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_viewer_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ListViewerFragment.newInstance())
                    .commitNow();
        }
    }
}
