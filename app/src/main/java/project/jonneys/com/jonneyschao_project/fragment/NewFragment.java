package project.jonneys.com.jonneyschao_project.fragment;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import project.jonneys.com.jonneyschao_project.NewsContentActivity;
import project.jonneys.com.jonneyschao_project.bean.Data;

public class NewFragment extends Fragment{

	public void init(ListView listview,final Context context,final List<Data> data){
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				System.out.println(data.toString());
				Data data2 = data.get(position-1);
				Intent intent = new Intent(context,NewsContentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("data2", data2);
				intent.putExtra("bundle", bundle);
				context.startActivity(intent);
				
			}
		});
	}

}
