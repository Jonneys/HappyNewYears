package project.jonneys.com.jonneyschao_project.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ScreenInfoUtil {
    public static int getScreenWidht(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        return screenWidth;
    }
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenHeight = wm.getDefaultDisplay().getHeight();
        return screenHeight;
    }
}
