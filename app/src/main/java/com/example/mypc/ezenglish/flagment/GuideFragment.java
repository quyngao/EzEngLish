package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.special.ResideMenu.ResideMenu;

/**
 * Created by Quylt on 9/1/2016.
 */
public class GuideFragment extends Fragment {
    private View parentView;
    private ResideMenu resideMenu;
    WebView web;
    ProgressBar progressBar;
    TextView tv1, tv2, tv3;
    String urlrule = "https://www.youtube.com/playlist?list=PLvckz0aC6Uw0E75xE2Z0gzu9tA5D9M-Ks";
    String urlguide = "http://learningeffortlessenglish.com/huong-dan-hoc/307/huong-dan-hoc-effortless-english-ban-tieng-viet-.html";
    String urltake = "https://www.youtube.com/watch?v=iK9GTDMw_7s";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_guide, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        web = (WebView) parentView.findViewById(R.id.webview01);
        progressBar = (ProgressBar) parentView.findViewById(R.id.progressBar1);

        tv1 = (TextView) parentView.findViewById(R.id.tv1);
        tv2 = (TextView) parentView.findViewById(R.id.tv2);
        tv3 = (TextView) parentView.findViewById(R.id.tv3);


        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(urlguide);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl(urlguide);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl(urlrule);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl(urltake);
            }
        });
    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
