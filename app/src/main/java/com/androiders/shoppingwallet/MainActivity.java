package com.androiders.shoppingwallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    TextView[] mDots;
    SliderAdapter sliderAdapter;
    Button SkipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(preferences.getBoolean("onboarding_complete",false)){

            Intent onboarding = new Intent(this, SecondActivity.class);
            startActivity(onboarding);

            finish();
            return;
        }

        SkipBtn = findViewById(R.id.skipBtn);
        SkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishOnboarding();
            }
        });

        mSlideViewPager = findViewById(R.id.viewpager);
        mDotLayout = findViewById(R.id.mDotLayout);


        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        AddDotsCreator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void AddDotsCreator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if(position == 2){
                finishOnboarding();
            }
        }

        @Override
        public void onPageSelected(int position) {
            AddDotsCreator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void finishOnboarding() {
        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        preferences.edit()
                .putBoolean("onboarding_complete",true).apply();

        Intent main = new Intent(this, SecondActivity.class);
        startActivity(main);

        finish();
    }

}
