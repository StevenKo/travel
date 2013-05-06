package com.travel.story;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TabSearchActivity extends SherlockActivity implements AdWhirlInterface {
	
	private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
		
	private EditText mEditText;
	private ImageView mImageButton;
	private RadioGroup mRadioGroup;
	
	private AlertDialog.Builder aboutUsDialog;
	private ActionBar           ab;
	
	private final String        adWhirlKey  = "8c0c4844165c467490f058cc4ea09118";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_search);
        
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth(); // deprecated
        int height = display.getHeight(); // deprecated

        if (width > 480) {
        	   setContentView(R.layout.layout_search);
        } else {
        	   setContentView(R.layout.layout_search_small);
        }
        
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("旅遊大師>搜索");
        
        mEditText = (EditText) findViewById (R.id.edittext_search);
        mImageButton = (ImageView) findViewById (R.id.imageview_search);
        mRadioGroup = (RadioGroup) findViewById (R.id.radiogroup_search);
        
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
//        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            	try{
	                if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
//	                	int type = mRadioGroup.getCheckedRadioButtonId();
	                	int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
	                    View radioButton = mRadioGroup.findViewById(radioButtonID);
	                    int idx = mRadioGroup.indexOfChild(radioButton);
	                	
	                    Bundle bundle = new Bundle();
	                    bundle.putString("SearchKeyword", v.getText().toString());
	                    bundle.putInt("SearchTypeId", idx);
	                    Intent intent = new Intent();
	                    intent.setClass(TabSearchActivity.this, SearchActivity.class);
	                    intent.putExtras(bundle);
	                    startActivity(intent);
	                    return true;
	                }
            	}catch(Exception e){
            		Toast.makeText(TabSearchActivity.this, "got problem", Toast.LENGTH_SHORT).show();
            	}
                return false;
            }
        });
        
        mImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // if edittext != "", go to SearchActivity
            	// else toast enter searh key
            	if(mEditText.getText().toString().equals("") || mEditText.getText().toString().equals(0) ){
            		Toast.makeText(TabSearchActivity.this, "請輸入搜索文字", Toast.LENGTH_SHORT).show();
            	}else{
            		int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
                    View radioButton = mRadioGroup.findViewById(radioButtonID);
                    int idx = mRadioGroup.indexOfChild(radioButton);
                    
            		Bundle bundle = new Bundle();
                    bundle.putString("SearchKeyword", mEditText.getText().toString());
                    bundle.putInt("SearchTypeId", idx);
                    Intent intent = new Intent();
                    intent.setClass(TabSearchActivity.this, SearchActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
            	}
            }
        });
        
        try {        
	           if (width > 320) {
	        	   setAdAdwhirl();
	           }
           } catch (Exception e) {
          
           }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);

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
            break;
        case ID_SETTING: // setting
             Intent intent = new Intent(TabSearchActivity.this, SettingActivity.class);
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
