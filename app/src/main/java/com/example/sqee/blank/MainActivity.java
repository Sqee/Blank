package com.example.sqee.blank;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
/*import android.widget.Adapter;*/
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TableFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {setupViewPager(viewPager);}

        final FloatingActionButton addPersonFab = (FloatingActionButton) findViewById(R.id.addPersonFab);
        addPersonFab.hide();

        View.OnClickListener PersonClickListener = new ItemAddOnClickListener<Person>();
        addPersonFab.setOnClickListener(PersonClickListener);
        addOnPageChangeListener(viewPager);

        final TabLayout mainMenu = (TabLayout) findViewById(R.id.tabs);
        mainMenu.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new BlankFragment(), "Blank");
        adapter.addFragment(new TableFragment(), "People");
        viewPager.setAdapter(adapter);
    }

    public void addOnPageChangeListener(ViewPager viewPager){
        ViewPager.OnPageChangeListener OnPageChangeListener = new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected (int position) {
                final FloatingActionButton addPersonFab = (FloatingActionButton) findViewById(R.id.addPersonFab);
                switch (position) {
                    case 1:  addPersonFab.show(); break;
                    default: addPersonFab.hide(); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        viewPager.addOnPageChangeListener(OnPageChangeListener);
    }

    public void onFragmentInteraction(Uri uri) {}
    public void onFragmentInteraction(String a) {}

    class ItemAddOnClickListener<T> implements View.OnClickListener{
        public void onClick(View a) {
            DatabaseHelper mDbHelper = new DatabaseHelper(MainActivity.this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            Table persons = new Table();
            ContentValues values = new ContentValues();
            TableColumn bla = (TableColumn) Table.getTableColumns().get(1);

            values.put(bla.getName(), "bla3");
            TableColumn bla2 = (TableColumn) Table.getTableColumns().get(2);
            values.put(bla2.getName(), "bla232");
            long newRowId = db.insert(Table.getName(), null, values);
            Log.d("test", String.valueOf(newRowId));
            List<Fragment> allFragments = getSupportFragmentManager().getFragments();
            if (allFragments != null) {
                for (Fragment fragment : allFragments) {
                    if (fragment instanceof TableFragment) {
                        TableFragment tableFragment = (TableFragment) fragment;
                        tableFragment.update();
                    }
                }
            }
        }
    }

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

