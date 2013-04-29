package com.kosbrother.fragments;

import java.io.File;

import com.travel.story.R;

import android.app.ProgressDialog;
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
    private LinearLayout myTravleTheme;
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
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("IS_RECNET", false);
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(getActivity(), BookmarkActivity.class);
//                startActivity(intent);
            }
        });
    	mySetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("IS_RECNET", true);
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(getActivity(), BookmarkActivity.class);
//                startActivity(intent);
            }
        });

    	myTravleTheme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyNovelActivity.class);
//                startActivity(intent);
            }
        });

    	myCity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SettingActivity.class);
//                startActivity(intent);
            }
        });


    }

    private void findViews() {
    	myCollection = (LinearLayout) myFragmentView.findViewById(R.id.my_collection);
    	mySetting = (LinearLayout) myFragmentView.findViewById(R.id.my_setting);
    	myTravleTheme = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_theme);
    	myCity = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_city);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
