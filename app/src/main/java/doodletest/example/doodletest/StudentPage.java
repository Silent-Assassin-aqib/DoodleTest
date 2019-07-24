package doodletest.example.doodletest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

/**
 * Created by Vostro-Daily on 8/6/2015.
 */
public class StudentPage {
    View currView;
    Person13[] studentListItems = {new Person13("Goals"),new Person13("Curriculum"),new Person13("Assessment"),new Person13("Location"),new Person13("Messaging")};
    //DynamicListAdapter dynamicListAdapter;
    DoodleRecyclerViewAdapter<Person13> mAdapter;

    StudentPage(Context ctx, Bundle args){
        // read arguments
        String username = args.getString("username");

        RelativeLayout rl = new RelativeLayout(ctx);
        //ll.setLayoutParams(new FragmentLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.MATCH_PARENT));
        //ListView listView = new ListView(ctx);
        //ArrayList<View> views = new ArrayList<>();

        //SudentListItems = new ArrayList<>(Arrays.asList(ctx.getResources().getStringArray(R.array.stud_list)));
        RecyclerView rv = new RecyclerView(ctx);
        LinearLayoutManager llm = new LinearLayoutManager(ctx);
        rv.setLayoutManager(llm);
        mAdapter = new DoodleRecyclerViewAdapter<>(Arrays.asList(studentListItems));
        rv.setAdapter(mAdapter);
//        for(String item : studentListItems){
//            TextView textView = new TextView(ctx);
//            textView.setText(item);
//            views.add(textView);
//        }
//
//        dynamicListAdapter = new DynamicListAdapter(views , ctx);
//        listView.setAdapter(dynamicListAdapter);

        TextView tv = new TextView(ctx);
        tv.setText(username);
        tv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 60));
        rl.addView(tv);
        rl.addView(rv);

        currView = rl;
    }

    public View getView(){
        return currView;
    }

    public class Person13 extends DoodleRecylerViewSetter {
        String text;

        Person13(String str) {
            text = str;
        }

        @Override
        protected void setViewChild(ViewGroup v) {
            TextView textView = new TextView(v.getContext());
            textView.setText(text);
            textView.setTextSize(50);
            textView.setMinHeight(40);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
                    if (text.equalsIgnoreCase("goals")) {
                        Intent intent = new Intent(view.getContext(), GoalsActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (text.equalsIgnoreCase("curriculum")) {
                        Intent intent = new Intent(view.getContext(), TeacherCurriculum.class);
                        view.getContext().startActivity(intent);
                    } else if (text.equalsIgnoreCase("assessment")) {
                        Intent intent = new Intent(view.getContext(), Assessment.class);
                        view.getContext().startActivity(intent);
                    } else if (text.equalsIgnoreCase("attendance")) {
                        Intent intent = new Intent(view.getContext(), Attendance.class);
                        view.getContext().startActivity(intent);
                    } else if (text.equalsIgnoreCase("location")) {
                        Intent intent = new Intent(view.getContext(), LocationActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (text.equalsIgnoreCase("messaging")) {
                        Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            v.addView(textView);
        }
    }
}
