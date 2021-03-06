package com.kosbrother.fragments;


import com.travel.story.CitysCategoryActivity;
import com.travel.story.CollectionActivity;
import com.travel.story.R;
import com.travel.story.SettingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public final class MyTravelFragment extends Fragment {

    public static MyTravelFragment newInstance() {
        MyTravelFragment fragment = new MyTravelFragment();
        return fragment;
    }

    private View         myFragmentView;
    private LinearLayout myCollection;
    private LinearLayout mySetting;
    private LinearLayout myTravelTheme;
    private LinearLayout myTravelSeason;
    private LinearLayout myTravelBeach;
    private LinearLayout myCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.layout_my_travel, container, false);
        findViews();
        setViews();
        return myFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void setViews() {

    	myCollection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });
    	mySetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

    	myTravelTheme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CitysCategoryActivity.class);
                Bundle bundle = new Bundle();
        		bundle.putInt("ThemeId", 2); 
        		intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    	
    	myTravelSeason.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CitysCategoryActivity.class);
                Bundle bundle = new Bundle();
        		bundle.putInt("ThemeId", 1); 
        		intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    	
    	myTravelBeach.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CitysCategoryActivity.class);
                Bundle bundle = new Bundle();
        		bundle.putInt("ThemeId", 3); 
        		intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    	myCity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(getActivity(), CitysCategoryActivity.class);
                Bundle bundle = new Bundle();
         		bundle.putInt("ThemeId", 4); 
         		intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    private void findViews() {
    	myCollection = (LinearLayout) myFragmentView.findViewById(R.id.my_collection);
    	mySetting = (LinearLayout) myFragmentView.findViewById(R.id.my_setting);
    	myTravelTheme = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_theme);
    	myTravelSeason = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_season);
    	myTravelBeach = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_beach);
    	myCity = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_city);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
