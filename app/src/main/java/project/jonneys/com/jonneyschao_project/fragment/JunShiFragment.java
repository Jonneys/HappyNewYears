package project.jonneys.com.jonneyschao_project.fragment;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import project.jonneys.com.jonneyschao_project.R;
import project.jonneys.com.jonneyschao_project.adapter.ListViewAdapter_KeJi;
import project.jonneys.com.jonneyschao_project.bean.Data;
import project.jonneys.com.jonneyschao_project.utils.AnalysisURLUtils;
import project.jonneys.com.jonneyschao_project.view.RefreshListView;

public class JunShiFragment extends NewFragment {
	public static final String PATH = "http://v.juhe.cn/toutiao/index?type=junshi&key=99d065aebbf5a152d7f81f509aaf3e91";
	private View view;
	private RefreshListView listView;
	private List<Data> data;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if(msg.obj!=null){
				data = (List<Data>) msg.obj;
				final ListViewAdapter_KeJi adapter =  new ListViewAdapter_KeJi(data, getActivity());
				listView.setVisibility(View.VISIBLE);
				listView.setAdapter(adapter);
				init(listView, getActivity(), data);
				listView.setRefreshListener(new RefreshListView.OnRefreshListener() {
					@Override
					public void onRefresh() {
						new Thread(){
							public void run() {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								getActivity().runOnUiThread(new Runnable() {
									@Override
									public void run() {
										adapter.notifyDataSetChanged();
										listView.onRefreshComplete();
									}
								});
							}
						}.start();
					}

					@Override
					public void onLoadMore() {
						new Thread(){
							public void run() {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								getActivity().runOnUiThread(new Runnable() {
									@Override
									public void run() {
										adapter.notifyDataSetChanged();
										listView.onRefreshComplete();
									}
								});
							}
						}.start();
					}
				});
			}else{
				if(msg.arg1==1){
					listView.setVisibility(View.GONE);
					tv.setVisibility(View.VISIBLE);
					iv.setVisibility(View.VISIBLE);
				}
			}
		}
	};
	private TextView tv;
	private ImageView iv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.junshifragment, container, false);
		listView = (RefreshListView) view.findViewById(R.id.listview_junshi);
		tv = (TextView) view.findViewById(R.id.tv_junshi);
		iv = (ImageView) view.findViewById(R.id.iv_junshi);
		initData();
		return view;
	}
	
	private void initData() {
		AnalysisURLUtils.initData(PATH, handler,getActivity());
	}
}
