package com.kosbrother.fragments;

import com.travel.story.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

@SuppressLint("ValidFragment")
public class TabHostParentFragment extends Fragment {
	
    private FragmentTabHost mTabHost;
    private int id;
    private Bundle arg1;
    
    public TabHostParentFragment(int id){
    	this.id = id;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
		mTabHost = new FragmentTabHost(getActivity());
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_tabhost);
	
		arg1 = new Bundle();
		arg1.putInt("CategoryId", id);
		
//		mTabHost.addTab(mTabHost.newTabSpec("TabHostTextView1").setIndicator("Child 1"),
//			TextViewFragment.class, arg1);
//	
//		Bundle arg2 = new Bundle();
//		arg2.putInt(TextViewFragment.POSITION_KEY, 2);
//		mTabHost.addTab(mTabHost.newTabSpec("TabHostTextView2").setIndicator("Child 2"),
//			TextViewFragment.class, arg2);
		
//		mTabHost.addTab(mTabHost.newTabSpec("View1").setIndicator("遊記"),
//				CategoryTravelNoteFragment.class, null);
//		
//		mTabHost.addTab(mTabHost.newTabSpec("View2").setIndicator("景點"),
//				CategorySiteFragment.class, null);
		
		setupTab(CategoryTravelNoteFragment.class, "遊記", "View1");
		setupTab(CategorySiteFragment.class, "景點", "View2");

		return mTabHost;
    }
    
    private void setupTab(Class<?> ccls, String name, String nameSpec) {

        getActivity().getLayoutInflater();
		View tab = LayoutInflater.from(getActivity()).inflate(R.layout.item_tab, null);
        TextView text = (TextView) tab.findViewById(R.id.text);
        text.setText(name);
        mTabHost.addTab(mTabHost.newTabSpec(nameSpec).setIndicator(tab),ccls, arg1);

    }
    
    @Override
    public void onDestroyView() {
	super.onDestroyView();
		mTabHost = null;
    }
}
