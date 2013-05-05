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
import com.taiwan.imageload.GridViewCollectSiteAdapter;
import com.taiwan.imageload.GridViewSiteAdapter;
import com.travel.story.ArticleActivity;
import com.travel.story.R;
import com.travel.story.SiteActivity;
import com.travel.story.db.SQLiteTravel;
import com.travel.story.entity.Site;

public class CollectionSiteFragment extends Fragment {

    private GridView            myGrid;
    private GridViewCollectSiteAdapter myGridViewAdapter;
    private ArrayList<Site>     sites;
    private LinearLayout layouNoData;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.grid, container, false);
        myGrid = (GridView) myFragmentView.findViewById(R.id.gridView1);
        layouNoData = (LinearLayout) myFragmentView.findViewById(R.id.layout_no_data);
        SQLiteTravel db = new SQLiteTravel(this.getActivity());
        sites = db.getAllSites();
        if(sites == null || sites.size() == 0){
        	layouNoData.setVisibility(View.VISIBLE);
        }else{
        	layouNoData.setVisibility(View.GONE);
	        myGridViewAdapter = new GridViewCollectSiteAdapter(getActivity(), sites);
	        myGrid.setAdapter(myGridViewAdapter);
        }
        
        myGrid.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
			{
				Intent intent = new Intent(getActivity(), SiteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("SiteId", sites.get(position).getId());
                bundle.putString("SiteName", sites.get(position).getName());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);	
			}      	
        });
              
        myGrid.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
            	final String[] ListStr = { "閱讀景點", "刪除", "取消" };
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setTitle(sites.get(position).getName());
            	builder.setItems(ListStr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0 ) {
                            // to song activity
                        	Intent intent = new Intent(getActivity(), SiteActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("SiteId", sites.get(position).getId());
                            bundle.putString("SiteName", sites.get(position).getName());
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);	
                        } else if(item == 1){
                            // delete
                        	SQLiteTravel db = new SQLiteTravel(getActivity());
                        	db.deleteSite(sites.get(position));
                        	sites = db.getAllSites();
                        	myGridViewAdapter = new GridViewCollectSiteAdapter(getActivity(), sites);
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
