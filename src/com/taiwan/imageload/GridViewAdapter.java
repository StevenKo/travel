package com.taiwan.imageload;

import java.util.ArrayList;

import com.travel.story.ArticleActivity;
import com.travel.story.R;
import com.travel.story.entity.Article;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridViewAdapter extends BaseAdapter {

    private final Activity         activity;
    private final ArrayList<Article> data;
    private static LayoutInflater  inflater = null;
    public ImageLoader             imageLoader;

    public GridViewAdapter(Activity a, ArrayList<Article> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext(), 70);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        // if (convertView == null)
//         vi = inflater.inflate(R.layout.item_gridview_article, null);

        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth(); // deprecated
        int height = display.getHeight(); // deprecated

        if (width > 480) {
            vi = inflater.inflate(R.layout.item_gridview_article, null);
        } else {
            vi = inflater.inflate(R.layout.item_gridview_article_small, null);
        }

        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Toast.makeText(activity, "tt", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, ArticleActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("NovelId", data.get(position).getId());
//                bundle.putString("NovelName", data.get(position).getName());
//                bundle.putString("NovelAuthor", data.get(position).getAuthor());
//                bundle.putString("NovelDescription", data.get(position).getDescription());
//                bundle.putString("NovelUpdate", data.get(position).getLastUpdate());
//                bundle.putString("NovelPicUrl", data.get(position).getPic());
//                bundle.putString("NovelArticleNum", data.get(position).getArticleNum());
//                intent.putExtras(bundle);
                activity.startActivity(intent);

            }

        });

        TextView textTitle = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView textAuthor = (TextView) vi.findViewById(R.id.grid_item_author);
        TextView textDate = (TextView) vi.findViewById(R.id.grid_item_date);

        textTitle.setText(data.get(position).getTitle());
        textAuthor.setText(data.get(position).getAuthor());
        textDate.setText(data.get(position).getDate());
        
        
        if (data.get(position).getPicUrl().equals("") ) {
            image.setImageResource(R.drawable.app_icon);
        } else {
            imageLoader.DisplayImage(data.get(position).getPicUrl(), image);
        }


        return vi;
    }
}
