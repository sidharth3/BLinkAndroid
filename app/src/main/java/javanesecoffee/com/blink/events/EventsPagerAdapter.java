package javanesecoffee.com.blink.events;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EventsPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    public EventsPagerAdapter(FragmentManager fm, String[] titles ) {
        super(fm);
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                EventListFragment exploreEventList = new EventListFragment();
                exploreEventList.setType(EventListTypes.EXPLORE);
                return exploreEventList;
            case 1:
                EventListFragment upcomingEventList = new EventListFragment();
                upcomingEventList.setType(EventListTypes.UPCOMING);
                return upcomingEventList;
            case 2:
                EventListFragment pastEventList = new EventListFragment();
                pastEventList.setType(EventListTypes.PAST_EVENTS);
                return pastEventList ;
            default:
                return new EventListFragment();
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
