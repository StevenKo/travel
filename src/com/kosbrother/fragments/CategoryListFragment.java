package com.kosbrother.fragments;


import java.util.ArrayList;

import com.taiwan.imageload.ListNationIntroAdapter;
import com.taiwan.imageload.SeparatedListAdapter;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Nation;
import com.travel.story.entity.NationGroup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class CategoryListFragment extends Fragment {

	private SeparatedListAdapter adapter;
	private ArrayList<NationGroup> myNationGroups = new ArrayList<NationGroup>();
	private int stateId;
	
	public CategoryListFragment(){
	}
	
	public static final CategoryListFragment newInstance(int state_id)
    {
		CategoryListFragment f = new CategoryListFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("id", state_id);
        f.setArguments(bdl);
        return f;
    }
	
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		stateId = getArguments().getInt("id");
		myNationGroups = TravelAPI.getStateNationGroups(stateId);
	    super.onCreate(savedInstanceState);
	 }
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView myFragmentView = (ListView)inflater.inflate(R.layout.list, container, false);
        
        adapter = new SeparatedListAdapter(getActivity());      
        
        for (int i=0; i< myNationGroups.size();i++){
			ArrayList<Nation> groupNations = TravelAPI.getGroupNations(myNationGroups.get(i).getId());
			ArrayList<Nation> nations = new ArrayList<Nation>();
			if(groupNations!=null){				
				for (int j= 0; j< groupNations.size(); j++){
					nations.add(groupNations.get(j));
				}
			}
			ListNationIntroAdapter nationAdapter = new ListNationIntroAdapter(getActivity(), nations);
			adapter.addSection(myNationGroups.get(i).getName(), nationAdapter);
			
		}
        
        myFragmentView.setAdapter(adapter);
        
        return myFragmentView;
    }
	
	

}
