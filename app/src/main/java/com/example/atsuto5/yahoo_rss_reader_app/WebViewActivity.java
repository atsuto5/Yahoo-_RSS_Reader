package com.example.atsuto5.yahoo_rss_reader_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Atsuto5 on 2017/02/12.
 */
public class WebViewActivity extends AppCompatActivity {

    String URL_KEY = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        TextView textView = (TextView)findViewById(R.id.textView);
        Intent webViewIntent = getIntent();
        textView.setText(webViewIntent.getStringExtra(URL_KEY));
    }



}
