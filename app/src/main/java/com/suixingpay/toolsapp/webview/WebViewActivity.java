package com.suixingpay.toolsapp.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.suixingpay.toolsapp.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView = null;
    private String url = null;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        if(webView != null && url != null) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setSupportMultipleWindows(false);// 支持打开多窗口
            // AndroidJs 挂载到window上的对面名称
            webView.addJavascriptInterface(new AndroidAndJSInterface(this), "AndroidJs");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    Log.d("webView", "onPageFinished: url:" + url);
                }
                // 设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.d("webView", "不在新窗口，直接加载: url:" + url);
                    view.loadUrl(url);
                    return true;
                }
            });
            webView.loadUrl(url);
        }
    }
    /**
     * js可以调用该类的方法
     */
    class AndroidAndJSInterface {
        Activity context;
        public AndroidAndJSInterface(Activity context) {
            Log.d("AndroidAndJSInterface", "AndroidAndJSInterface: context:" + context);
            this.context = context;
        }
        @JavascriptInterface
        public void postMessage(String message) {
            Log.d("AndroidAndJSInterface", "postMessage: message:" + message);
            if(message != null && message.equals("icts-h5-close")) {
                this.context.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }
}