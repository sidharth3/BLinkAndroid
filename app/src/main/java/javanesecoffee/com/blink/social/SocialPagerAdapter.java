package javanesecoffee.com.blink.social;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SocialPagerAdapter extends FragmentPagerAdapter {
    String[] titles;


    public SocialPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                SocialSummaryFrag socialSummaryFrag = new SocialSummaryFrag();
                return socialSummaryFrag;
            case 1:
                SocialAllContactsFrag allContactsFrag = new SocialAllContactsFrag();
                return allContactsFrag;
            default:
                return new SocialSummaryFrag();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
