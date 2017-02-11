package com.example.atsuto5.yahoo_rss_reader_app;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class RssAdapter extends ArrayAdapter<ItemBeans> {
    private LayoutInflater mInflater;

    public RssAdapter(Context context, int id, List<ClipData.Item> items) {
        super(context, id);

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


    public View getView(int position, View view, ViewGroup parent){

        if(view == null){
        view = mInflater.inflate(R.layout.rss_beans, null);
        }

        ItemBeans item = this.getItem(position);

        if(item != null){

            TextView titleText = (TextView)view.findViewById(R.id.textView_1);
            titleText.setText(item.getTitle());
            TextView contentsText = (TextView)view.findViewById(R.id.textView_2);
            contentsText.setText(item.getSummary());
            }
        return view;
        }
}
