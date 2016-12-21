package project.jonneys.com.jonneyschao_project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import project.jonneys.com.jonneyschao_project.R;
import project.jonneys.com.jonneyschao_project.bean.Data;

public class ListViewAdapter_KeJi extends BaseAdapter {

	private List<Data> mList;
	private Context context;

	public ListViewAdapter_KeJi(List<Data> mList, Context context) {
		this.mList = mList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Data data = mList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_item, null);
			holder.tv_title_keji = (TextView) convertView
					.findViewById(R.id.tv_title_keji);
			holder.tv_name_keji = (TextView) convertView
					.findViewById(R.id.tv_name_keji);
			holder.image_keji = (ImageView) convertView
					.findViewById(R.id.image_keji);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_title_keji.setText(data.getTitle());
		holder.tv_name_keji.setText(data.getAuthor_name());
		// holder.image_keji.setImageResource(R.drawable.guide_1);
		initImageBitamp(data,holder.image_keji);
		return convertView;
	}

	private void initImageBitamp(Data data,final ImageView imageview) {
		final String urlPath = data.getThumbnail_pic_s();
		try {
			URL url = new URL(urlPath);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			new Thread(){
				public void run() {
					try {
						if(conn.getResponseCode()==200){
							InputStream inputStream = conn.getInputStream();
							System.out.println("success");
							final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
							imageview.post(new Runnable() {
								
								@Override
								public void run() {
									imageview.setImageBitmap(bitmap);
								}
							});
						}else{
							System.out.println("fail");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ViewHolder {
		ImageView image_keji;
		TextView tv_title_keji;
		TextView tv_name_keji;
	}

}
