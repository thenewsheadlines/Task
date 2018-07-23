package im.bcs.task;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import im.bcs.task.Adapter.FragmentAdapter;
import im.bcs.task.Adapter.GridViewAdapter;
import im.bcs.task.Interface.IPresenter;
import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.Presenters.Presenter;
import im.bcs.task.Util.Constant;
import im.bcs.task.Util.PageChangeListener;
import im.bcs.task.Util.TabListener;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntializeUI();
        TabLayout.Tab home = tabLayout.newTab();
        home.setText("HOME");
        TabLayout.Tab message = tabLayout.newTab();
        message.setText("Message");
        tabLayout.addTab(home);
        tabLayout.addTab(message);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new PageChangeListener());
        tabLayout.addOnTabSelectedListener(new TabListener(viewPager));

    }

    private void IntializeUI() {

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }


}
