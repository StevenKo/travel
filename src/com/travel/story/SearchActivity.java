package com.travel.story;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.kosbrother.fragments.CategorySiteFragment;
import com.kosbrother.fragments.CategoryTravelNoteFragment;
import com.taiwan.imageload.GridViewAdapter;
import com.taiwan.imageload.GridViewSiteAdapter;
import com.taiwan.imageload.LoadMoreGridView;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Note;
import com.travel.story.entity.Site;

public class SearchActivity extends SherlockActivity {
	
	private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
	
    private AlertDialog.Builder aboutUsDialog;
    private Bundle mBundle;
	private int searchTypeId;
	private String searchName;
    
	private LinearLayout progressLayout;
	private LinearLayout reloadLayout;
	private LinearLayout  loadmoreLayout;
	private Button buttonReload;
	private LoadMoreGridView       mGridView;
	
	private Boolean          checkLoad  = true;
    private int       myPage     = 1;
    
    private ArrayList<Site> mSites = new ArrayList<Site>();
    private ArrayList<Site> moreSites = new ArrayList<Site>();
    private ArrayList<Note> mNotes = new ArrayList<Note>();
    private ArrayList<Note> moreNotes = new ArrayList<Note>();
    
    private GridViewAdapter mNoteAdapter;
    private GridViewSiteAdapter mSiteAdapter;
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		
		mBundle = this.getIntent().getExtras();
        searchTypeId = mBundle.getInt("SearchTypeId");
        searchName = mBundle.getString("SearchKeyword");
        
        if(searchTypeId == 0){       
        	setContentView(R.layout.loadmore_grid);
        	setTitle("遊記搜索 >"+searchName);
        	progressLayout = (LinearLayout) findViewById(R.id.layout_progress);
        	reloadLayout = (LinearLayout) findViewById(R.id.layout_reload);
        	loadmoreLayout = (LinearLayout) findViewById(R.id.load_more_grid);
        	buttonReload = (Button) findViewById(R.id.button_reload);
        	mGridView = (LoadMoreGridView) findViewById(R.id.news_list);       	
        	mGridView.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
                public void onLoadMore() {
                    if (checkLoad) {
                        myPage = myPage + 1;
                        loadmoreLayout.setVisibility(View.VISIBLE);
                        new LoadMoreTask().execute();
                    } else {
                    	mGridView.onLoadMoreComplete();
                    }
                }
            });
        }else if(searchTypeId == 1){
        	setContentView(R.layout.loadmore_grid);
        	setTitle("景點搜索 >"+searchName);
        	progressLayout = (LinearLayout) findViewById(R.id.layout_progress);
        	reloadLayout = (LinearLayout) findViewById(R.id.layout_reload);
        	loadmoreLayout = (LinearLayout) findViewById(R.id.load_more_grid);
        	buttonReload = (Button) findViewById(R.id.button_reload);
        	mGridView = (LoadMoreGridView) findViewById(R.id.news_list);       	
        	mGridView.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
                public void onLoadMore() {
                    if (checkLoad) {
                        myPage = myPage + 1;
                        loadmoreLayout.setVisibility(View.VISIBLE);
                        new LoadMoreTask().execute();
                    } else {
                    	mGridView.onLoadMoreComplete();
                    }
                }
            });      	
        }
        
        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                progressLayout.setVisibility(View.VISIBLE);
                reloadLayout.setVisibility(View.GONE);
                new DownloadChannelsTask().execute();
            }
        });
        
      new DownloadChannelsTask().execute();  
        
		setAboutUsDialog();
	
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		
    	menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		
        return true;
    }
	
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
            finish();
            // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
            break;
        case ID_SETTING: // setting
            Intent intent = new Intent(SearchActivity.this, SettingActivity.class);
            startActivity(intent);
            break;
        case ID_RESPONSE: // response
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { getResources().getString(R.string.respond_mail_address) });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.respond_mail_title));
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            break;
        case ID_ABOUT_US:
            aboutUsDialog.show();
            break;
        case ID_GRADE:
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.recommend_url)));
            startActivity(browserIntent);
            break;
	    }
	    return true;
	}
    
    private void setAboutUsDialog() {
        // TODO Auto-generated method stub
        aboutUsDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.about_us_string)).setIcon(R.drawable.play_store_icon)
                .setMessage(getResources().getString(R.string.about_us))
                .setPositiveButton(getResources().getString(R.string.yes_string), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }
    
    
    private class DownloadChannelsTask extends AsyncTask {


        @Override
        protected Object doInBackground(Object... params) {  	
        	if(searchTypeId == 0){       
        		mNotes = TravelAPI.searchNotes(searchName, myPage);         	
            }else if(searchTypeId == 1){
            	mSites = TravelAPI.searchSites(searchName, myPage);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);
            
             if(searchTypeId == 0){
           	
            	if(mNotes!= null && mNotes.size()!=0){
            		mNoteAdapter = new GridViewAdapter(SearchActivity.this, mNotes);
            		mGridView.setAdapter(mNoteAdapter);
                }else{
                	reloadLayout.setVisibility(View.VISIBLE);        	
                }       
            	
            }else if(searchTypeId == 1){          	
                if (mSites != null && mSites.size()!=0) {
                	mSiteAdapter = new GridViewSiteAdapter(SearchActivity.this, mSites);
                	mGridView.setAdapter(mSiteAdapter);
                } else {
                	reloadLayout.setVisibility(View.VISIBLE);   
                }
            }

        }
    }
    
    private class LoadMoreTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub
        	
        	
        	if(searchTypeId == 0){       
        		moreNotes = TravelAPI.searchNotes(searchName, myPage);
            	if (moreNotes != null && moreNotes.size()!=0) {
                    for (int i = 0; i < moreNotes.size(); i++) {
                    	mNotes.add(moreNotes.get(i));
                    }
                }
            }else if(searchTypeId == 1){
            	moreSites = TravelAPI.searchSites(searchName, myPage);
            	if (moreSites != null && moreSites.size()!=0) {
                    for (int i = 0; i < moreSites.size(); i++) {
                    	mSites.add(moreSites.get(i));
                    }
                }
            }
        	
        	

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            
            loadmoreLayout.setVisibility(View.GONE);
            
             if(searchTypeId == 0){
           	
            	if(moreNotes!= null && moreNotes.size()!=0){
            		mNoteAdapter.notifyDataSetChanged();	                
                }else{
                    checkLoad= false;
                    Toast.makeText(SearchActivity.this, "no more data", Toast.LENGTH_SHORT).show();            	
                }       
            	mGridView.onLoadMoreComplete();
            	
            }else if(searchTypeId == 1){
            	
                if (moreSites != null && moreSites.size()!=0) {
                	mSiteAdapter.notifyDataSetChanged();
                } else {
                    checkLoad = false;
                    Toast.makeText(SearchActivity.this, "no more data", Toast.LENGTH_SHORT).show();
                }
                mGridView.onLoadMoreComplete();
            }

        }
    }

}
