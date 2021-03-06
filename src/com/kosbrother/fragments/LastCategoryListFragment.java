package com.kosbrother.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.taiwan.imageload.ListAdapter;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;


@SuppressLint("ValidFragment")
public class LastCategoryListFragment extends ListFragment {
	
	private String[] CONTENT;
	private  String[]  category;
	private ViewPager  pager;
	
  public LastCategoryListFragment(){
	  
  }
  
  public static final LastCategoryListFragment newInstance()
  {
	  LastCategoryListFragment f = new LastCategoryListFragment();
      return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {		
	  pager = (ViewPager) getActivity().findViewById(R.id.pager);
	  super.onCreate(savedInstanceState);
  }
  
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    
    CONTENT = getActivity().getResources().getStringArray(R.array.last_sections);
    
    // 去掉"類別"
    category = new String[CONTENT.length-1];   
	for(int i=0; i<category.length;i++){
		category[i] =  CONTENT[i+1];
	}
    
    ListAdapter adapter = new ListAdapter(getActivity(), category, null);
    setListAdapter(adapter);
    
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
	  
	  pager.setCurrentItem(position+1);
	  
  }

} 