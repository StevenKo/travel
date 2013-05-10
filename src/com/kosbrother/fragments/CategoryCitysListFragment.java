package com.kosbrother.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.taiwan.imageload.ListAdapter;
import com.travel.story.R;

public class CategoryCitysListFragment extends ListFragment {
	
//	private String[] CONTENT;
	private  String[]  category;
	private ViewPager  pager;
	
  public CategoryCitysListFragment(){
	  
  }
  
  public static final CategoryCitysListFragment newInstance(String[] city_category)
  {
	  CategoryCitysListFragment f = new CategoryCitysListFragment();
	  Bundle bdl = new Bundle();
      bdl.putStringArray("CityCategory", city_category);
      f.setArguments(bdl);
      return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {		
	  pager = (ViewPager) getActivity().findViewById(R.id.pager);
	  category = getArguments().getStringArray("CityCategory");
	  super.onCreate(savedInstanceState);
  }
  
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    
    ListAdapter adapter = new ListAdapter(getActivity(), category, null);
    setListAdapter(adapter);
    
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
	  
	  pager.setCurrentItem(position+1);
	  
  }

} 
