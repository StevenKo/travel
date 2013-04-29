package com.kosbrother.fragments;


import java.util.ArrayList;
import com.taiwan.imageload.ListAdapter;
import com.travel.story.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;



public class CategoryListFragment extends ListFragment {

	private String[]  Category;
	
//  private ArrayList<String> categories = new ArrayList<String>();
//  private static Activity mActivity;
	
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
//    String[] values = new String[] { "經典武俠", "經典小說", "長篇",
//        "短篇" };
    
    Category = getActivity().getResources().getStringArray(R.array.category_sections); 
    ListAdapter adapter = new ListAdapter(getActivity(), Category);
   
    setListAdapter(adapter);
  }
  
  public static ListFragment newInstance(Activity myActivity) {
//	  mActivity = myActivity;
	  CategoryListFragment fragment = new CategoryListFragment();
      return fragment;
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    // Do something with the data
//	  	Intent intent = new Intent(getActivity(), CategoryActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putInt("CategoryId", categories.get(position).getId()); 
//		bundle.putString("CategoryName", categories.get(position).getCateName());
//		intent.putExtras(bundle);
//		getActivity().startActivity(intent);
  }

} 
