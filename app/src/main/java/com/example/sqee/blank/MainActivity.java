package com.example.sqee.blank;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
/*import android.widget.Adapter;*/
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }


        TabLayout mainMenu = (TabLayout) findViewById(R.id.tabs);
        mainMenu.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new BlankFragment(), "Category 1");
        adapter.addFragment(new BlankFragment(), "Category 2");
        viewPager.setAdapter(adapter);
    }
    public void onFragmentInteraction(Uri uri) {}


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public int getCount() {
            return mFragments.size();
        }

        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

        public void addFragment(Fragment fragmentToAdd, String fragmentName) {
            mFragments.add(fragmentToAdd);
            mFragmentTitles.add(fragmentName);
        }
    }
}

