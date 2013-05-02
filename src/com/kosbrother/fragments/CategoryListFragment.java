package com.kosbrother.fragments;


import java.util.ArrayList;

import com.taiwan.imageload.SeparatedListAdapter;
import com.travel.story.LastCategoryActivity;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Nation;
import com.travel.story.entity.NationGroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class CategoryListFragment extends ListFragment {

	private SeparatedListAdapter adapter;
	private ArrayList<NationGroup> myNationGroups = new ArrayList<NationGroup>();
	private ArrayList<Nation> myNaions = new ArrayList<Nation>();
	private int stateId;

	public CategoryListFragment(int state_id, ArrayList<NationGroup> nation_groups){
		this.stateId = state_id;
		this.myNationGroups = nation_groups;
	}
	
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new SeparatedListAdapter(getActivity());
		
//		ArrayList<Nation> groupNations = TravelAPI.getGroupNations(myNationGroups.get(0).getId());
//		SampleAdapter adapter1 = new SampleAdapter(getActivity());
//		for (int j= 0; j< groupNations.size(); j++){
//			adapter1.add(new SampleItem(groupNations.get(j).getName()));
//			myNaions.add(groupNations.get(j));
//		}
//		adapter.addSection(myNationGroups.get(0).getName(), adapter1);
			
		for (int i=0; i< myNationGroups.size();i++){
			ArrayList<Nation> groupNations = TravelAPI.getGroupNations(myNationGroups.get(i).getId());
			SampleAdapter adapter1 = new SampleAdapter(getActivity());
			if(groupNations!=null){
				for (int j= 0; j< groupNations.size(); j++){
					adapter1.add(new SampleItem(groupNations.get(j).getName()));
					myNaions.add(groupNations.get(j));
				}		
				adapter.addSection(myNationGroups.get(i).getName(), adapter1);
			}
		}
		
		
		
		
//		SampleAdapter adapter2 = new SampleAdapter(getActivity());
//		
//		adapter2.add(new SampleItem("泰國"));
//		adapter.addSection("泰國", adapter2);
//		
//		SampleAdapter adapter3 = new SampleAdapter(getActivity());
//		
//		adapter3.add(new SampleItem("新加坡"));
//		adapter.addSection("新加坡", adapter3);
				
		setListAdapter(adapter);
			
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	   
//		String item = String.valueOf(position);
//		Toast.makeText( getActivity(), item, Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(getActivity(), LastCategoryActivity.class);
		getActivity().startActivity(intent);
		
		
	}

	private class SampleItem {
		public String tag;
		public SampleItem(String tag){
			this.tag = tag;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
				if (convertView == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_section_row, null);
				}
				
				TextView title = (TextView) convertView.findViewById(R.id.row_title);
				try{
					title.setText(getItem(position).tag);
				}catch(Exception e){
					
				}


			return convertView;
		}

	}
}
