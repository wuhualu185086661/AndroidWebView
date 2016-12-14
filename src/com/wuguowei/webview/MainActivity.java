package com.wuguowei.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String LOGTAG = "MainActivity";
	private Button btn_up;
	private Button btn_down;
	private Button btn_left;
	private Button btn_right;

	@SuppressLint("JavascriptInterface")

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_up = (Button) findViewById(R.id.btn_up);
		btn_down = (Button) findViewById(R.id.btn_down);
		btn_left = (Button) findViewById(R.id.btn_left);
		btn_right = (Button) findViewById(R.id.btn_right);
		final WebView myWebView = (WebView) findViewById(R.id.webView);
		WebSettings settings = myWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new JsInteration(), "control");
		myWebView.setWebChromeClient(new WebChromeClient() {
		});
		myWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				// testMethod(myWebView);
			}
		});
		btn_up.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				control(myWebView, "up");

			}
		});
		btn_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				control(myWebView, "down");

			}
		});
		btn_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				control(myWebView, "left");

			}
		});
		btn_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				control(myWebView, "right");

			}
		});

		myWebView.loadUrl("http://10.0.2.2:3000");

	}

	private void control(WebView webView, String dir) {
		String call = "";
		if (dir == "up")
			call = "javascript:websocketup()";
		else if (dir == "left")
			call = "javascript:websocketleft()";
		else if (dir == "down")
			call = "javascript:websocketdown()";
		else if (dir == "right")
			call = "javascript:websocketright()";
		webView.loadUrl(call);
	}

	public class JsInteration {
		@JavascriptInterface
		public void toastMessage(String message) {
			Toast.makeText(getApplicationContext(), message, 1).show();
		}

		@JavascriptInterface
		public void onSumResult(int result) {
			Log.i(LOGTAG, "onSumResult result=" + result);
		}

	}

}
