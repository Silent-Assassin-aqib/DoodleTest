package doodletest.example.doodletest;

import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

/**
 * Created by Satyender Yadav on 04-08-2015.
 */
public class TempActivity extends FragmentActivity {
    TempPagerAdapter mPagerAdapter;
    ArrayList<Fragment> frags;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        this.setTheme(android.R.style.Theme_Holo);
        //****************************EXTRA STUFF***********************
//        Intent intent = new Intent(this, Attendance.class);
//        Intent intent = new Intent(this, TeacherCurriculum.class);
//        startActivity(new Intent(this, Assessment.class));
//        startActivity(new Intent(this, GoalsActivity.class));
        //**************************************************************
        this.setRequestedOrientation(1);
        if(savedInstanceState == null) {
            frags = new ArrayList<>();
            TempLoginFragment t1 = new TempLoginFragment();
            Bundle args1 = new Bundle();
            Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            args1.putInt("LoginPageWidth", size.x);
            args1.putInt("LoginPageHeight", size.y);
            t1.setArguments(args1);
            frags.add(t1);
            mPagerAdapter = new TempPagerAdapter(frags, getSupportFragmentManager());
            mViewPager = new ViewPager(this);
            mViewPager.setId(110011);
            mViewPager.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            setContentView(mViewPager);
            mViewPager.setAdapter(mPagerAdapter);
        } else {
            setContentView(mViewPager);
        }
    }

    public void addPage(String username, String title) {
//        TempFragment t = new TempFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
//        args.putString("title", title);
//        t.setArguments(args);
//        frags.add(t);

        if(title.equalsIgnoreCase("student")){
            TempStudentFragment tsf = new TempStudentFragment();
            tsf.setArguments(args);
            frags.add(tsf);
        } else if(title.equalsIgnoreCase("teacher")){
            // create teacher temp fragment
            TempTeacherFragment ttf = new TempTeacherFragment();
            ttf.setArguments(args);
            frags.add(ttf);
        } else if(title.equalsIgnoreCase(("management"))){
            TempManagementFragment tmf = new TempManagementFragment();
            tmf.setArguments(args);
            frags.add(tmf);
        }

        mPagerAdapter.notifyDataSetChanged();
    }

    public void addAndGotoPage(String s, String s1) {
        addPage(s,s1);
        mViewPager.setCurrentItem(mPagerAdapter.getCount()-1);
    }
}
