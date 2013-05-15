package com.travel.story;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;
import com.kosbrother.tool.ExpandableHeightGridView;
import com.taiwan.imageload.GridViewAreaIntroAdapter;
import com.taiwan.imageload.GridViewNationIntroAdapter;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.AreaIntro;
import com.travel.story.entity.AreaIntroCategory;

public class AreaIntroActivity extends SherlockActivity implements AdWhirlInterface {

    private static final int     ID_SETTING   = 0;
    private static final int     ID_RESPONSE  = 1;
    private static final int     ID_ABOUT_US  = 2;
    private static final int     ID_GRADE     = 3;

    private LinearLayout         layoutProgress;
    private LinearLayout         layoutReload;
    private LinearLayout         layoutIntroCategoryContent;
    private LinearLayout     	 layoutNodata;

    // private final ArrayList<AreaIntroCategory> myAreaIntroCategory = new ArrayList<AreaIntroCategory>();
    private ArrayList<AreaIntro> myAreaIntros = new ArrayList<AreaIntro>();

    private Bundle               mBundle;
    private String               areaName;
    private int                  areaId;
    private AlertDialog.Builder  aboutUsDialog;
    private int typeId;	// 0 for nation, 1 for area
    private int nationId;
    private String nationName;
    
    private final String        adWhirlKey  = "8c0c4844165c467490f058cc4ea09118";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_intro_category);
        layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        layoutReload = (LinearLayout) findViewById(R.id.layout_reload);
        layoutIntroCategoryContent = (LinearLayout) findViewById(R.id.layout_intro_category_content);
        layoutNodata = (LinearLayout) findViewById(R.id.layout_no_data);
        final ActionBar ab = getSupportActionBar();
        
        mBundle = this.getIntent().getExtras();
        typeId = mBundle.getInt("TypeId");
        if(typeId == 0){
        	nationId = mBundle.getInt("NationId");
        	nationName = mBundle.getString("NationName");
        	ab.setTitle("簡介:" + nationName);
        }else{
        	areaId = mBundle.getInt("AreaId");
        	areaName = mBundle.getString("AreaName");
        	ab.setTitle("簡介:" + areaName);
        }
             
        ab.setDisplayHomeAsUpEnabled(true);

        new DownloadSiteTask().execute();

        setAboutUsDialog();
        
        try {
            Display display = getWindowManager().getDefaultDisplay();
           int width = display.getWidth(); // deprecated
           int height = display.getHeight(); // deprecated
          
           if (width > 320) {
           setAdAdwhirl();
           }
           } catch (Exception e) {
          
           }
    }

    private class DownloadSiteTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {

            // myAreaIntroCategory = TravelAPI.getAreaIntroCategories();
        	if(typeId == 0){
        		myAreaIntros = TravelAPI.getNationIntros(nationId);
        	}else{
        		myAreaIntros = TravelAPI.getAreaIntros(areaId);
        	}
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            layoutProgress.setVisibility(View.GONE);

            if (myAreaIntros != null) {
            	if(myAreaIntros.size()!=0){
            		setUIAfterLoading();
            	}else{
            		layoutNodata.setVisibility(View.VISIBLE);
            	}
            } else {
                // 重試
                layoutReload.setVisibility(View.VISIBLE);
                // Toast.makeText(getApplicationContext(), "無資料,請重試！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUIAfterLoading() {

        TreeMap map = new TreeMap<Integer, ArrayList<AreaIntro>>();

        for (int i = 0; i < myAreaIntros.size(); i++) {
            if (map.containsKey(myAreaIntros.get(i).getAreaIntroCateId())) {
                ((ArrayList<AreaIntro>) map.get(myAreaIntros.get(i).getAreaIntroCateId())).add(myAreaIntros.get(i));
            } else {
                ArrayList<AreaIntro> list = new ArrayList<AreaIntro>(10);
                list.add(myAreaIntros.get(i));
                map.put(myAreaIntros.get(i).getAreaIntroCateId(), list);
            }
        }

        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            Integer key = (Integer) i.next();
            ArrayList<AreaIntro> value = (ArrayList<AreaIntro>) map.get(key);
            TextView textImage = new TextView(this);
            textImage.setText(AreaIntroCategory.getCategoryName(key));
            layoutIntroCategoryContent.addView(textImage);
            LinearLayout liearGridLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_grids, null);
            ExpandableHeightGridView mGridView = (ExpandableHeightGridView) liearGridLayout.findViewById(R.id.grids);
            mGridView.setExpanded(true);
            if(typeId == 0){
            	GridViewNationIntroAdapter  myGridViewAdapter = new GridViewNationIntroAdapter(this, value);
            	mGridView.setAdapter(myGridViewAdapter);
            }else{
            	GridViewAreaIntroAdapter myGridViewAdapter = new GridViewAreaIntroAdapter(this, value);
            	mGridView.setAdapter(myGridViewAdapter);
            }
            layoutIntroCategoryContent.addView(liearGridLayout);

        }

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
            Intent intent = new Intent(AreaIntroActivity.this, SettingActivity.class);
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
    
    private void setAdAdwhirl() {
        // TODO Auto-generated method stub
        AdWhirlManager.setConfigExpireTimeout(1000 * 60);
        AdWhirlTargeting.setAge(23);
        AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
        AdWhirlTargeting.setKeywords("online games gaming");
        AdWhirlTargeting.setPostalCode("94123");
        AdWhirlTargeting.setTestMode(false);

        AdWhirlLayout adwhirlLayout = new AdWhirlLayout(this, adWhirlKey);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.adonView);

        adwhirlLayout.setAdWhirlInterface(this);

        mainLayout.addView(adwhirlLayout);

        mainLayout.invalidate();
    }

    @Override
    public void adWhirlGeneric() {
        // TODO Auto-generated method stub

    }

    public void rotationHoriztion(int beganDegree, int endDegree, AdView view) {
        final float centerX = 320 / 2.0f;
        final float centerY = 48 / 2.0f;
        final float zDepth = -0.50f * view.getHeight();

        Rotate3dAnimation rotation = new Rotate3dAnimation(beganDegree, endDegree, centerX, centerY, zDepth, true);
        rotation.setDuration(1000);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(rotation);
    }

}
