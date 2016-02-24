package lasalle2016studentproject.teacheravailabilities2016;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.HorizontalScrollView;

import android.widget.TabHost;


import lasalle2016studentproject.teacheravailabilities2016.DataManagement.DataManager;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.MyFragmentPagerAdapter;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment1;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment2;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment3;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    ViewPager viewPager;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        DataManager.reLoadAllData(this);
        initViewPager();
        initTabHost();
    }

    private void initTabHost(){
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        String[] tabNames = {"Tab1", "Tab2", "Tab3"};

        for(int i = 0 ; i < tabNames.length ; i++){     //Adding Tabs
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec("Tab1");
            tabSpec.setIndicator("", GetTabImage(i));       //Replace Text with Image
            tabSpec.setContent(new FakeContent(getApplicationContext()));

            tabHost.addTab(tabSpec);
        }

        tabHost.setOnTabChangedListener(this);
    }

    private Drawable GetTabImage(int i){
        Resources res = getResources();

        switch(i){
            case 0:
                return res.getDrawable(R.drawable.tab1_selector);
            case 1:
                return res.getDrawable(R.drawable.tab2_selector);
            case 2:
                return res.getDrawable(R.drawable.tab3_selector);
        }

        return null;
    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<Fragment> listOfFragments = new ArrayList<Fragment>();
        listOfFragments.add(new Fragment1());
        listOfFragments.add(new Fragment2());
        listOfFragments.add(new Fragment3());

        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listOfFragments);
        viewPager.setAdapter(myAdapter);

        viewPager.setOnPageChangeListener(this);
    }

    public class FakeContent implements TabHost.TabContentFactory{
        Context context;

        public FakeContent(Context context){
            this.context = context;
        }
        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void changeTitle(int index){
        switch(index){
            case 0:
                setTitle(R.string.tab1_title);
                break;
            case 1:
                setTitle(R.string.tab2_title);
                break;
            case 2:
                setTitle(R.string.tab3_title);
        }
    }


    @Override
    public void onTabChanged(String tabId) {
        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        changeTitle(selectedItem);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.hScrollView);

        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hScrollView.getWidth() - tabView.getWidth() / 2);

        hScrollView.smoothScrollTo(scrollPos, 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
