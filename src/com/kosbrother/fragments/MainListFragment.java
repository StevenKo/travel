package com.kosbrother.fragments;


import java.util.ArrayList;
import com.taiwan.imageload.ListAdapter;
import com.taiwan.imageload.ListStateAdapter;
import com.travel.story.CategoryActivity;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.State;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;



public class MainListFragment extends ListFragment {

//	private String[]  Category;
	private final Integer[]  mImageIds = { 
			R.drawable.icon_china,
			R.drawable.icon_asia,
			R.drawable.icon_europe,
			R.drawable.icon_oceania,
			R.drawable.icon_africa,
			R.drawable.icon_america,
			R.drawable.icon_south_america,
			R.drawable.icon_antartic
			};
	
	private ArrayList<State> myStates = new ArrayList<State>();
	
//  private ArrayList<String> categories = new ArrayList<String>();
//  private static Activity mActivity;
	
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
//    String[] values = new String[] { "經典武俠", "經典小說", "長篇",
//        "短篇" };
    
//    Category = getActivity().getResources().getStringArray(R.array.category_sections);
    
    myStates = TravelAPI.getStates();
    ListStateAdapter adapter = new ListStateAdapter(getActivity(), myStates, mImageIds);
   
    setListAdapter(adapter);
  }
  
  public static ListFragment newInstance(Activity myActivity) {
//	  mActivity = myActivity;
	  MainListFragment fragment = new MainListFragment();
      return fragment;
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    // Do something with the data
	  	Intent intent = new Intent(getActivity(), CategoryActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putInt("CategoryId", categories.get(position).getId()); 
//		bundle.putString("CategoryName", categories.get(position).getCateName());
//		intent.putExtras(bundle);
		getActivity().startActivity(intent);
  }

} 
