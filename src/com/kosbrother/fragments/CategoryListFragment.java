package com.kosbrother.fragments;


import com.taiwan.imageload.SeparatedListAdapter;
import com.travel.story.LastCategoryActivity;
import com.travel.story.R;

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

public class CategoryListFragment extends ListFragment {

	private SeparatedListAdapter adapter;

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new SeparatedListAdapter(getActivity());
		SampleAdapter adapter1 = new SampleAdapter(getActivity());	
		adapter1.add(new SampleItem("日本"));
		adapter1.add(new SampleItem("韓國"));
		adapter1.add(new SampleItem("朝鮮"));
		adapter.addSection("日本,韓國,朝鮮", adapter1);
		
		SampleAdapter adapter2 = new SampleAdapter(getActivity());
		
		adapter2.add(new SampleItem("泰國"));
		adapter.addSection("泰國", adapter2);
		
		SampleAdapter adapter3 = new SampleAdapter(getActivity());
		
		adapter3.add(new SampleItem("新加坡"));
		adapter.addSection("新加坡", adapter3);
				
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
