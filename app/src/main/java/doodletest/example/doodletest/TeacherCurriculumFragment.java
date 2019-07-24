package doodletest.example.doodletest;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCurriculumFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecycleView;         // listView defined in the xml
    private ArrayList<String> mList;    // current list of items
//    private ArrayList<String> classes, subjects, units, sections, subSections, topics, topic1, topic2;
//    private ArrayAdapter mAdapter;
    private DoodleRecyclerViewAdapter<Person11> mAdapter;
    private String json;                // json string fetched from file
    public String uri;                  // the uri for the current view item list in the database or in the json tree
    public Stack<String> stack;         // elements or uri parameters are pushed on to the stack when next view is reqd.
                                                // and popped off when previous view is being fetched
    private DataBaseHelper dbHelper;    // reads json string from the database, and helps to parse it
//    public ArrayList<View> views;
    ArrayList<Person11> curriculumItemList;

    public TeacherCurriculumFragment() {
        // Required empty public constructor
    }

    public boolean onBackPressed(){
        // pop an item from the uri, get the list on the basis of this uri, add that list on to the array adapter
        // Toast.makeText(getActivity(), stack.get(1), Toast.LENGTH_SHORT).show();
        int size = dbHelper.getSize(uri);
        if(size == 0)
            return false;

        removeItemFromUri(); // remove the last item from the uri
        stack.pop();

        mList = dbHelper.fetchList(uri);

//        mAdapter.clear();
//        mAdapter.addAll(mList);
//        views.clear();  // clear views list
        // create a new views list
        curriculumItemList.clear();
        for(String item : mList) {
//            views.add(getTextView(item));
            Person11 p = new Person11(item);
            curriculumItemList.add(p);
        }
        // pass the new views list to dynamicListAdapter
//        mAdapter = new DynamicListAdapter(views, mContext);
        mAdapter = new DoodleRecyclerViewAdapter<>(curriculumItemList);
        mRecycleView.setAdapter(mAdapter);

        return true;
    }

    // removes the last item from the uri
    private void removeItemFromUri() {
        int size = dbHelper.getSize(uri);
        String newUri = "/";
        // if size = 1 newUri remains "/"

        for(int i=0; i < size -1; i++) { // find each item and add to new uri
            String str1 = uri.substring(uri.indexOf("/") + 1);
            String ele = str1.substring(0, str1.indexOf("/"));
            uri = str1.substring(str1.indexOf("/"));
            newUri = newUri + ele + "/";
        }
        uri = newUri;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            ArrayList<String> list = savedInstanceState.getStringArrayList("list");
//            mAdapter.clear();
//            views.clear();
            curriculumItemList.clear();
            for(String item : list) {
//                views.add(getTextView(item));
                Person11 p = new Person11(item);
                curriculumItemList.add(p);
            }
//            mAdapter = new DynamicListAdapter(views, mContext);
            mAdapter = new DoodleRecyclerViewAdapter<>(curriculumItemList);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", mList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_curriculum, container, false);

        // initialization stuff here
        mContext = getActivity();
        stack = new Stack<>();      // creating a new stack
//        views = new ArrayList<>();
        curriculumItemList = new ArrayList<>();
        // read json file from the assets directory
        json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("curriculum");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        uri = "/";  // start of uri .... represents the list of classes
        mList = new ArrayList<>();
        dbHelper = new DataBaseHelper();
        mList = dbHelper.fetchList(uri); // fetches the list of classes
        if(savedInstanceState != null) {
            mList = savedInstanceState.getStringArrayList("list");
            //mArrayAdapter.clear();
            //mArrayAdapter.addAll(mList);
        }
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//            JSONObject list = jsonObject.getJSONObject("list");
//            JSONArray jsonArray = list.getJSONArray("items");

//            classes = new ArrayList<String>();
//            classes.add(jsonArray.getString(0));
//            classes.add(jsonArray.getString(1));



//            list = list.getJSONObject("class 5");
//            jsonArray = list.getJSONArray("items");
//            subjects = new ArrayList<>();
//            subjects.add(jsonArray.getString(0));
//            subjects.add(jsonArray.getString(1));
//
//            list = list.getJSONObject("sub 1");
//            jsonArray = list.getJSONArray("items");
//            units = new ArrayList<>();
//            units.add(jsonArray.getString(0));
//            units.add(jsonArray.getString(1));
//
//            list = list.getJSONObject("unit 1");
//            jsonArray = list.getJSONArray("items");
//            sections = new ArrayList<>();
//            sections.add(jsonArray.getString(0));
//            sections.add(jsonArray.getString(1));
//
//            list = list.getJSONObject("section 1");
//            jsonArray = list.getJSONArray("items");
//            subSections = new ArrayList<>();
//            subSections.add(jsonArray.getString(0));
//            subSections.add(jsonArray.getString(1));
//
//            list = list.getJSONObject("sub-section 1");
//            jsonArray = list.getJSONArray("items");
//            topics = new ArrayList<>();
//            topics.add(jsonArray.getString(0));
//            topics.add(jsonArray.getString(1));
//
//            topic1 = new ArrayList<>();
//            topic1.add(list.getString("topic 1"));
//            topic2 = new ArrayList<>();
//            topic2.add(list.getString("topic 2"));
//
//        }catch (JSONException e){
//            Log.e("TeacherCurriculum", "Something wrong with json string", e);
//        }

        // add a list to it and on selection of list items change the list string : e.g. classes to subjects to units
        // get all the string arrays
//        Resources resources = getActivity().getResources();
//        classes = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.classes)));
//        subjects = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.subjects)));
//        units_science_class5 = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.units_science_class5)));
//        units_math_class5 = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.units_math_class5)));

       // mRecycleView = (ListView) rootView.findViewById(R.id.listView_curriculum);
        // creating listView dynamically
        mRecycleView = new RecyclerView(mContext);
//        mRecycleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        mRecycleView.setMinimumHeight(200);

        // create a textView for each item in the list and add to views
//        for (String item : mList){
//            views.add(getTextView(item));
//        }

//        mAdapter = new ArrayAdapter<>(getActivity(),
//                    android.R.layout.simple_list_item_1, mList);
        //mAdapter = new DynamicListAdapter(views, mContext);

        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        mRecycleView.setLayoutManager(llm);

        for(String item : mList){
            Person11 p = new Person11(item);
            curriculumItemList.add(p);
        }

        mAdapter = new DoodleRecyclerViewAdapter<>(curriculumItemList);
        mRecycleView.setAdapter(mAdapter);

//        mRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
////                String item = (String) mAdapter.getItem(position);
//                TextView textView = (TextView) mAdapter.getItem(position);
//                String item = (String) textView.getText();
////                if (item.equalsIgnoreCase("class 5") || item.equalsIgnoreCase("class 6")) {
////                    mArrayAdapter.addAll(subjects);
////                } else if (item.equalsIgnoreCase("sub 1") || item.equalsIgnoreCase("sub 2")) {
////                    mArrayAdapter.addAll(units);
////                } else if (item.equalsIgnoreCase("unit 1") || item.equalsIgnoreCase("unit 2")) {
////                    mArrayAdapter.addAll(sections);
////                } else if (item.equalsIgnoreCase("section 1") || item.equalsIgnoreCase("section 2")) {
////                    mArrayAdapter.addAll(subSections);
////                } else if (item.equalsIgnoreCase("sub-section 1") || item.equalsIgnoreCase("sub-section 2")) {
////                    mArrayAdapter.addAll(topics);
////                } else if (item.equalsIgnoreCase("topic 1")) {
////                    mArrayAdapter.add(topic1);
////                } else if (item.equalsIgnoreCase("topic 2")) {
////                    mArrayAdapter.add(topic2);
////                }
//                int size = dbHelper.getSize(uri);
//                if (size < 5) {     // 5 = depth-1 of json tree class, sub, unit, section, sub-section, topic
//                    mList = getChildList(item);
////                    mAdapter.clear();
////                    mAdapter.addAll(mList);
//                    views.clear();
//                    for (String ele : mList)
//                        views.add(getTextView(ele));
//                    mAdapter = new DynamicListAdapter(views, mContext);
//                }
//            }
//        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button saveButton = ViewProvider.getSimpleButton(mContext, "Save");
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
        saveButton.setId(1);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make each editText non editable
                for (Person11 item : curriculumItemList) {
                    item.isEditable(false);
                }
            }
        });

        Button addButton = ViewProvider.getSimpleButton(mContext, "Add");
        params1.addRule(RelativeLayout.ABOVE, saveButton.getId());
        addButton.setId(2);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch list of items on basis of current uri
                ArrayList<String> listOfItems = dbHelper.getItemsFromUri(uri);
                Bundle args = new Bundle();
                args.putStringArrayList("list",listOfItems);

