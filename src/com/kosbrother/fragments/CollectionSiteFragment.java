package com.kosbrother.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.taiwan.imageload.GridViewSiteAdapter;
import com.travel.story.R;
import com.travel.story.db.SQLiteTravel;
import com.travel.story.entity.Site;

public class CollectionSiteFragment extends Fragment {

    private GridView            myGrid;
    private GridViewSiteAdapter myGridViewAdapter;
    private ArrayList<Site>     sites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.grid, container, false);
        myGrid = (GridView) myFragmentView.findViewById(R.id.gridView1);
        SQLiteTravel db = new SQLiteTravel(this.getActivity());
        sites = db.getAllSites();
        myGridViewAdapter = new GridViewSiteAdapter(getActivity(), sites);
        myGrid.setAdapter(myGridViewAdapter);
        return myFragmentView;
    }

}
