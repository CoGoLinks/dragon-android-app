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
//      urlTextarea.setText("https://icts-sit.suixingpay.com/wallet-h5/login?rootAccountId=f45f51495207e0b0e193e36bd7465aaf&busUserId=d41d8cd98f00b204e9800998ecf8427e&outTradeNo=d41d8cd98f00b204e9800998ecf8427e&mchId=888000000005009&merchantGrade=2&userCode=88959447c063bd64d33373f62afcbb45&merchantId=&mchName=苏州市德寿堂药房有限公司&sign=ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnZkWFJVY21Ga1pVNXZJam9pSWl3aWNtOXZkRUZqWTI5MWJuUkpaQ0k2SWxBeU5ETTBPRGswTVRjd01UQTVPVGt5T1RZaUxDSmlkWE5WYzJWeVNXUWlPaUlpTENKMWMyVnlRMjlrWlNJNklsVXlORE0xTURZeU1EYzNOelEyTURFeU1UWWlmUS5CYWZzaExrdzVPcHVqRk9uMmQ4UG05c1pZWkZFRjgtYkExNmdvWllqVGYw");
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