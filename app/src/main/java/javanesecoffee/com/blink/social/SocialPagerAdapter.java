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
                SocialSummaryFragment socialSummaryFragment = new SocialSummaryFragment();
                return socialSummaryFragment;
            case 1:
                SocialAllContactsFragment allContactsFrag = new SocialAllContactsFragment();
                return allContactsFrag;
            default:
                return new SocialSummaryFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
