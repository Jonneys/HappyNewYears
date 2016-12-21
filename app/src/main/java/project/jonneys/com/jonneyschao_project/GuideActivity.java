package project.jonneys.com.jonneyschao_project;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.jonneys.com.jonneyschao_project.utils.AnimationUtil;
import project.jonneys.com.jonneyschao_project.utils.BigBitmapUtil;
import project.jonneys.com.jonneyschao_project.utils.ImmersedStatusbarUtils;
import project.jonneys.com.jonneyschao_project.utils.ScreenInfoUtil;
import project.jonneys.com.jonneyschao_project.utils.SharedPreferencesUtil;
import project.jonneys.com.jonneyschao_project.view.MyGuideViewGroup;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private MyGuideViewGroup mViewPager;//自定义垂直方向ViewPager
    private Button mBtn_music;//音乐开关按钮
    private MediaPlayer mp;//音乐播放控件
    private boolean flag_music = false;//用来控制音乐的播放与静音


    //#######################下面图片最好进行大图片OOM异常处理,该数组存放的是可以滑动的四张背景图片#############
    private final int[] imgSource = {R.mipmap.spr,R.mipmap.sum,R.mipmap.aut,R.mipmap.win};
    //背景图片上面的引导图标
    private final int[] imgButtonResource = {R.mipmap.up_guide, R.mipmap.up_guide2, R.mipmap.up_guide3, R.mipmap.up_guide4};
    //存放滑动View的集合
    private List<View> viewList;
    //滑动View
    private View view;
    //进入MainActivity界面按钮
    private ImageButton imgbutton;
    //引导页图标动画集合
    private AnimationSet imgButtonSet;
    private boolean isFrist = false;
    private TextView tv_guide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        initView();//初始化View
        initData();//初始化数据
        initListener();//实现监听事件
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMusicAnimation();//初始化音乐动画
        initMusic();//初始化音乐并播放


    }

    private void initMusicAnimation() {
        RotateAnimation rotate = AnimationUtil.getRotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ScaleAnimation scale = AnimationUtil.getScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alpha = AnimationUtil.getAlphaAnimation(1.0f, 0.5f);
        //动画集合，将上面动画加入到此集合中
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        set.setDuration(6000);
        set.start();
        //音乐按钮执行动画
        mBtn_music.startAnimation(set);


    }

    private void initData() {
        //初始化引导按钮动画
        initUpImageButtonAnimation();
        int screenWidht = ScreenInfoUtil.getScreenWidht(GuideActivity.this);
        int screenHeight = ScreenInfoUtil.getScreenHeight(GuideActivity.this);
        //通过for循环将要滑动的view初始化并添加到List中
        for (int i = 0; i < imgSource.length; i++) {
            view = getLayoutInflater().inflate(R.layout.layout_viewpager, null);
            ImageView img = (ImageView) view.findViewById(R.id.pager);
            Bitmap bitmap = BigBitmapUtil.getBitmap(getResources(), imgSource[i], screenWidht, screenHeight);
            img.setImageBitmap(bitmap);
            viewList.add(view);
        }
        //将view添加到自定义的ViewPager中
        mViewPager.setViewList(viewList);
        imgbutton.startAnimation(imgButtonSet);

    }

    private void initUpImageButtonAnimation() {
        ScaleAnimation imgButtonscale = AnimationUtil.getScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        imgButtonscale.setDuration(2000);
        TranslateAnimation imgButtontranslate = AnimationUtil.getTranslateAnimation(0, 0, 0, -20.0f);
        imgButtonSet = new AnimationSet(true);
        imgButtonSet.addAnimation(imgButtonscale);
        imgButtonSet.addAnimation(imgButtontranslate);
        imgButtonSet.setDuration(2000);
        imgButtonSet.start();

    }

    private void initMusic() {
        //播放音乐
        mp = new MediaPlayer();
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.music);
        try {
            mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            mp.prepare();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(0.5f, 0.5f);
        mp.setLooping(true);
        mp.start();
    }

    private void initListener() {
        mBtn_music.setOnClickListener(this);
        imgbutton.setOnClickListener(this);
        //如果当前引导页显示的是最后一个页面，改变该引导页面的引导按钮图片
        mViewPager.setOnVerticalPageChangeListener(new MyGuideViewGroup.OnVerticalPageChangeListener() {

            @Override
            public void onVerticalPageSelected(int position) {
                switch (position){
                    case 0:
                        imgbutton.setImageResource(imgButtonResource[0]);
                        break;
                    case 1:
                        imgbutton.setImageResource(imgButtonResource[1]);
                        break;
                    case 2:
                        imgbutton.setImageResource(imgButtonResource[2]);
                        break;
                    case 3:
                        imgbutton.setImageResource(R.mipmap.enter_guide);
                        break;
                }
            }
        });
    }

    private void initView() {
        mViewPager = (MyGuideViewGroup) findViewById(R.id.viewpager_guide);
        mBtn_music = (Button) findViewById(R.id.btn_music_guide);
        viewList = new ArrayList<View>();
        imgbutton = (ImageButton) findViewById(R.id.imagebtn_guide);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_music_guide:
                //根据flag_music来设置音乐的播放状态为正常或静音
                if (flag_music) {
                    mp.setVolume(1, 1);
                    mBtn_music.setBackgroundResource(R.mipmap.music_on);
                    flag_music = false;
                } else {
                    mp.setVolume(0, 0);
                    mBtn_music.setBackgroundResource(R.mipmap.music_off);
                    flag_music = true;
                }
                break;
            case R.id.imagebtn_guide:
                View currentView = mViewPager.getCurrentView();
//                for (int i=0;i<viewList.size()-1;i++){
//                    if(currentView==viewList.get(i)){
//                        mViewPager.setToScreen(i+1);
//                    }
//                }
                if(currentView==viewList.get(0)){
                    mViewPager.setToScreen(1);
                    imgbutton.setImageResource(imgButtonResource[1]);
                }
                if(currentView==viewList.get(1)){
                    mViewPager.setToScreen(2);
                    imgbutton.setImageResource(imgButtonResource[2]);
                }
                if(currentView==viewList.get(2)){
                    imgbutton.setImageResource(R.mipmap.enter_guide);
                    mViewPager.setToScreen(3);
                }
                if(currentView==viewList.get(viewList.size()-1)){
                    SharedPreferencesUtil.setBoolean(this,"isFirst",false,MODE_PRIVATE);
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(mBtn_music.getAnimation().hasStarted()){
            mBtn_music.clearAnimation();
        }
        if(imgbutton.getAnimation().hasStarted()){
            imgbutton.clearAnimation();
        }
        if(mp.isPlaying()){
            mp.release();
        }
        super.onDestroy();
    }
}
