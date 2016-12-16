package project.jonneys.com.jonneyschao_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.jonneys.com.jonneyschao_project.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isFirst = SharedPreferencesUtil.getBoolean(this, "isFirst", true, MODE_PRIVATE);
        if(isFirst){
            Intent intent = new Intent(MainActivity.this,GuideActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
