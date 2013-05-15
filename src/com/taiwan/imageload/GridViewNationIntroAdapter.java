package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.travel.story.AreaIntroContentActivity;
import com.travel.story.R;
import com.travel.story.entity.AreaIntro;

public class GridViewNationIntroAdapter extends BaseAdapter {

    private final Activity         activity;
    private final ArrayList<AreaIntro> data;
    private static LayoutInflater  inflater = null;
    public ImageLoader             imageLoader;
    

    public GridViewNationIntroAdapter(Activity a, ArrayList<AreaIntro> d) {
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
        // vi = inflater.inflate(R.layout.item_gridview_novel, null);

//        Display display = activity.getWindowManager().getDefaultDisplay();
//        int width = display.getWidth(); // deprecated
//        int height = display.getHeight(); // deprecated
//        
//        if (width > 480) {
//           
//        } else {
//            vi = inflater.inflate(R.layout.item_gridview_novel_small, null);
//        }
        
        vi = inflater.inflate(R.layout.item_gridview_city, null);
        TextView mTextView = (TextView) vi.findViewById(R.id.text_grid_item);
        mTextView.setText(data.get(position).getTitle());
        
        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AreaIntroContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("NationIntroId", data.get(position).getId());
                bundle.putString("NationIntroTitle", data.get(position).getTitle());
                bundle.putInt("TypeId", 0);
                intent.putExtras(bundle);
                activity.startActivity(intent);

            }

        });

        return vi;
    }
}
