package project.jonneys.com.jonneyschao_project.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

import project.jonneys.com.jonneyschao_project.R;

public class Fragment2 extends FragmentBase {

    private ViewPager viewPager;
    private RadioGroup group_f2;
    private RadioButton radio0_f2;
    private RadioButton radio1_f2;
    private RadioButton radio2_f2;
    private RadioButton radio3_f2;
    private RadioButton radio4_f2;
    private RadioButton radio5_f2;
    private RadioButton radio6_f2;
    private RadioButton radio7_f2;

    private HorizontalScrollView scrollView;

    private View view;
    private List<NewFragment> listViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        initView();// 初始化view
        initData();// 初始化数据
        initEvents();// 初始化事件
        return view;
    }

    private void initData() {
        listViews = new ArrayList<NewFragment>();
        TouTiaoFragment toutiao = new TouTiaoFragment();
        GuoJiFragment guoji = new GuoJiFragment();
        JunShiFragment jushi = new JunShiFragment();
        KeJiFragment keji = new KeJiFragment();
        SheHuiFragment shehui = new SheHuiFragment();
        CaiJingFragment caijing = new CaiJingFragment();
        ShiShangFragment shishang = new ShiShangFragment();
        YuLeFragment yule = new YuLeFragment();
        listViews.add(toutiao);
        listViews.add(guoji);
        listViews.add(jushi);
        listViews.add(keji);
        listViews.add(shehui);
        listViews.add(yule);
        listViews.add(caijing);
        listViews.add(shishang);

    }

    private void initEvents() {
        viewPager.setAdapter(new MyAdapter(getFragmentManager()));// 为ViewPager设置适配器

        // 为ViewPager设置监听事件
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        radio0_f2.setChecked(true);
                        scrollView.smoothScrollTo(0, 0);
                        break;
                    case 1:
                        radio1_f2.setChecked(true);
                        scrollView.smoothScrollTo(0, 0);
                        break;
                    case 2:
                        radio2_f2.setChecked(true);
                        scrollView.smoothScrollTo(0, 0);
                        break;
                    case 3:
                        radio3_f2.setChecked(true);
                        scrollView.smoothScrollTo(0, 0);
                        break;
                    case 4:
                        radio4_f2.setChecked(true);
                       scrollView.smoothScrollTo(scrollView.getMeasuredWidth(), 0);
                        break;
                    case 5:
                        radio5_f2.setChecked(true);
                        scrollView.smoothScrollTo(scrollView.getMeasuredWidth(), 0);
                        break;
                    case 6:
                        radio6_f2.setChecked(true);
                        scrollView.smoothScrollTo(scrollView.getMeasuredWidth(), 0);
                        break;
                    case 7:
                        radio7_f2.setChecked(true);
                        scrollView.smoothScrollTo(scrollView.getMeasuredWidth(), 0);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        // 给RadioGroup设置选中监听事件
        group_f2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0_f2:
                        viewPager.setCurrentItem(0, false);
                        break;

                    case R.id.radio1_f2:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.radio2_f2:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.radio3_f2:
                        viewPager.setCurrentItem(3, false);
                        break;
                    case R.id.radio4_f2:
                        viewPager.setCurrentItem(4, false);
                        break;
                    case R.id.radio5_f2:
                        viewPager.setCurrentItem(5,false);
                        break;
                    case R.id.radio6_f2:
                        viewPager.setCurrentItem(6,false);
                        break;
                    case R.id.radio7_f2:
                        viewPager.setCurrentItem(7,false);
                        break;
                }
            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_f2);
        group_f2 = (RadioGroup) view.findViewById(R.id.group_f2);
        radio0_f2 = (RadioButton) view.findViewById(R.id.radio0_f2);
        radio1_f2 = (RadioButton) view.findViewById(R.id.radio1_f2);
        radio2_f2 = (RadioButton) view.findViewById(R.id.radio2_f2);
        radio3_f2 = (RadioButton) view.findViewById(R.id.radio3_f2);
        radio4_f2 = (RadioButton) view.findViewById(R.id.radio4_f2);
        radio5_f2 = (RadioButton) view.findViewById(R.id.radio5_f2);
        radio6_f2 = (RadioButton) view.findViewById(R.id.radio6_f2);
        radio7_f2 = (RadioButton) view.findViewById(R.id.radio7_f2);
        scrollView = (HorizontalScrollView) view.findViewById(R.id.scrollView);
    }

    /**
     * ViewPager的适配器类
     *
     * @author Administrator
     */
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {

            return listViews.get(arg0);
        }

        @Override
        public int getCount() {
            return listViews.size();
        }

    }
}
