package com.travel.story;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;
import com.kosbrother.fragments.MainNoteFragment;
import com.kosbrother.fragments.MainListFragment;
import com.kosbrother.fragments.MyTravelFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends SherlockFragmentActivity implements AdWhirlInterface {

    private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_SEARCH   = 5;

    private String[]            CONTENT;
    // private EditText search;
//    private MenuItem            itemSearch;
    private ViewPager           pager;
    private AlertDialog.Builder aboutUsDialog;

    private final String        adWhirlKey  = "8c0c4844165c467490f058cc4ea09118";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting.setApplicationActionBarTheme(this);
        setContentView(R.layout.simple_titles);

        Resources res = getResources();
        CONTENT = res.getStringArray(R.array.sections);

        FragmentPagerAdapter adapter = new NovelPagerAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        pager.setCurrentItem(1);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);

        menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
//        itemSearch = menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse)
//                .setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//                    private EditText     search;
//                    private LinearLayout layout;
//
//                    @Override
//                    public boolean onMenuItemActionExpand(MenuItem item) {
//                        layout = (LinearLayout) item.getActionView();
//                        search = (EditText) layout.findViewById(R.id.edittext_search);
//                        ImageView searchImage = (ImageView) layout.findViewById(R.id.image_search);
//                        // search.setInputType(InputType.TYPE_CLASS_TEXT);
//                        // search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//
//                        searchImage.setOnClickListener(new OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                // Toast.makeText(activity, "tt", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                                startActivity(intent);
//
//                            }
//                        });
//
//                        // search.requestFocus();
//                        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                            @Override
//                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                                    // Bundle bundle = new Bundle();
//                                    // bundle.putString("SearchKeyword", v.getText().toString());
//                                    Intent intent = new Intent();
//                                    intent.setClass(MainActivity.this, SearchActivity.class);
//                                    // intent.putExtras(bundle);
//                                    startActivity(intent);
//                                    itemSearch.collapseActionView();
//                                    return true;
//                                }
//                                return false;
//                            }
//                        });
//                        // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        // imm.showSoftInput(null, InputMethodManager.SHOW_IMPLICIT);
//                        return true;
//                    }
//
//                    @Override
//                    public boolean onMenuItemActionCollapse(MenuItem item) {
//                        // TODO Auto-generated method stub
//                        search.setText("");
//                        return true;
//                    }
//                }).setActionView(R.layout.collapsible_edittext);
//        itemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case ID_SETTING: // setting
             Intent intent = new Intent(MainActivity.this, SettingActivity.class);
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
        case ID_SEARCH: 
        	Intent intent2 = new Intent(MainActivity.this, TabSearchActivity.class);
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
                kk = MainListFragment.newInstance(MainActivity.this);
            } else if (position == 1) {
                kk = MyTravelFragment.newInstance();
            } else {
                kk = MainNoteFragment.newInstance(position-3);
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

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 1) {
            finish();
        } else {
            pager.setCurrentItem(1, true);
        }
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
