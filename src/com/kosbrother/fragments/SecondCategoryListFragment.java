package com.kosbrother.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.taiwan.imageload.ListIntroAdapter;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Area;

@SuppressLint("ValidFragment")
public class SecondCategoryListFragment extends Fragment {

//	private SeparatedListAdapter adapter;
	private ArrayList<Area> myAreas = new ArrayList<Area>();
	private int nationId;
	private ListView myFragmentView;
	
	public SecondCategoryListFragment(){

	}
	
		
	public static final SecondCategoryListFragment newInstance(int nation_id)
    {
		SecondCategoryListFragment f = new SecondCategoryListFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("id", nation_id);
        f.setArguments(bdl);
        return f;
    }
	
	
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 nationId = getArguments().getInt("id");
	    super.onCreate(savedInstanceState);
	 }
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		
		new DownloadChannelsTask().execute();		
        myFragmentView = (ListView)inflater.inflate(R.layout.list, container, false);
        
        
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

        	myAreas = TravelAPI.getNationAreas(nationId);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            
            try{
            	ListIntroAdapter adapter = new ListIntroAdapter(getActivity(), myAreas);
            	myFragmentView.setAdapter(adapter);
            }catch(Exception e){
            	
            }
            
            
        }
    }
	
}
