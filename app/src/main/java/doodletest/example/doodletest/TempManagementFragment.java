package doodletest.example.doodletest;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Satyender Yadav on 07-08-2015.
 */
public class TempManagementFragment extends Fragment {
    private ManagementPage mpg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        Bundle args = getArguments();
        mpg = new ManagementPage(container.getContext(), args);
        return (mpg.getView());
    }
}
