package com.travel.story;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.taiwan.imageload.ImageLoader;
import com.travel.story.R;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Site;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SiteActivity extends SherlockActivity {
	
	private static final int Contact_US = 0;
	private static final int ID_ABOUT_US = 1;
    private static final int ID_GRADE = 2;
    private static final int ID_OUR_APP = 3;
    private static final int ID_COLLECTION = 4;
    private static final int ID_SETTING = 6;
	
//    Integer[] pics = {
//    		R.drawable.antartica1,
//    		R.drawable.antartica2,
//    		R.drawable.antartica3,
//    		R.drawable.antartica4,
//    		R.drawable.antartica5,
//    		R.drawable.antartica6,
//    		R.drawable.antartica7,
//    		R.drawable.antartica8,
//    		R.drawable.antartica9,
//    		R.drawable.antartica10
//    };
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_site);
        layoutProgress = (LinearLayout) findViewById (R.id.layout_progress);
		layoutReload = (LinearLayout) findViewById (R.id.layout_reload);
		imageView = (ImageView) findViewById (R.id.ImageView01);
		textSiteName = (TextView) findViewById (R.id.text_site_name);
//		textSiteInfo = (TextView) findViewById (R.id.text_site_info);
//		textSiteIntroduce = (TextView) findViewById (R.id.text_site_introduce);
		webviewSiteInfo = (WebView) findViewById (R.id.webview_site_info);
		webviewSiteIntro = (WebView) findViewById (R.id.webview_site_introduce);
		myGallery = (Gallery)findViewById(R.id.Gallery01);
        imageLoader = new ImageLoader(SiteActivity.this, 70);
        
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        
        mBundle = this.getIntent().getExtras();       
        siteId = mBundle.getInt("SiteId");
        siteName = mBundle.getString("SiteName");
        ab.setTitle(siteName);
        
        new DownloadSiteTask().execute();
        
        myGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View v,
        	int position, long id) {
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
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            
            layoutProgress.setVisibility(View.GONE);
            
            if (mySite!=null){	            
            	setUIAfterLoading();           	
            }else{
            	// 重試
            	layoutReload.setVisibility(View.VISIBLE);
//            	Toast.makeText(getApplicationContext(), "無資料,請重試！", Toast.LENGTH_SHORT).show();
            }           
        }
	}
    
    private void setUIAfterLoading() {
    	myGallery.setAdapter(new ImageAdapter(this));
        int firstPosition = myPics.length/2;       
        myGallery.setSelection(firstPosition, true);
        if (myPics.length == 0) {
			imageView.setImageResource(R.drawable.app_icon);
        } else {
            imageLoader.DisplayImage(myPics[firstPosition], imageView);
        }
        
        String text = "<font color=#9E1919>"+mySite.getName()+"</font>";
        textSiteName.setText(Html.fromHtml(text));
        
//        textSiteInfo.setText(Html.fromHtml(mySite.getInfo()));
//		textSiteIntroduce.setText(Html.fromHtml(mySite.getIntroduction()));
		
		webviewSiteInfo.loadDataWithBaseURL(null,mySite.getInfo(), "text/html", "UTF-8", null);  	
		webviewSiteIntro.loadDataWithBaseURL(null,mySite.getIntroduction(), "text/html", "UTF-8", null);  	
	}
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
		
		menu.add(0, ID_SETTING, 0, "閱讀設定").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, Contact_US, 0, "聯絡我們").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_ABOUT_US, 1, "關於我們").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_GRADE, 2, "給APP評分").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_OUR_APP, 3, "我們的APP").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	menu.add(0, ID_COLLECTION, 7, "我的收藏").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
        return true;
    }
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	        finish();
	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	    case Contact_US:
	    	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	    	emailIntent.setType("plain/text");
	    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"brotherkos@gmail.com"});
	    	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "聯絡我們 from Ptt美食部落");
	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
	    	startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	        break;
	    case ID_ABOUT_US:
//	    	aboutUsDialog.show();
	        break;
	    case ID_GRADE:
	    	Intent gradeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.grade_url)));
			startActivity(gradeIntent);
	        break;
	    case ID_OUR_APP:
	    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.recommend_url)));
			startActivity(browserIntent);
	        break;
	    case ID_COLLECTION:
//	    	Intent intent = new Intent(ArticleActivity.this, FavoriteActivity.class);
//	    	startActivity(intent);
	        break;
	    case ID_SETTING:
	    	Intent intentSetting = new Intent(SiteActivity.this, SettingActivity.class);
	    	startActivity(intentSetting);
	        break;
	    }
	    return true;
	}
    
    
	public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
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
//    		iv.setImageResource(pics[position]);
    		
    		if (myPics.length == 0 ) {
    			iv.setImageResource(R.drawable.app_icon);
            } else {
                imageLoader.DisplayImage(myPics[position], iv);
            }
    		
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
    		iv.setBackgroundResource(imageBackground);
    		return iv;
    	}

    }
}
