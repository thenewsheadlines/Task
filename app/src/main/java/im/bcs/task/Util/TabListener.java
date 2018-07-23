package im.bcs.task.Util;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by Opriday on 7/23/2018.
 */

public class TabListener implements TabLayout.OnTabSelectedListener {

    ViewPager viewPager;

    public TabListener(ViewPager viewPager){
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e("TAB","onTabSelect:"+tab.getPosition());
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.e("TAB","onTabUnselected:"+tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.e("TAB","onTabReselected:"+tab.getPosition());
    }
}
