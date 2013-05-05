package com.kosbrother.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import com.taiwan.imageload.GridViewCollectNoteAdapter;
import com.travel.story.ArticleActivity;
import com.travel.story.R;
import com.travel.story.db.SQLiteTravel;
import com.travel.story.entity.Note;

public class CollectionNoteFragment extends Fragment {

    private GridView        myGrid;
    private GridViewCollectNoteAdapter myGridViewAdapter;
    private ArrayList<Note> notes;
    private LinearLayout layouNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.grid, container, false);
        myGrid = (GridView) myFragmentView.findViewById(R.id.gridView1);
        layouNoData = (LinearLayout) myFragmentView.findViewById(R.id.layout_no_data);
        SQLiteTravel db = new SQLiteTravel(this.getActivity());
        notes = db.getAllNotes();
        if(notes == null || notes.size() == 0){
        	layouNoData.setVisibility(View.VISIBLE);
        }else{
        	layouNoData.setVisibility(View.GONE);
        	myGridViewAdapter = new GridViewCollectNoteAdapter(getActivity(), notes);
        	myGrid.setAdapter(myGridViewAdapter);
        }
        
        
        
        myGrid.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
			{
				Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("NoteId", notes.get(position).getId());
                bundle.putString("NoteTitle", notes.get(position).getTitle());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);		
			}      	
        });
              
        myGrid.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
            	final String[] ListStr = { "閱讀遊記", "刪除", "取消" };
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setTitle(notes.get(position).getTitle());
            	builder.setItems(ListStr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0 ) {
                            // to song activity
                        	Intent intent = new Intent(getActivity(), ArticleActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("NoteId", notes.get(position).getId());
                            bundle.putString("NoteTitle", notes.get(position).getTitle());
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                        } else if(item == 1){
                            // delete
                        	SQLiteTravel db = new SQLiteTravel(getActivity());
                        	db.deleteNote(notes.get(position));
                        	notes = db.getAllNotes();
                        	myGridViewAdapter = new GridViewCollectNoteAdapter(getActivity(), notes);
                        	myGrid.setAdapter(myGridViewAdapter);
                        } else if(item == 2){
                        	//do nothing
                        	dialog.dismiss();
                        }
                    }
                });
            	AlertDialog alert = builder.create();
                alert.show();
            	
                return false;
            }
        });
        
        return myFragmentView;
    }

}
