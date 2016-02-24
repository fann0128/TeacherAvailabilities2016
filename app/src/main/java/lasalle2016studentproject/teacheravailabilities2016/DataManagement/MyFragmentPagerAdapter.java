package lasalle2016studentproject.teacheravailabilities2016.DataManagement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listOfFragments;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> listOfFragments){
        super(fm);
        this.listOfFragments = listOfFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragments.size();
    }
}
