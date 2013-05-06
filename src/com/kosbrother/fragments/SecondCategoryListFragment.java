package com.kosbrother.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.taiwan.imageload.ListIntroAdapter;
import com.travel.story.R;
import com.travel.story.entity.Area;

@SuppressLint("ValidFragment")
public class SecondCategoryListFragment extends Fragment {

//	private SeparatedListAdapter adapter;
	private ArrayList<Area> myAreas = new ArrayList<Area>();
//	private int nationId;

	public SecondCategoryListFragment(int nation_id, ArrayList<Area> areas){
//	
		this.myAreas = areas;
	}
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView myFragmentView = (ListView)inflater.inflate(R.layout.list, container, false);
        ListIntroAdapter adapter = new ListIntroAdapter(getActivity(), myAreas);
        myFragmentView.setAdapter(adapter);
        
        return myFragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
	

	
}
