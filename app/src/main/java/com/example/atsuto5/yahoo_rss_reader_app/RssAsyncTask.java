package com.example.atsuto5.yahoo_rss_reader_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.LoginFilter;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class RssAsyncTask extends AsyncTask<String, Integer, RssAdapter> {

    private ListView mRssListView;
    private RssAdapter mRssAdapter;
    private static final String TAG = "RssAsyncTask";
    private MainActivity mActivity;
    private ProgressDialog mLoadingDialog;
    private boolean mDialogFlag;
    private SwipeRefreshLayout mRefreshLayout;

    public RssAsyncTask(ListView listView, RssAdapter rssAdapter, MainActivity activity, SwipeRefreshLayout refreshLayout, Boolean dialogFlag) {
        this.mRssListView = listView;
        this.mRssAdapter = rssAdapter;
        this.mActivity = activity;
        this.mDialogFlag = dialogFlag;
        this.mRefreshLayout = refreshLayout;

        }
    @Override
    protected void onPreExecute(){
        if(mDialogFlag) {
            mLoadingDialog = new ProgressDialog(mActivity);
            mLoadingDialog.setMessage("Now Loading...");
            mLoadingDialog.show();
        }
    }


    @Override
    protected RssAdapter doInBackground(String... arg0) {


        String url = "http://news.yahoo.co.jp/pickup/rss.xml";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpUriRequest req = new HttpGet(url);
        HttpResponse res = null;

        try {

            res = client.execute(req);
            res.getStatusLine().getStatusCode();

            Log.i(TAG, "doInBackground res = : " + res.getStatusLine().getStatusCode());

            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(res.getEntity().getContent(),"UTF-8");


            ItemBeans item = null;

            for(int e = xmlPullParser.getEventType(); e != XmlPullParser.END_DOCUMENT; e = xmlPullParser.next()){

                Log.i(TAG, "doInBackground: xmlPullParser.getName()" + xmlPullParser.getName());


                if (e == XmlPullParser.START_TAG) {
                    if (xmlPullParser.getName().equals("item")) {
                        item = new ItemBeans();
                        }
                    if (xmlPullParser.getName().equals("title")) {
                        if (item != null) item.setTitle(xmlPullParser.nextText());
                        }

                    if (xmlPullParser.getName().equals("link")) {
                        if (item != null) item.setUrl(xmlPullParser.nextText());
                        }
                    }if (e == XmlPullParser.END_TAG && xmlPullParser.getName().equals("item")) {
                    mRssAdapter.add(item);
                    }
                }


            } catch (Exception e) {
            e.printStackTrace();
        }
        return mRssAdapter;
        }

    @Override
    protected void onPostExecute(RssAdapter res) {
        if(mDialogFlag) {
            //ダイアログを消去
            mLoadingDialog.dismiss();
            mRssListView.setAdapter(res);
        }else{
            //下スワイプのインジケータをストップ
            mRefreshLayout.setRefreshing(false);
            }
        }
    }