package org.maktab.masoudbn.dictionary;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements EditDeleteFragment.Callbacks{
    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerTabs;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayoutTabs = (TabLayout) findViewById(R.id.tab_layout_tabs);
        viewPagerTabs = (ViewPager) findViewById(R.id.view_pager_tabs);


        tabLayoutTabs.addTab(tabLayoutTabs.newTab().setText("Search"), true);
        tabLayoutTabs.addTab(tabLayoutTabs.newTab().setText("Add"));
        tabLayoutTabs.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new PagerAdapter(
                getSupportFragmentManager(), tabLayoutTabs.getTabCount());
        viewPagerTabs.setAdapter(pagerAdapter);
        viewPagerTabs.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTabs));
        tabLayoutTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerTabs));



    }

    @Override
    public void updateUI() {
        SearchFragment.newInstance().updateUI();
    }
}
