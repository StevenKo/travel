package com.kosbrother.fragments;


import java.util.ArrayList;

import com.taiwan.imageload.ListIntroAdapter;
import com.taiwan.imageload.ListNationIntroAdapter;
import com.taiwan.imageload.SeparatedListAdapter;
import com.travel.story.LastCategoryActivity;
import com.travel.story.R;
import com.travel.story.SecondCategoryActivity;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Nation;
import com.travel.story.entity.NationGroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class CategoryListFragment extends Fragment {

	private SeparatedListAdapter adapter;
	private ArrayList<NationGroup> myNationGroups = new ArrayList<NationGroup>();
	private int stateId;
	private Nation nullNation = new Nation();
	
	public CategoryListFragment(int state_id, ArrayList<NationGroup> nation_groups){
		this.stateId = state_id;
		this.myNationGroups = nation_groups;
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
