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
import android.widget.Toast;
import com.taiwan.imageload.GridViewSiteAdapter;
import com.taiwan.imageload.LoadMoreGridView;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Site;

public class SecondCategorySiteFragment extends Fragment {

    private  ArrayList<Site> sites       = new ArrayList<Site>();
    private LoadMoreGridView      myGrid;
    private GridViewSiteAdapter   myGridViewAdapter;
    private LinearLayout          progressLayout;
    private LinearLayout          loadmoreLayout;
    private LinearLayout          layoutReload;
    private LinearLayout          layoutNodata;
    private Button                buttonReload;

    private int areaId;
    private  int myPage = 1;
    private Boolean checkLoad = true;
    private  ArrayList<Site> moreSites       = new ArrayList<Site>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	if (getArguments() != null) {  	
    		areaId = getArguments().getInt("AreaId");
    	}
    	
        View myFragmentView = inflater.inflate(R.layout.loadmore_grid, container, false);
        progressLayout = (LinearLayout) myFragmentView.findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) myFragmentView.findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) myFragmentView.findViewById(R.id.layout_reload);
        layoutNodata = (LinearLayout) myFragmentView.findViewById(R.id.layout_no_data);
        buttonReload = (Button) myFragmentView.findViewById(R.id.button_reload);
        myGrid = (LoadMoreGridView) myFragmentView.findViewById(R.id.news_list);
        myGrid.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
            public void onLoadMore() {
            	if(checkLoad){
					myPage = myPage +1;
					new LoadMoreTask().execute();
				}else{
					myGrid.onLoadMoreComplete();
				}
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

            sites = TravelAPI.getAreaSites(areaId, myPage);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (sites != null) {
            	
            	layoutReload.setVisibility(View.GONE);
            	
            	if (sites.size()!=0){
            		try {
            			myGridViewAdapter = new GridViewSiteAdapter(getActivity(), sites);
            			myGrid.setAdapter(myGridViewAdapter);
            		} catch (Exception e) {
            			
            		}
            	}else{
            		layoutNodata.setVisibility(View.VISIBLE);
            	}
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }
    
    private class LoadMoreTask extends AsyncTask {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            loadmoreLayout.setVisibility(View.VISIBLE);
            
        }
    	
        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub
        	
        	moreSites.clear();
        	moreSites = TravelAPI.getAreaSites(areaId, myPage);
        	if(moreSites!= null){
	        	for(int i=0; i<moreSites.size();i++){
	        		sites.add(moreSites.get(i));	        		
	            }
        	}
        	
        	
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            
            loadmoreLayout.setVisibility(View.GONE);
            
            if(moreSites!= null && moreSites.size()!=0){
            	myGridViewAdapter.notifyDataSetChanged();	                
            }else{
                checkLoad= false;
                Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();            	
            }       
            myGrid.onLoadMoreComplete();
          	
          	
        }
    }

}
