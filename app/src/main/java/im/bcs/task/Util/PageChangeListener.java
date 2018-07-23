package im.bcs.task.Util;

import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by Opriday on 7/23/2018.
 */

public class PageChangeListener implements ViewPager.OnPageChangeListener {

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.e("PageChangeListener","onPageScroll:"+position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("PageChangeListener","onPageSelectListener:"+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("PageChangeListener","onPageScrollStateChanged:"+state);
    }

}
