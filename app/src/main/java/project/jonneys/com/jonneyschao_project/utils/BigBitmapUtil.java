package project.jonneys.com.jonneyschao_project.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/12/14.
 */
public class BigBitmapUtil {

    public static Bitmap getBitmap(Resources res,int resId,int reqWidth,int reqHeight){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);
        
        options.inSampleSize = getinSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    private static int getinSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
        final int bitmapWidth = options.outWidth;
        final int bitmapHeight = options.outHeight;
        int inSampleSize = 1;
        if(bitmapHeight>reqHeight || bitmapWidth>reqWidth){
            final int halfHeight = bitmapHeight/2;
            final int halfWidht = bitmapWidth/2;
            while((halfHeight/inSampleSize)>reqHeight && (halfWidht/inSampleSize)>reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }
}
