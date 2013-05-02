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

import com.taiwan.imageload.GridViewAdapter;
import com.taiwan.imageload.LoadMoreGridView;
import com.travel.story.R;
import com.travel.story.entity.Article;

public class LastCategoryMostSeeFragment extends Fragment {

	private ArrayList<Article> articles = new ArrayList<Article>();
    private LoadMoreGridView myGrid;
    private GridViewAdapter  myGridViewAdapter;
    private LinearLayout     progressLayout;
    private LinearLayout     loadmoreLayout;
    private LinearLayout     layoutReload;
    private Button           buttonReload;

    private Article samlpleArticle = new Article(1, "最新最全最实用厦门旅游攻略（2012升级版）", "飞奔的小驴", "2012-04-20", "之前有很多网友在我的攻略里提问，因为论坛不会每天上所以回复没有那么及时，如果大家有更", "http://p3.lvpingphoto.com/LCRG7cV1u_metal");
    
    public static LastCategoryMostSeeFragment newInstance() {

    	LastCategoryMostSeeFragment fragment = new LastCategoryMostSeeFragment();

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
                // Do the work to load more items at the end of list

                // if(checkLoad){
                // myPage = myPage +1;
                // loadmoreLayout.setVisibility(View.VISIBLE);
                // new LoadMoreTask().execute();
                // }else{
                // myGrid.onLoadMoreComplete();
                // }
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

        	articles.add(samlpleArticle);
        	articles.add(samlpleArticle);
        	articles.add(samlpleArticle);
        	articles.add(samlpleArticle);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (articles != null) {
                layoutReload.setVisibility(View.GONE);
                try {
                    myGridViewAdapter = new GridViewAdapter(getActivity(), articles);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

}