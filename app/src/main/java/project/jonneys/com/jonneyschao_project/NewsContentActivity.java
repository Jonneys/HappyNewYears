package project.jonneys.com.jonneyschao_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import project.jonneys.com.jonneyschao_project.bean.Data;

public class NewsContentActivity extends Activity {
	
	private Data data;
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_content);
		webview = (WebView) findViewById(R.id.webview);
		initData();
		initNetWork();
	}

	private void initNetWork() {
		analysisURL();
	}



	private void analysisURL() {
		String path = data.getUrl();
		webview.loadUrl(path);
		//����ҳʱ������ϵͳ������� �����ڱ�WebView����ʾ
		webview.setWebViewClient(new WebViewClient(){
		      @Override
		      public boolean shouldOverrideUrlLoading(WebView view, String url) {
		          view.loadUrl(url);
		          return true;
		      }
		  });
		
		//ͨ��java�������javascript
		WebSettings webSettings =   webview .getSettings(); 
		webSettings.setJavaScriptEnabled(true);
		
		
	}
	
	//�����ؼ�ʱ�� ���˳�������Ƿ�����һ���ҳ��
	public boolean onKeyDown(int keyCode, KeyEvent event) {       
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {       
        	webview.goBack();       
            return true;       
        }       
        return super.onKeyDown(keyCode, event);       
    }

	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		data = (Data) bundle.getSerializable("data2");
	}
}
