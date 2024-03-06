package com.suixingpay.toolsapp.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.suixingpay.toolsapp.R;

public class WebViewRootActivity extends AppCompatActivity {
   private Button clearBtn;
   private Button visiteBtn;

   private EditText urlTextarea;

   @SuppressLint("ClickableViewAccessibility")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_webview_root);
      // URL的输入框
      urlTextarea = findViewById(R.id.url_textarea);
      // urlTextarea.setText("https://icts-uat.suixingpay.com/wallet-h5/home?rootAccountId=2608e1b32d0df36b70ceb446fded418e&busUserId=06409663226af2f3114485aa4e0a23b4&outTradeNo=d41d8cd98f00b204e9800998ecf8427e&userCode=d41d8cd98f00b204e9800998ecf8427e&merchantGrade=2&action=mer_active&sign=e8y5J7z9eeX7Nc02ZeWb02i2Odi7JchbbmRyb2lkIiwidmVyc2lvbiI6IjguNC4wIiwibW9kZWwiOiJSZWRtaSBOb3RlIDggUHJvIiwidGltZSI6MTcwODkzNTU2MiwiaWQiOiIxMDMyOTIiLCJ0b2tlbiI6IjU5MzlkZjhiNjYxNjY1MTFhOGYzZjUyM2IzYjYwOTAzIn0O0O0O");
      // 清除按钮
      clearBtn = findViewById(R.id.clear_btn);
      // 访问按钮
      visiteBtn = findViewById(R.id.visite_btn);

      ActionBar actionBar = getSupportActionBar();
      if(actionBar != null){
         actionBar.setTitle("WebView");
         actionBar.setHomeButtonEnabled(true);
         actionBar.setDisplayHomeAsUpEnabled(true);
      }

      clearBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            urlTextarea.setText("");
         }
      });
      visiteBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            String url = urlTextarea.getText().toString();
            if(url == null || url.equals("")) {
               Toast.makeText(getApplicationContext(), "请输入要访问的链接",
                       Toast.LENGTH_SHORT).show();
               return;
            }
            Intent intent = new Intent(com.suixingpay.toolsapp.webview.WebViewRootActivity.this, WebViewActivity.class);
            intent.putExtra("url",url);
            startActivity(intent);
         }
      });
   }
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case android.R.id.home:
            this.finish(); // back button
            return true;
      }
      return super.onOptionsItemSelected(item);
   }
}