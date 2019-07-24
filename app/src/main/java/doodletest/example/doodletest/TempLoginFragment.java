package doodletest.example.doodletest;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Vostro-Daily on 8/6/2015.
 */
public class TempLoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return (new LoginPage(container.getContext(),getArguments().getInt("LoginPageWidth"),getArguments().getInt("LoginPageHeight"))).getView();
    }
}
