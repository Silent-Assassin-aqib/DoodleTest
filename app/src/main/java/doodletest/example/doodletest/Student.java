package doodletest.example.doodletest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

class Student extends DoodleRecylerViewSetter  {
    String text;
    Student(String str){
        text = str;
    }

    @Override
    protected void setViewChild(ViewGroup v) {
        TextView txt = new TextView(v.getContext());
        txt.setText(text);
        txt.setTextSize(50);
        txt.setMinHeight(40);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
            }
        });
        v.addView(txt);
    }
}
