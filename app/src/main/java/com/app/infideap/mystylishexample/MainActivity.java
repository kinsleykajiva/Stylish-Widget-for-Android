package com.app.infideap.mystylishexample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.app.infideap.stylishwidget.view.Stylish;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initial this before setContentView or declare in onCreate() of Custom Application
        String fontFolder = "rajdhani/";
        Stylish.getInstance().set(
                fontFolder.concat("Rajdhani-Regular.ttf"),
                fontFolder.concat("Rajdhani-Bold.ttf"),
                fontFolder.concat("Rajdhani-Light.ttf"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabLayout();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return WidgetFragment.newInstance();
                    case 1:
                        return ButtonPlainFragment.newInstance();
                    case 2:
                        return ButtonOutlineFragment.newInstance();
                    case 3:
                        return MessageBoxFragment.newInstance();
                    case 4:
                        return ProgressBarFragment.newInstance();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        assert tabLayout != null;

        TabLayout.Tab[] tabs = {
                tabLayout.newTab().setText(R.string.widget),
                tabLayout.newTab().setText(R.string.plainbutton),
                tabLayout.newTab().setText(R.string.outlinebutton),
                tabLayout.newTab().setText(R.string.messagebox),
                tabLayout.newTab().setText(R.string.progressbar),
        };

        for (TabLayout.Tab tab : tabs)
            tabLayout.addTab(tab);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
