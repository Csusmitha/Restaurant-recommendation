package com.example.placesnearby_deploy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Introduction extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);

        //hide the action bar
        //getSupportActionBar().hide();

        //ini views
        btnNext = findViewById(R.id.nret_button);
        btnGetStarted = findViewById(R.id.lats_button);
        tabIndicator = findViewById(R.id.tab_indicator);

        //fill list screen

        List<side_layout> mList = new ArrayList<>();
        mList.add(new side_layout("Fresh Food","enjoy the taste of food with wide variety",R.drawable.dish));
        mList.add(new side_layout("Best Restaurants","enjoy the taste of food with wide variety",R.drawable.restoo));
        mList.add(new side_layout("Great Suggestions","enjoy the taste of food with wide variety",R.drawable.sugedt));

        //setupViewPager
        screenPager = findViewById(R.id.lay);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tabLayout with viewPager
        tabIndicator.setupWithViewPager(screenPager);

        //next button click listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size()-1) {//when reach to the last

                    //TODO : show the GETSTARETD button and hide the indicator and the next button

                    loadLastScreen();
                }

            }


        });

        //tablayout add change listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mList.size()-1)
                {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        btnGetStarted.bringToFront();
        tabIndicator.setVisibility(View.INVISIBLE);

    }
}
