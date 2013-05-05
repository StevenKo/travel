package com.travel.story;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.kosbrother.fragments.SecondCategoryListFragment;
import com.kosbrother.fragments.SecondTabHostParentFragment;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Area;
import com.viewpagerindicator.TitlePageIndicator;

public class SecondCategoryActivity extends SherlockFragmentActivity {

    private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_SEARCH   = 5;
    

    private Bundle    mBundle;
    private int    nationId;
    private String nationName;
    private ArrayList<Area> myAreas = new ArrayList<Area>();
    
    private ViewPager           pager;
    private AlertDialog.Builder aboutUsDialog;

    private LinearLayout layoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);
        layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        
        mBundle = this.getIntent().getExtras();
        nationId = mBundle.getInt("NationId");
        nationName = mBundle.getString("NationName");
        
        
        
        final ActionBar ab = getSupportActionBar();
        ab.setTitle(nationName);
        ab.setDisplayHomeAsUpEnabled(true);
        
        new DownloadChannelsTask().execute();
        
        

        setAboutUsDialog();

//        try {
//            Display display = getWindowManager().getDefaultDisplay();
//            int width = display.getWidth(); // deprecated
//            int height = display.getHeight(); // deprecated
//
//            if (width > 320) {
//                setAdAdwhirl();
//            }
//        } catch (Exception e) {
//
//        }

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);

        menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);      
        menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


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
            Intent intent = new Intent(SecondCategoryActivity.this, SettingActivity.class);
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
        case ID_SEARCH: // response
        	Intent intent3 = new Intent(SecondCategoryActivity.this, TabSearchActivity.class);
            startActivity(intent3);
            break;
        }
        return true;
    }

    class NovelPagerAdapter extends FragmentPagerAdapter {
        public NovelPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            if (position == 0) {
                kk = new SecondCategoryListFragment(nationId, myAreas);
            } else {
                kk = new SecondTabHostParentFragment(myAreas.get(position-1).getId());
            }  
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	if(position==0){
        		String a = "分類";
        		return a;
        	}else{
        		return myAreas.get(position-1).getName();
        	}
        }

        @Override
        public int getCount() {
            return myAreas.size()+1;
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (pager.getCurrentItem() == 1) {
//            finish();
//        } else {
//            pager.setCurrentItem(1, true);
//        }
//    }

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
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            layoutProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub

        	myAreas = TravelAPI.getNationAreas(nationId);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            layoutProgress.setVisibility(View.GONE);
            
            FragmentPagerAdapter adapter = new NovelPagerAdapter(getSupportFragmentManager());

            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
            indicator.setViewPager(pager);

            pager.setCurrentItem(1);
            
        }
    }

}
