package com.example.atsuto5.yahoo_rss_reader_app;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RssAdapter mRssAdapter;
    private ListView mRssList;
    private MainActivity mMainActivity;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList items = new ArrayList();
        mRssAdapter = new RssAdapter(this, R.layout.rss_beans, items);
        mRssList = (ListView) findViewById(R.id.Rss_ListView);
        mMainActivity = new MainActivity();

        //データ取得開始
        RssAsyncTask rssAsync = new RssAsyncTask(mRssList, mRssAdapter, this, mRefreshLayout, true);
        rssAsync.execute("");

        //下にフリックした際、更新処理を行う
        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.red,R.color.blue,R.color.green,R.color.yellow);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RssAsyncTask rssAsync = new RssAsyncTask(mRssList, mRssAdapter, mMainActivity, mRefreshLayout, false);
                rssAsync.execute("");
            }
        });
    }
}