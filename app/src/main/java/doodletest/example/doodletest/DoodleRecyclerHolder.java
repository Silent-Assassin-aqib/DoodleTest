package doodletest.example.doodletest;


import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vostro-Daily on 7/29/2015.
 */
public class DoodleRecyclerHolder<T extends DoodleRecylerViewSetter> extends RecyclerView.ViewHolder{
    ViewGroup vg;

    public DoodleRecyclerHolder(View itemView) {
        super(itemView);
        vg = (ViewGroup)itemView;
    }

}
