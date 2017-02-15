package com.example.atsuto5.yahoo_rss_reader_app;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class RssAdapter extends ArrayAdapter<ItemBeans> {
    private LayoutInflater mInflater;
    private ArrayList<ItemBeans> itemList = new ArrayList<>();
    private String TAG = "RssAdapter";
    private static final String URL_KEY = "URL";
    private static final String PACKAGE_NAME = "com.example.atsuto5.yahoo_rss_reader_app";
    private static final String WebViewActivity_NAME = "com.example.atsuto5.yahoo_rss_reader_app.WebViewActivity";
    private Context mContext;

    static class ViewHolder{
        TextView titleText;
        TextView urlText;
        ImageButton webViewButton;
    }


    public RssAdapter(Context context, int id) {
        super(context, id);
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


    public View getView(final int position, View view, final ViewGroup parent){

        ViewHolder holder;

        if(view == null) {
            view = mInflater.inflate(R.layout.rss_beans, null);
            //ViewHolderを作成
            holder = new ViewHolder();
            holder.titleText = (TextView) view.findViewById(R.id.titleTextView);
            holder.urlText = (TextView) view.findViewById(R.id.urlTextView);
            holder.webViewButton = (ImageButton) view.findViewById(R.id.webViewButton);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }

            final ItemBeans item = this.getItem(position);

            if(item != null){

                holder.titleText.setText(item.getTitle());
                holder.urlText.setText(item.getUrl());

                holder.webViewButton.setBackgroundResource(R.drawable.button_selector);
                holder.webViewButton.setOnClickListener(new View.OnClickListener()  {
                    //ボタンを押したときWebViewActivityに遷移する
                    public void onClick(View v) {
                        Intent webViewIntent = new Intent();
                        webViewIntent.setClassName(PACKAGE_NAME,WebViewActivity_NAME);
                        webViewIntent.putExtra(URL_KEY, item.getUrl());
                        Log.i(TAG, "onClick: " + item.getUrl());
                        mContext.startActivity(webViewIntent);
                    }
                });
            }
        return view;
        }
}
