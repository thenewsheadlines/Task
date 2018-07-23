package im.bcs.task.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import im.bcs.task.Advisor;
import im.bcs.task.PubnubMessage;

/**
 * Created by Opriday on 7/23/2018.
 */

public class FragmentAdapter  extends FragmentPagerAdapter {

    int count=2;

    public FragmentAdapter(FragmentManager fm, int count) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Advisor();
        }else if (position == 1){
            return new PubnubMessage();
        }
        return  null;
    }

    @Override
    public int getCount() {
        Log.e("FragmentAdapter","getCount() called");
        return count;
    }
}
