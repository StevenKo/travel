package com.travel.story;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.AreaIntro;

public class AreaIntroContentActivity extends SherlockActivity implements AdWhirlInterface {
	
    
	private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_COLLECTION = 4;
    
	private WebView articleWebView;
	
	private AreaIntro myAreaIntro; // uset to get article text
	private Bundle mBundle;
	
	private ActionBar ab;
	private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
	
	private int areaIntroId;
	private String areaIntroTitle;
	
	private AlertDialog.Builder aboutUsDialog;
	private final String        adWhirlKey  = "8c0c4844165c467490f058cc4ea09118";
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layou_intro_content); 
        
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        
        mBundle = this.getIntent().getExtras();       
        areaIntroId = mBundle.getInt("AreaIntroId");
        areaIntroTitle = mBundle.getString("AreaIntroTitle");
        ab.setTitle(areaIntroTitle);
        
        setViews();
        
        new DownloadArticleTask().execute();
        
        setAboutDialog();
        
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





	private void setViews() {		
		
		layoutProgress = (LinearLayout) findViewById (R.id.layout_progress);
		layoutReload = (LinearLayout) findViewById (R.id.layout_reload);
        articleWebView = (WebView) findViewById(R.id.article_webview);
         
        
	}
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

		
		menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_COLLECTION, 4, "我的收藏").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
        return true;
    }
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	        finish();
	        break;
	    case ID_SETTING: // setting
            Intent intent = new Intent(AreaIntroContentActivity.this, SettingActivity.class);
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
	    	Intent intent2 = new Intent(AreaIntroContentActivity.this, CollectionActivity.class);
	    	startActivity(intent2);
	        break;
	    }
	    return true;
	}
	
	private class DownloadArticleTask extends AsyncTask {
		
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }
		
        @Override
        protected Object doInBackground(Object... params) {        	

        	myAreaIntro = TravelAPI.getAreaIntro(areaIntroId);
        	/// pics size = 1 now
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            
            layoutProgress.setVisibility(View.GONE);
            
            if (myAreaIntro!=null){	            
            	setUIAfterLoading();           	
            }else{
            	// 重試
            	layoutReload.setVisibility(View.VISIBLE);
//            	Toast.makeText(getApplicationContext(), "無資料,請重試！", Toast.LENGTH_SHORT).show();
            }           
        }
	}
	

	private void setUIAfterLoading() {
			
		articleWebView.loadDataWithBaseURL(null, myAreaIntro.getIntro(), "text/html", "UTF-8", null);  		
		
	}
	
	
	
	@Override
    protected void onResume() {
        super.onResume();
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	 }
	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	private void setAboutDialog() {
		// TODO Auto-generated method stub
    	aboutUsDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.about_us_string))
				.setIcon(R.drawable.app_icon)
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