package doodletest.example.doodletest;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Satyender Yadav on 06-08-2015.
 */
public class TempPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> frags;

    public TempPagerAdapter(List<Fragment> frg, FragmentManager fm){
        super(fm);
        frags = frg;
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }
}
