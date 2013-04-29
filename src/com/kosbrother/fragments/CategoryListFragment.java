package com.kosbrother.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.taiwan.imageload.ListAdapter;
import com.travel.story.CategoryActivity;
import com.travel.story.LastCategoryActivity;
import com.travel.story.R;

public class CategoryListFragment extends ListFragment {

	private  String[]  Category;
	private final Integer[]  mImageIds = { 
			R.drawable.icon_season,  
			R.drawable.icon_beach, 
			R.drawable.icon_china,
			R.drawable.icon_asia,
			R.drawable.icon_europe,
			R.drawable.icon_others
			};
	
//  private ArrayList<String> categories = new ArrayList<String>();
//  private static Activity mActivity;
	
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ListAdapter adapter = new ListAdapter(getActivity(), Category, null);
    setListAdapter(adapter);
    
  }
  
  
  public CategoryListFragment(String[] catetories){
	  Category = catetories;
  }


  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    // Check if go to lastCategoryActivity   
	  	Intent intent = new Intent(getActivity(), LastCategoryActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putInt("CategoryId", categories.get(position).getId()); 
//		bundle.putString("CategoryName", categories.get(position).getCateName());
//		intent.putExtras(bundle);
		getActivity().startActivity(intent);
  }

} 
