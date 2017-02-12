package com.example.atsuto5.yahoo_rss_reader_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList items = new ArrayList();
        RssAdapter rssAdapter = new RssAdapter(this, R.layout.rss_beans, items);
        ListView rssList = (ListView) findViewById(R.id.Rss_ListView);
        RssAsyncTask rssAsync = new RssAsyncTask(rssList, rssAdapter, this);
        rssAsync.execute("");

        //Listernerをセット


    }
}