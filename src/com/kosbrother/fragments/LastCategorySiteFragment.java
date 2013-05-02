package com.kosbrother.fragments;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taiwan.imageload.GridViewSiteAdapter;
import com.taiwan.imageload.LoadMoreGridView;
import com.travel.story.R;
import com.travel.story.entity.Site;

public class LastCategorySiteFragment extends Fragment {

    private ArrayList<Site> sites = new ArrayList<Site>();
    private LoadMoreGridView myGrid;
    private GridViewSiteAdapter  myGridViewAdapter;
    private LinearLayout     progressLayout;
    private LinearLayout     loadmoreLayout;
    private LinearLayout     layoutReload;
    private Button           buttonReload;
    
    
    String[] samplePics = {"http://www.alaska-in-pictures.com/data/media/13/cruise-ship-scenic_5402.jpg","http://www.alaska-in-pictures.com/data/media/13/kodiak-at-night_5403.jpg"};
    private Site samlpleSite = new Site(1, 
    		4, "迪士尼度假区（DISNEY LAND", 
    		"东京迪士尼度假区距离东京10公里，于1983年4月开幕，其宗旨是集历史知识、童话故事", "+81-570-008632", 
    		"一日票成年人5800日元", "宝物殿：3月至10月9：00-16：30", 
    		"轨道交通：ＪＲ京叶线舞滨站下车", "主题公园", 
    		samplePics);

    public static LastCategorySiteFragment newInstance() {

    	LastCategorySiteFragment fragment = new LastCategorySiteFragment();

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.loadmore_grid, container, false);
        progressLayout = (LinearLayout) myFragmentView.findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) myFragmentView.findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) myFragmentView.findViewById(R.id.layout_reload);
        buttonReload = (Button) myFragmentView.findViewById(R.id.button_reload);
        myGrid = (LoadMoreGridView) myFragmentView.findViewById(R.id.news_list);
        myGrid.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
            public void onLoadMore() {

            }
        });

        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                progressLayout.setVisibility(View.VISIBLE);
                layoutReload.setVisibility(View.GONE);
                new DownloadChannelsTask().execute();
            }
        });

        if (myGridViewAdapter != null) {
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);
            myGrid.setAdapter(myGridViewAdapter);
        } else {
            new DownloadChannelsTask().execute();
        }

        return myFragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private class DownloadChannelsTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub

        	sites.add(samlpleSite);
        	sites.add(samlpleSite);
        	sites.add(samlpleSite);
        	sites.add(samlpleSite);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (sites != null) {
                try {
                    layoutReload.setVisibility(View.GONE);
                    myGridViewAdapter = new GridViewSiteAdapter(getActivity(), sites);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

}