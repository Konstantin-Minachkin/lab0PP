package com.example.lab2ppp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class Recycle extends FragmentActivity {

    ViewPager pager;
    ArrayList<Peredach> techList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        techList = getIntent().getParcelableArrayListExtra("mas1");
        pager = (ViewPager) findViewById(R.id.viewPage);
        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(getIntent().getIntExtra("pos", 50));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public RecycleFragment getItem(int position) {
            return RecycleFragment.newInstance(position, techList.get(position));
        }

        @Override
        public int getCount() {
            return techList.size();
        }

    }

}
