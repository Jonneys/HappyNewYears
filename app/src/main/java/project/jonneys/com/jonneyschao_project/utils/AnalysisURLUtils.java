package project.jonneys.com.jonneyschao_project.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import project.jonneys.com.jonneyschao_project.bean.Data;
import project.jonneys.com.jonneyschao_project.bean.NewsStates;
import project.jonneys.com.jonneyschao_project.bean.Result;

public class AnalysisURLUtils {

	private static List<Data> data;
	private static HttpURLConnection conn = null;
	private static InputStream inputStream = null;
	public static final void initData(String path, final Handler handler,
			final Context context) {
		URL url = null;
		try {
			url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			new Thread() {

				private BufferedReader reader;

				public void run() {

					StringBuilder sb = null;
					try {
						inputStream = conn.getInputStream();
						reader = new BufferedReader(new InputStreamReader(
								inputStream));
						sb = new StringBuilder();

						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line);
						}
						System.out.println("hhhhhhhhhh");
						Gson gson = new Gson();
						NewsStates news = gson.fromJson(sb.toString(),
								NewsStates.class);
						String reason = news.getReason();
						String error_code = news.getError_code();
						Result result = news.getResult();
						String state = result.getstat();
						data = result.getData();
						Message msg = Message.obtain();
						msg.obj = data;
						handler.sendMessage(msg);
					} catch (IOException e) {
//						Looper.prepare();
						Message msg = Message.obtain();
						msg.arg1 = 1;
						handler.sendMessage(msg);
//						System.out.println("ccccccccccccc");
//						Looper.loop();
						return;
					}
					
				}
			}.start();
		} catch (Exception e) {
			System.out.println("����������Ϣ");
			Toast.makeText(context, "fail", 1).show();
		}

	}
}
