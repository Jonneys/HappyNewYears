package project.jonneys.com.jonneyschao_project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import project.jonneys.com.jonneyschao_project.R;

public class Fragment1 extends FragmentBase {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment1, container, false);
//		WebView webview = (WebView) view.findViewById(R.id.f1_webview);
//		webview.loadUrl("http://www.baidu.com");
		return view;
	}
}
