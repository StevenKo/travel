package com.travel.story;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.kosbrother.tool.DetectScrollView;
import com.kosbrother.tool.DetectScrollView.DetectScrollViewListener;
import com.taiwan.imageload.ImageLoader;
import com.travel.story.api.TravelAPI;
import com.travel.story.entity.Note;


public class ArticleActivity extends SherlockActivity implements DetectScrollViewListener{
	
    
    private static final int Contact_US = 0;
	private static final int ID_ABOUT_US = 1;
    private static final int ID_GRADE = 2;
    private static final int ID_OUR_APP = 3;
    private static final int ID_COLLECTION = 4;
    private static final int ID_WATCHPICS = 5;
    private static final int ID_SETTING = 6;
    
//	private TextView articleTextView;
	private TextView articleTextTitle;
	private TextView articleTextDate;
	private TextView articlePercent;
	private CheckBox checkboxFavorite;
	private DetectScrollView articleScrollView;
	private Button buttonReload;
	private WebView articleWebView;
	
	private Note myNote; // uset to get article text
	
	private ArrayList<Note> favoriteNotes;
	private Bundle mBundle;
	
	private ActionBar ab;
	private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
	
	private int noteId;
	private String noteTitle;
	
	private int textTitleSize;
	private int textContentSize;
	private AlertDialog.Builder aboutUsDialog;
	
//	private ImageView mImageView;
//	private Gallery   mGallery;
	private String[]  pics;
	public ImageLoader    imageLoader;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_article);
        imageLoader = new ImageLoader(ArticleActivity.this, 70);

        restorePreValues();
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mBundle = this.getIntent().getExtras();
        noteId = mBundle.getInt("NoteId");
        noteTitle = mBundle.getString("NoteTitle");
        ab.setTitle(noteTitle);

        setViews();

        new DownloadArticleTask().execute();

        setAboutDialog();

        // try {
        // Display display = getWindowManager().getDefaultDisplay();
        // int width = display.getWidth(); // deprecated
        // int height = display.getHeight(); // deprecated
        //
        // if (width > 320) {
        // setAdAdwhirl();
        // }
        // } catch (Exception e) {
        //
        // }

    }

    private void restorePreValues() {
        textTitleSize = Setting.getSetting(Setting.keyTextTitleSize, ArticleActivity.this);
        textContentSize = Setting.getSetting(Setting.keyTextContentSize, ArticleActivity.this);

    }

    private void setViews() {

        layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        layoutReload = (LinearLayout) findViewById(R.id.layout_reload);
        // articleTextView = (TextView) findViewById (R.id.article_text);
        articleTextTitle = (TextView) findViewById(R.id.text_article_title);
        articleTextDate = (TextView) findViewById(R.id.text_article_date);
        checkboxFavorite = (CheckBox) findViewById(R.id.checkbox_article);
        articleScrollView = (DetectScrollView) findViewById(R.id.article_scrollview);
        buttonReload = (Button) findViewById(R.id.button_reload);
        articlePercent = (TextView) findViewById(R.id.article_percent);
        // mImageView = (ImageView) findViewById (R.id.ImageView01) ;
        // mGallery = (Gallery) findViewById (R.id.Gallery01);
        articleWebView = (WebView) findViewById(R.id.article_webview);

        articleTextTitle.setTextSize(textTitleSize);
        articleTextDate.setTextSize(textTitleSize - 3);
        // articleTextView.setTextSize(textContentSize);

        articleScrollView.setScrollViewListener(ArticleActivity.this);

        // mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //
        // @Override
        // public void onItemSelected(AdapterView<?> parent, View v,
        // int position, long id) {
        // imageLoader.DisplayImage(pics[position], mImageView);
        // }
        //
        //
        // @Override
        // public void onNothingSelected(AdapterView<?> arg0) {
        //
        // }
        // }
        // );

        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                layoutProgress.setVisibility(View.VISIBLE);
                layoutReload.setVisibility(View.GONE);
                new DownloadArticleTask().execute();
            }
        });

        checkboxFavorite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // if(checkboxFavorite.isChecked()){
                // DBAPI.insertArticle(myAricle, ArticleActivity.this);
                // Toast.makeText(ArticleActivity.this, "加入我的最愛", Toast.LENGTH_SHORT).show();
                // }else{
                // DBAPI.deleteArticle(myAricle, ArticleActivity.this);
                // Toast.makeText(ArticleActivity.this, "從我的最愛移除", Toast.LENGTH_SHORT).show();
                // }
            }
        });

    }

    @Override
    public void onScrollChanged(DetectScrollView scrollView, int x, int y, int oldx, int oldy) {
        // int kk = articleScrollView.getHeight();
        // int tt = articleTextView.getHeight();
        // int xx = (int)(((double)(y+kk)/(double)(tt))*100);
        // String yPositon = Integer.toString(xx);
        // articlePercent.setText(yPositon+"%");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);

        menu.add(0, ID_SETTING, 0, "閱讀設定").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, Contact_US, 0, "聯絡我們").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 1, "關於我們").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 2, "給APP評分").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_OUR_APP, 3, "我們的APP").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_COLLECTION, 4, "我的收藏").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        // menu.add(0, ID_WATCHPICS, 5, "看照片").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

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
        case Contact_US:
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "brotherkos@gmail.com" });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "聯絡我們 from Ptt美食部落");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            break;
        case ID_ABOUT_US:
            aboutUsDialog.show();
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
            // Intent intent = new Intent(ArticleActivity.this, FavoriteActivity.class);
            // startActivity(intent);
            break;
        case ID_SETTING:
            Intent intentSetting = new Intent(ArticleActivity.this, SettingActivity.class);
            startActivity(intentSetting);
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

            myNote = TravelAPI.getNote(noteId);
            // / pics size = 1 now
            pics = new String[1];
            pics[0] = myNote.getPic();
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            layoutProgress.setVisibility(View.GONE);

            if (myNote != null) {
                setUIAfterLoading();
            } else {
                // 重試
                layoutReload.setVisibility(View.VISIBLE);
                // Toast.makeText(getApplicationContext(), "無資料,請重試！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUIAfterLoading() {

        // articleWebView.loadData(myNote.getContent(), "text/html", "UTF-8");
        articleWebView.loadDataWithBaseURL(null, myNote.getContent(), "text/html", "UTF-8", null);

        // articleTextView.setText(Html.fromHtml(myNote.getContent()));
        // articleTextView.setMovementMethod(LinkMovementMethod.getInstance());

        String text = "<font color=#404040>" + myNote.getTitle() + "</font>" + "<font color=#858585>" + " by " + myNote.getAuthor() + "</font>";
        articleTextTitle.setText(Html.fromHtml(text));

        articleTextDate.setText(myNote.getDate());

        // mGallery.setAdapter(new ImageAdapter(this));
        // int firstPosition = pics.length/2;
        // mGallery.setSelection(firstPosition, true);
        // imageLoader.DisplayImage(pics[firstPosition], mImageView);

        // set checkbox
        // for(int i =0; i<favoriteArticles.size();i++){
        // if(favoriteArticles.get(i).getId() == myAricle.getId()){
        // checkboxFavorite.setChecked(true);
        // break;
        // }else{
        // checkboxFavorite.setChecked(false);
        // }
        // }
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

            return pics.length;
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

            if (pics[position] == null || pics[position].equals("")) {
                iv.setImageResource(R.drawable.app_icon);
            } else {
                imageLoader.DisplayImage(pics[position], iv);
            }

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
            iv.setBackgroundResource(imageBackground);
            return iv;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        restorePreValues();
        articleTextTitle.setTextSize(textTitleSize);
        articleTextDate.setTextSize(textTitleSize - 3);
        WebSettings setting = articleWebView.getSettings();
        setting.setDefaultFontSize(textContentSize);

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
        aboutUsDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.about_us_string)).setIcon(R.drawable.app_icon)
                .setMessage(getResources().getString(R.string.about_us))
                .setPositiveButton(getResources().getString(R.string.yes_string), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }

}
