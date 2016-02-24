package lasalle2016studentproject.teacheravailabilities2016.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;



import java.util.ArrayList;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;
import lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.DataManager;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.MyListViewAdapter;
import lasalle2016studentproject.teacheravailabilities2016.R;


public class Fragment3 extends Fragment {


    private ListView listView;
    private static ArrayList<Teacher> sampleTeachers = new ArrayList<>();
    public static ArrayList<Teacher> listOfTeachers = new ArrayList<>();
    private int textLength;
    public static MyListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_layout3, container, false);
        final EditText mySearchText =  (EditText) v.findViewById(R.id.editSearch);
        updateListOfTeacherSearch();
        listView = (ListView) v.findViewById(R.id.listOfItems);
        final MyListViewAdapter adapter = new MyListViewAdapter(sampleTeachers, getContext());
        listView.setAdapter(adapter);




        // occur when an item clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent("lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity");

                Bundle teacher = new Bundle();
                // ArrayList<OfficeHour> listOfHours = new ArrayList<OfficeHour>();

                Teacher t = sampleTeachers.get(position);
                teacher.putSerializable("teacher",t);
                intent.putExtras(teacher);

                CourseInTableActivity.teacher_name = t.getfName() + " " + t.getlName();
                startActivity(intent);

            }
        });

        mySearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textLength = mySearchText.getText().length();
                sampleTeachers.clear();

                for (int i = 0; i < listOfTeachers.size(); i++)
                    if (DataManager.getDepNameById(listOfTeachers.get(i).getDepId()).toLowerCase().contains((mySearchText.getText().toString().toLowerCase()).trim()) ||
                            listOfTeachers.get(i).getfName().toLowerCase().contains(mySearchText.getText().toString().toLowerCase().trim()) ||
                            listOfTeachers.get(i).getlName().toLowerCase().contains(mySearchText.getText().toString().toLowerCase().trim())) {

                        // Search doesn't support full name
                        sampleTeachers.add(listOfTeachers.get(i));
                    }
                if (mySearchText.getText().length() <= 0) {
                    if (adapter != null) {
                        sampleTeachers.clear();
                       adapter.notifyDataSetChanged();
                    }
                } else {
                   adapter.notifyDataSetChanged();
                }

            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }



    public static void updateListOfTeacherSearch()
    {
        listOfTeachers = DataManager.listOfTeachers;
    }




}
