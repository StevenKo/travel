package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.story.R;
import com.travel.story.entity.Note;

public class GridViewCollectNoteAdapter extends BaseAdapter {

    private final Activity         activity;
    private final ArrayList<Note> data;
    private static LayoutInflater  inflater = null;
    public ImageLoader             imageLoader;

    public GridViewCollectNoteAdapter(Activity a, ArrayList<Note> d) {
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


        TextView textTitle = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView textAuthor = (TextView) vi.findViewById(R.id.grid_item_author);
        TextView textDate = (TextView) vi.findViewById(R.id.grid_item_date);

        textTitle.setText(data.get(position).getTitle());
        textAuthor.setText(data.get(position).getAuthor());
        textDate.setText(data.get(position).getDate());
        
        
        if (data.get(position).getPic().equals("null") ) {
            image.setImageResource(R.drawable.app_icon);
        } else {
            imageLoader.DisplayImage(data.get(position).getPic(), image);
        }


        return vi;
    }
}
