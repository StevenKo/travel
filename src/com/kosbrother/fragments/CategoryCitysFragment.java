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
import android.widget.GridView;
import android.widget.LinearLayout;

import com.taiwan.imageload.GridViewCitysAdapter;
import com.travel.story.R;

public class CategoryCitysFragment extends Fragment {

    private ArrayList<String> mCitys = new ArrayList<String>();
    private GridView myGrid;
    private GridViewCitysAdapter  myGridViewAdapter;
    private LinearLayout     progressLayout;
//    private LinearLayout     loadmoreLayout;
    private LinearLayout     layoutReload;
    private Button           buttonReload;
    
    private String[] sampleCitys ={
    		"大阪",
    		"京都",
    		"上海",
    		"大阪",
    		"京都",
    		"上海",
    		"大阪",
    		"京都",
    		"上海"
    		
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.layout_category_city, container, false);
        progressLayout = (LinearLayout) myFragmentView.findViewById(R.id.layout_progress);
        layoutReload = (LinearLayout) myFragmentView.findViewById(R.id.layout_reload);
        buttonReload = (Button) myFragmentView.findViewById(R.id.button_reload);
        myGrid = (GridView) myFragmentView.findViewById(R.id.grid_city);

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
        	
        	for(int i=0 ; i<sampleCitys.length; i++){
        		mCitys.add(sampleCitys[i]);
        	}

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);

            if (mCitys != null) {
                try {
                    layoutReload.setVisibility(View.GONE);
                    myGridViewAdapter = new GridViewCitysAdapter(getActivity(), mCitys);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

}
