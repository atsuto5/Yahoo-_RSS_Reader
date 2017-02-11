package com.example.atsuto5.yahoo_rss_reader_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList mItems = new ArrayList();
        RssAdapter mRssAdapter = new RssAdapter(this,R.layout.rss_beans,mItems);
        ListView mRssList = (ListView)findViewById(R.id.Rss_ListView);
        RssAsyncTask mRssAsync = new RssAsyncTask(mRssList,mRssAdapter);
        mRssAsync.execute("");
    }
}
