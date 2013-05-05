package com.travel.story;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.kosbrother.fragments.LastCategoryNoteFragment;
import com.kosbrother.fragments.LastCategoryListFragment;
import com.kosbrother.fragments.LastCategorySiteFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class LastCategoryActivity extends SherlockFragmentActivity {

    private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_AREA_INTRO = 4;
    private static final int    ID_SEARCH   = 5;

    private String[]            CONTENT = {
    		"類別",
            "最佳遊記",
    		"最新遊記",
    		"最多瀏覽數",
    		"景點"
    	};
    private ViewPager           pager;
    private AlertDialog.Builder aboutUsDialog;
    
    private Bundle    mBundle;
    private String    areaName;
    private int       areaId;

    private final String        adWhirlKey  = "215f895eb71748e7ba4cb3a5f20b061e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Setting.setApplicationActionBarTheme(this);
        setContentView(R.layout.simple_titles);

        mBundle = this.getIntent().getExtras();
        areaId = mBundle.getInt("AreaId");
        areaName = mBundle.getString("AreaName");
        
        final ActionBar ab = getSupportActionBar();
        ab.setTitle(areaName);
        ab.setDisplayHomeAsUpEnabled(true);
        
        FragmentPagerAdapter adapter = new NovelPagerAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        pager.setCurrentItem(1);

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
        menu.add(0, ID_AREA_INTRO, 5, "簡介").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case ID_SETTING: // setting
            Intent intent = new Intent(LastCategoryActivity.this, SettingActivity.class);
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
        	Intent intent3 = new Intent(LastCategoryActivity.this, TabSearchActivity.class);
            startActivity(intent3);
            break;
        case  ID_AREA_INTRO:
        	Intent intent2 = new Intent(LastCategoryActivity.this, AreaIntroActivity.class);
    		Bundle bundle = new Bundle();
    		bundle.putInt("AreaId", areaId); 
    		bundle.putString("AreaName", areaName);
    		intent2.putExtras(bundle);
    		startActivity(intent2);
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
            	String[] categories = new String[CONTENT.length-1];
            	for(int i=0; i<categories.length;i++){
            		categories[i] =  CONTENT[i+1];
            	}
                kk = new LastCategoryListFragment(categories, pager);
            }else if(position == 4){
            	kk = new LastCategorySiteFragment(areaId);
            } else {
                kk = new LastCategoryNoteFragment(areaId, position);
            } 
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }

        @Override
        public int getCount() {
            return CONTENT.length;
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

}