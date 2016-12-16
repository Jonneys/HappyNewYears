package project.jonneys.com.jonneyschao_project.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2016/12/13.
 * 动画工具类
 */
public class AnimationUtil {
    public static RotateAnimation getRotateAnimation(float fromDegress, float toDegress, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue){
        //旋转动画
        RotateAnimation rotate = new RotateAnimation(fromDegress,toDegress,pivotXType,pivotXValue,pivotYType,pivotYValue);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.RESTART);
        LinearInterpolator lin = new LinearInterpolator();//设置匀速旋转
        rotate.setInterpolator(lin);
        rotate.setDuration(6000);
        return rotate;
    }

    public static ScaleAnimation getScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue){
        //缩放动画
        ScaleAnimation scale = new ScaleAnimation(fromX,toX,fromY,toY,pivotXType,pivotXValue,pivotYType,pivotYValue);
        scale.setRepeatCount(Animation.INFINITE);
        scale.setRepeatMode(Animation.REVERSE);
        scale.setDuration(6000);

        return scale;
    }

    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha){
        //透明度渐变动画
        AlphaAnimation alpha = new AlphaAnimation(fromAlpha,toAlpha);
        alpha.setRepeatCount(Animation.INFINITE);
        alpha.setRepeatMode(Animation.REVERSE);
        alpha.setDuration(6000);
        return alpha;
    }

    public static TranslateAnimation getTranslateAnimation(float fromX,float toX, float fromY,float toY){
        //透明度渐变动画
        TranslateAnimation translateAnimation = new TranslateAnimation(fromX,toX,fromY,toY);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        return translateAnimation;
    }
}
