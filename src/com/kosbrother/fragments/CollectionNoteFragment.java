package com.kosbrother.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.taiwan.imageload.GridViewAdapter;
import com.travel.story.R;
import com.travel.story.db.SQLiteTravel;
import com.travel.story.entity.Note;

public class CollectionNoteFragment extends Fragment {

    private GridView        myGrid;
    private GridViewAdapter myGridViewAdapter;
    private ArrayList<Note> notes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.grid, container, false);
        myGrid = (GridView) myFragmentView.findViewById(R.id.gridView1);
        SQLiteTravel db = new SQLiteTravel(this.getActivity());
        notes = db.getAllNotes();
        myGridViewAdapter = new GridViewAdapter(getActivity(), notes);
        myGrid.setAdapter(myGridViewAdapter);
        return myFragmentView;
    }

}
