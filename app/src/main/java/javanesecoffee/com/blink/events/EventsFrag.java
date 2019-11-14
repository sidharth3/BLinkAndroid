package javanesecoffee.com.blink.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javanesecoffee.com.blink.R;

public class EventsFrag extends Fragment {
    @Nullable
    private TabbedEvents.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//        Toolbar toolbar = (Toolbar)getView().findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        mSectionsPagerAdapter = new EventsFrag().SectionsPagerAdapter(((AppCompatActivity)getActivity()).getChildFragmentManager());
//        mViewPager = (ViewPager) getView().findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        TabLayout tabLayout = (TabLayout)getView().findViewById(R.id.tabs);
//
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        return inflater.inflate(R.layout.fragment_events, container, false);

    }
}
