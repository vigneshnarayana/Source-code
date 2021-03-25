package com.example.tvs.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvs.R;


public class CustomGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;
    LayoutInflater inflter;

    public CustomGridViewAdapter(Context context, String[] gridViewString, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
        inflter = (LayoutInflater.from(mContext));
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
//        View gridViewAndroid;
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//
//            gridViewAndroid = new View(mContext);
//            gridViewAndroid = inflater.inflate(R.layout.gridviewmenu, null);
//            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
//            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
//            textViewAndroid.setText(gridViewString[i]);
//            imageViewAndroid.setImageResource(gridViewImageId[i]);
//        } else {
//            gridViewAndroid = (View) convertView;
//        }
//
//        return gridViewAndroid;
        convertView = inflter.inflate(R.layout.gridviewmenu, null); // inflate the layout
        ImageView imageViewAndroid = (ImageView) convertView.findViewById(R.id.android_gridview_image); // get the reference of ImageView
        TextView textViewAndroid = (TextView) convertView.findViewById(R.id.android_gridview_text);
        imageViewAndroid.setImageResource(gridViewImageId[i]); // set logo images
        textViewAndroid.setText(gridViewString[i]);
        return convertView;
    }
}
