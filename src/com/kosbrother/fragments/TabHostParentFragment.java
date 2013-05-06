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
    
    
    public TabHostParentFragment(){
 
    }
    
    public static final TabHostParentFragment newInstance(int caategory_id)
    {
    	TabHostParentFragment f = new TabHostParentFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("id", caategory_id);
        f.setArguments(bdl);
        return f;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	id = getArguments().getInt("id");
    	super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
			mTabHost = new FragmentTabHost(getActivity());
			mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_tabhost);
		
			arg1 = new Bundle();
			arg1.putInt("CategoryId", id);
			
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
