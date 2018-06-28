package com.developer.yoshi1125hisa.googlealert;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebActivity extends AppCompatActivity {


    String getUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);




        Intent intent = this.getIntent();
        getUrl = intent.getStringExtra("URL");
        //レイアウトで指定したWebViewのIDを指定する。
        WebView  webView = (WebView)findViewById(R.id.webView);

        //リンクをタップしたときに標準ブラウザを起動させない
        webView.setWebViewClient(new WebViewClient());


        //jacascriptを許可する
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setInitialScale(1);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //最初にgoogleのページを表示する。
        webView.loadUrl(getUrl);


    }

    public void clip(View v){

        //クリップボードに格納するItemを作成
        ClipData.Item item = new ClipData.Item(getUrl);

//MIMETYPEの作成
        String[] mimeType = new String[1];
        mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST;

//クリップボードに格納するClipDataオブジェクトの作成
        ClipData cd = new ClipData(new ClipDescription("text_data", mimeType), item);

//クリップボードにデータを格納
        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        cm.setPrimaryClip(cd);


        Toast.makeText(this, "クリップボードにコピーしました。", Toast.LENGTH_SHORT).show();



    }



}