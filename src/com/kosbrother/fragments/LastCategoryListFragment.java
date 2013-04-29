package com.kosbrother.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.taiwan.imageload.ListAdapter;


@SuppressLint("ValidFragment")
public class LastCategoryListFragment extends ListFragment {

	private  String[]  Category;
	private ViewPager  pager;
	
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ListAdapter adapter = new ListAdapter(getActivity(), Category, null);
    setListAdapter(adapter);
    
  }
  
  
  public LastCategoryListFragment(String[] catetories, ViewPager inputPager){
	  Category = catetories;
	  pager = inputPager;
  }


  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
	  
	  pager.setCurrentItem(position+1);
	  
  }

} 