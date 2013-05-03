package com.kosbrother.fragments;

import java.util.ArrayList;

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

import com.travel.story.LastCategoryActivity;
import com.travel.story.R;
import com.travel.story.entity.Area;

@SuppressLint("ValidFragment")
public class SecondCategoryListFragment extends ListFragment {

//	private SeparatedListAdapter adapter;
	private ArrayList<Area> myAreas = new ArrayList<Area>();
//	private int nationId;

	public SecondCategoryListFragment(int nation_id, ArrayList<Area> areas){
//		this.nationId = nation_id;
		this.myAreas = areas;
	}
	
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		SampleAdapter adapter1 = new SampleAdapter(getActivity());
		if(myAreas!=null){
			for (int j= 0; j< myAreas.size(); j++){
				adapter1.add(new SampleItem(myAreas.get(j).getName()));
			}		
		}				
		setListAdapter(adapter1);
			
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	   
		Intent intent = new Intent(getActivity(), LastCategoryActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("AreaId", myAreas.get(position).getId()); 
		bundle.putString("AreaName", myAreas.get(position).getName());
		intent.putExtras(bundle);
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