/*                CurriculumDialogFragment fragment = new CurriculumDialogFragment();
                fragment.setArguments(args);
                fragment.show(getFragmentManager(), "DialogAddItem");*/
            }
        });

        Button editButton = ViewProvider.getSimpleButton(mContext, "Edit");
        params2.addRule(RelativeLayout.ABOVE, addButton.getId());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make each editText editable
                for (Person11 item : curriculumItemList) {
                    item.isEditable(true);
                }
            }
        });

        RelativeLayout rl = ViewProvider.getRelativeLayout(mContext);
        rl.addView(mRecycleView);
        rl.addView(saveButton, params);
        rl.addView(addButton, params1);
        rl.addView(editButton, params2);

        ViewGroup frameLayout = (ViewGroup)rootView.findViewById(R.id.frame_layout_curriculum);     // the viewGroup is a frameLayout
        frameLayout.addView(rl);

//        ((FrameLayout)rootView).addView(mRecycleView);

        return rootView;
    }

    // returns the string list containing the items in the child view i.e. for units it returns sections list
    public ArrayList<String> getChildList(String item){
//        ArrayList<String> childList = new ArrayList<>();
//        String t0 = json.substring(json.indexOf(item)+1);
//        String t1 = t0.substring(t0.indexOf(item));// string from item(e.g. class 5) to end of json
//        String t2 = t1.substring(t1.indexOf("items"));// string from items array end to end of json string
//        String t3 = t2.substring(t2.indexOf("[") + 1, t2.indexOf("]")+1);// now we have string of child items that we need
//
//        Toast.makeText(getActivity(), t3, Toast.LENGTH_SHORT).show();
//        for(;;){
//            // loop, find items, add items to list and break when there is no item in the list
//            String i = t3.substring(t3.indexOf("\"")+1);
//            String j = i.substring(0, i.indexOf("\""));
//            t3 = i.substring(i.indexOf("\"")+1);
//            childList.add(j);\
//
//            if(t3.indexOf("\"") == -1){
//                break;
//            }else {
//                String t4 = t3.substring(t3.indexOf("\"")+1);
//                if(t4.indexOf("\"") == -1){
//                    break;
//                }
//            }
//        }

        stack.push(item);
        addItemToUri(item);
        ArrayList<String> childList = new ArrayList<>();
        childList = dbHelper.fetchList(uri);

        return childList;
    }

    public void addItemToUri(String item){
        uri = uri + item + "/";
    }

    private class DataBaseHelper {

        ArrayList<String> itemList;
        int noOfItems = 0;
        public DataBaseHelper(){

        }
        // returns the current items list
        public ArrayList<String> fetchList(String uri){

            noOfItems = getSize(uri);
            //Toast.makeText(getActivity(), noOfItems, Toast.LENGTH_SHORT).show();
            JSONObject jsonObject;
            itemList = new ArrayList<String>();
            try {
                jsonObject = new JSONObject(json);
                JSONObject list = jsonObject.getJSONObject("list");
                JSONArray jsonArray = list.getJSONArray("items");

                // add each class to the itemList
                for(int i=0; i<jsonArray.length(); i++) {
                    itemList.add(jsonArray.getString(i));
                }

                JSONObject obj = new JSONObject();
                obj = list;
                for( int i=0; i<noOfItems; i++ ){
                    // get to the child we want iteratively
                    String str1 = uri.substring(uri.indexOf("/")+1);
                    String ele = str1.substring(0, str1.indexOf("/"));
                    uri = str1.substring(str1.indexOf("/"));
                    obj = obj.getJSONObject(ele);
                    JSONArray array = obj.getJSONArray("items");

                    itemList.clear();
                    for(int j=0 ; j<array.length(); j++){
                        itemList.add(array.getString(j));
                    }
                }

            }catch (JSONException ex){
                ex.printStackTrace();
                Log.e("DataBaseHelper", "something wrong with json");
            }

            return itemList;
        }

        public int getSize(String uri){ //returns length of uri i.e. no. of items e.g. / = 0, /classes/ = 1, /classes/subjects/ = 2
            int count = 0;
            uri = uri.substring(uri.indexOf("/")+1);    // "classes/"

            for(;;){
                if(uri.equals("") || uri.indexOf("/") == -1)
                    break;
                uri = uri.substring(uri.indexOf("/")+1);
                count++;
            }
            return count;
        }

        public ArrayList<String> getItemsFromUri(String uri1){
            // uri1 is named so, so that we should not modify member uri incidentally
            ArrayList<String> items = new ArrayList<>();
            int length = getSize(uri1);
            if(length==0)
                return null;
            uri1 = uri1.substring(uri.indexOf("/")+1);

            for(int i=0;i<length;i++){
                String str1 = uri1.substring(0, uri1.indexOf("/"));
                uri1 = uri1.substring(uri1.indexOf("/")+1);
                items.add(str1);
            }
            return items;
        }

    }
    public TextView getTextView(String text){
        TextView textView = new TextView(getActivity());
        textView.setText(text);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }


    public class Person11 extends DoodleRecylerViewSetter /*implements View.OnLongClickListener*/{
        String text;
        boolean editable;
        EditText editText;

        Person11(String str){
            text = str;
            editable = false;
        }

        @Override
        protected void setViewChild(ViewGroup v) {
//            final TextView textView = ViewProvider.getTextView(v.getContext(), text);
//            textView.setTextSize(30);
//            textView.setMinHeight(40);
            editText = new EditText(v.getContext());
            editText.setText(text);
            editText.setFocusable(editable);
//            editText.setKeyListener(null);

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editable) {       // when edit text is being edited, disable onClick()
                        return;
                    }
                    Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
                    String item = text;

                    int size = dbHelper.getSize(uri);
                    if (size < 5) {     // 5 = depth-1 of json tree class, sub, unit, section, sub-section, topic
                        mList = getChildList(item);
//                    mAdapter.clear();
//                    mAdapter.addAll(mList);
//                        views.clear();
                        curriculumItemList.clear();
                        for (String ele : mList) {
//                            views.add(getTextView(ele));
                            Person11 p = new Person11(ele);
                            curriculumItemList.add(p);
                        }
//                        mAdapter = new DynamicListAdapter(views, mContext);
                        mAdapter = new DoodleRecyclerViewAdapter<Person11>(curriculumItemList);
                        mRecycleView.setAdapter(mAdapter);
                    }
                }
            });

            // disabling enter key, but it doesn't work yet
            editText.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    // If the event is a key-down event on the "enter" button
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        // Perform action on key press
                        Toast.makeText(getActivity(), "Enter key pressed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }
            });

//            editText.setOnLongClickListener(this);
            v.addView(editText);
        }

        public void isEditable(Boolean editable){
            this.editable = editable;
            if(editable) {
                editText.setClickable(false);       // for edit button
                editText.setOnClickListener(null);
            }
            else {
                editText.setClickable(true);        // for save button
            }

            mAdapter.notifyDataSetChanged();
        }
//        @Override
//        public boolean onLongClick(View view) {
//            Toast.makeText(view.getContext(), "Long click", Toast.LENGTH_SHORT).show();
//            this.editable = true;
//            mAdapter.notifyDataSetChanged();
//            return false;
//        }

    }
}
