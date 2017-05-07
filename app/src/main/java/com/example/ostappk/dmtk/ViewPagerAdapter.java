package com.example.ostappk.dmtk; /**
 * Created by OstapPK on 26.09.2015.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ostappk.dmtk.monday;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence[] Titles;
    int NumbofTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbofTabs = mNumbOfTabsumb;
    }

    @Override
    public  Fragment getItem(int position) {
        if (position == 0) {
            monday tab1 = new monday();
           return tab1;
        }
        if (position == 1) {
            Tuesday tab2 = new Tuesday();
            return tab2;
        }
        if (position == 2) {
            wednesday tab3 = new wednesday();
            return tab3;
        }
        if (position == 3) {
            Thursday tab4=new Thursday();
            return tab4;
        }
        else {
            Friday friday=new Friday();
            return friday;
        }

    }

public CharSequence getPageTitle(int position){
    return Titles[position];
}
    @Override
    public int getCount() {
        return NumbofTabs;
    }
}
