package doodletest.example.doodletest;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Satyender Yadav on 04-08-2015.
 */
public class TempFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        LinearLayout rl = new LinearLayout(container.getContext());
        TextView txt = new TextView(container.getContext());
        Bundle arg = getArguments();
        txt.setText(arg.getString("username")+" "+arg.getString("title"));
        //EditText et = new EditText(container.getContext());
        //et.setHint("Hey");
//        Button bt = new Button(container.getContext());
//        bt.setText("Add Page");
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TempActivity)v.getContext()).addPage();
//            }
//        });
        rl.addView(txt);
//        rl.addView(bt);
        return rl;
    }

}
