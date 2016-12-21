package project.jonneys.com.jonneyschao_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import project.jonneys.com.jonneyschao_project.fragment.Fragment1;
import project.jonneys.com.jonneyschao_project.fragment.Fragment2;
import project.jonneys.com.jonneyschao_project.fragment.Fragment3;
import project.jonneys.com.jonneyschao_project.fragment.Fragment4;
import project.jonneys.com.jonneyschao_project.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {


    private FrameLayout fl;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioGroup group;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        boolean isFirst = SharedPreferencesUtil.getBoolean(this, "isFirst", true, MODE_PRIVATE);
        if (isFirst) {
            Intent intent = new Intent(MainActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        }
        initView();// 初始化view
        initTab();// 初始化radio1对应的fragment
        initChecked();// 初始化点击事�?

    }

    private void initChecked() {

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        clickTab1Layout();
                        break;
                    case R.id.radio2:
                        clickTab2Layout();
                        break;
                    case R.id.radio3:
                        clickTab3Layout();
                        break;
                    case R.id.radio4:
                        clickTab4Layout();
                        break;
                }
            }
        });

    }

    private void initTab() {
        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }
        // 如果fragment1没被add过，将fragment1添加到fl布局中，这里即初始化进入MainActivity中的第一个显示页面，fragment1
        if (!fragment1.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fl, fragment1).commit();
            currentFragment = fragment1;
        }
    }

    /**
     * 点击第一个radiobutton
     */
    private void clickTab1Layout() {
        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                fragment1);

    }

    /**
     * 点击第二个radiobutton
     */
    private void clickTab2Layout() {
        if (fragment2 == null) {
            fragment2 = new Fragment2();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                fragment2);

    }

    /**
     * 点击第三个radiobutton
     */
    private void clickTab3Layout() {
        if (fragment3 == null) {
            fragment3 = new Fragment3();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                fragment3);
    }

    /**
     * 点击第三个radiobutton
     */
    private void clickTab4Layout() {
        if (fragment4 == null) {
            fragment4 = new Fragment4();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                fragment4);
    }

    /**
     * 添加或�?�显示碎�?
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.main_fl, fragment)
                    .commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.main_fl);
        group = (RadioGroup) findViewById(R.id.radioGroup1);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);
    }
}
