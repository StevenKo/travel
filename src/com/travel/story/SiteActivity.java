package com.travel.story;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;
import com.taiwan.imageload.ImageLoader;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Site;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.story.db.SQLiteTravel;


public class SiteActivity extends SherlockActivity implements AdWhirlInterface {
	
	private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_COLLECTION = 4;
	
    private ImageView imageView;
    private Site mySite;
    private String[] myPics;
    private ActionBar ab;
    private Bundle mBundle;
    private int siteId;
    private String siteName;
    public ImageLoader    imageLoader;
    private Gallery myGallery;
    
    private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
//	private TextView textSiteInfo;
//	private TextView textSiteIntroduce;
    private WebView webviewSiteInfo;
    private WebView webviewSiteIntro;
    private TextView textSiteName;
    private AlertDialog.Builder aboutUsDialog;
    private SQLiteTravel     db;
    private CheckBox         checkboxFavorite;
    
    private final String        adWhirlKey  = "8c0c4844165c467490f058cc4ea09118";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_site);
        db = new SQLiteTravel(this);
        layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        layoutReload = (LinearLayout) findViewById(R.id.layout_reload);
        imageView = (ImageView) findViewById(R.id.ImageView01);
        textSiteName = (TextView) findViewById(R.id.text_site_name);
        // textSiteInfo = (TextView) findViewById (R.id.text_site_info);
        // textSiteIntroduce = (TextView) findViewById (R.id.text_site_introduce);
        webviewSiteInfo = (WebView) findViewById(R.id.webview_site_info);
        webviewSiteIntro = (WebView) findViewById(R.id.webview_site_introduce);
        checkboxFavorite = (CheckBox) findViewById(R.id.checkbox_article);
        myGallery = (Gallery) findViewById(R.id.Gallery01);
        imageLoader = new ImageLoader(SiteActivity.this, 90);

        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mBundle = this.getIntent().getExtras();
        siteId = mBundle.getInt("SiteId");
        siteName = mBundle.getString("SiteName");
        ab.setTitle(siteName);

        new DownloadSiteTask().execute();

        
        setAboutDialog();
        



        myGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (myPics.length == 0) {
                    imageView.setImageResource(R.drawable.app_icon);
                } else {
                    imageLoader.DisplayImage(myPics[position], imageView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


        		}
        	}
        );      	
  
            


        checkboxFavorite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (checkboxFavorite.isChecked()) {
                    db.insertSite(mySite);
                    Toast.makeText(SiteActivity.this, "加入我的收藏", Toast.LENGTH_SHORT).show();
                } else {
                    db.deleteSite(mySite);
                    Toast.makeText(SiteActivity.this, "從我的收藏移除", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (db.isSiteCollected(siteId)) {
            checkboxFavorite.setChecked(true);
        }
        
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

            mySite = TravelAPI.getSite(siteId);
            myPics = new String[mySite.getPics().length];
            myPics = mySite.getPics();
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            layoutProgress.setVisibility(View.GONE);

            if (mySite != null) {
                setUIAfterLoading();
            } else {
                // 重試
                layoutReload.setVisibility(View.VISIBLE);
                // Toast.makeText(getApplicationContext(), "無資料,請重試！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUIAfterLoading() {
    	
    	int firstPosition = myPics.length ;
        if(firstPosition!=0 && firstPosition % 2 == 0){
        	firstPosition = firstPosition /2 -1;
        }else{
        	firstPosition = firstPosition /2 ;
        }
    	
    	if(myPics.length == 1){
    		myGallery.setVisibility(View.GONE);
    	}else{
	        myGallery.setAdapter(new ImageAdapter(this));	        
	        myGallery.setSelection(firstPosition, true);
    	}
        
        if (myPics.length == 0 || myPics[0].equals("") || myPics[0].equals("null")) {
//            imageView.setImageResource(R.drawable.app_icon);
            myGallery.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        } else {
            imageLoader.DisplayImage(myPics[firstPosition], imageView);
        }

        String text = "<font color=#404040>" + mySite.getName() + "</font>";
        textSiteName.setText(Html.fromHtml(text));

        // textSiteInfo.setText(Html.fromHtml(mySite.getInfo()));
        // textSiteIntroduce.setText(Html.fromHtml(mySite.getIntroduction()));

        webviewSiteInfo.loadDataWithBaseURL(null, mySite.getInfo(), "text/html", "UTF-8", null);
        webviewSiteIntro.loadDataWithBaseURL(null, mySite.getIntroduction(), "text/html", "UTF-8", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

//        getMenuInflater().inflate(R.menu.activity_main, menu);
		
    	menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_COLLECTION, 4, "我的收藏").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
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
            Intent intent = new Intent(SiteActivity.this, SettingActivity.class);
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
	    case ID_COLLECTION:
	    	Intent intent2 = new Intent(SiteActivity.this, CollectionActivity.class);
	    	startActivity(intent2);
	        break;
        }
        return true;
    }

    public class ImageAdapter extends BaseAdapter {

        private final Context ctx;
        int                   imageBackground;

        public ImageAdapter(Context c) {
            ctx = c;
            TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
            imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
            ta.recycle();
        }

        @Override
        public int getCount() {

            return myPics.length;
        }

        @Override
        public Object getItem(int arg0) {

            return arg0;
        }

        @Override
        public long getItemId(int arg0) {

            return arg0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            ImageView iv = new ImageView(ctx);
            // iv.setImageResource(pics[position]);

            if (myPics.length == 0) {
                iv.setImageResource(R.drawable.app_icon);
            } else {
                imageLoader.DisplayImage(myPics[position], iv);
            }

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
            iv.setBackgroundResource(imageBackground);
            return iv;
        }

    }
	
	private void setAboutDialog() {
        // TODO Auto-generated method stub
        aboutUsDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.about_us_string)).setIcon(R.drawable.app_icon)
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
