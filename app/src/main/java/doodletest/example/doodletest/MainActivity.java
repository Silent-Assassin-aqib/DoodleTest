package doodletest.example.doodletest;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout rl = new RelativeLayout(this);
        RecyclerView rv = new RecyclerView(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ArrayList<Person> lsp = new ArrayList<Person>();
        String[] names = {"Ashu","Chamoli","Sattu","Satyender","Idiots","Monkeys","Pulkit","Amit","Khandooja","Rohit"};
        for (String name:names){
            Person p = new Person(name);
            lsp.add(p);
        }
        DoodleRecyclerViewAdapter<Person> ra = new DoodleRecyclerViewAdapter<Person>(lsp);
        rv.setAdapter(ra);
        rv.setVerticalScrollBarEnabled(true);
        //rv.setScrollbarFadingEnabled(false);
        rl.addView(rv);
        setContentView(rl);
        //setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
