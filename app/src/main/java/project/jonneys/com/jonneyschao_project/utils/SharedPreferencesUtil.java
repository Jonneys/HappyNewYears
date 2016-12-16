package project.jonneys.com.jonneyschao_project.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/14.
 */
public class SharedPreferencesUtil {
    public static final boolean getBoolean(Context context, String key, boolean value, int mode){

        SharedPreferences sharedPreferences = context.getSharedPreferences(key, mode);
        boolean isFirst = sharedPreferences.getBoolean(key, value);

        return isFirst;
    }

    public static final void setBoolean(Context context,String key,boolean value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, mode);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
}
