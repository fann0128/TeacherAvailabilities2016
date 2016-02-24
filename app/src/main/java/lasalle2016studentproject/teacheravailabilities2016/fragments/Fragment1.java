package lasalle2016studentproject.teacheravailabilities2016.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;



import java.util.ArrayList;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Department;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Program;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;
import lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.DataManager;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.MyListViewAdapter;
import lasalle2016studentproject.teacheravailabilities2016.R;

public class Fragment1 extends Fragment {

    static private Spinner spDep;
    private Spinner spPro;

    static private ArrayAdapter<String> adapterDep;
    private ArrayAdapter<String> adapterPro;

    private ArrayList<Teacher> listOfMatchedTeacher;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_layout1, container, false);


        //---------spinner data loading -------------
        spDep = (Spinner)v.findViewById(R.id.spinnerDepName);
        spPro = (Spinner)v.findViewById(R.id.spinnerProgarm);
        listOfMatchedTeacher = new ArrayList<>();

        adapterDep = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_dropdown_item);
        adapterPro = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_dropdown_item);
        setSpinnerDepItems();

        ListView ListOfResult = (ListView) v.findViewById(R.id.listView);
        ListOfResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent("lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity");

                Bundle teacher = new Bundle();
                // ArrayList<OfficeHour> listOfHours = new ArrayList<OfficeHour>();

                Teacher t = listOfMatchedTeacher.get(position);
                teacher.putSerializable("teacher",t);
                intent.putExtras(teacher);

                CourseInTableActivity.teacher_name = t.getfName() + " " + t.getlName();

                startActivity(intent);


            }
        });




        spDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str = parent.getItemAtPosition(position).toString();
                setSpinnerPrograms(str);
                updateListViewTeacher(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strPro = parent.getItemAtPosition(position).toString();
                String strDep = spDep.getSelectedItem().toString();

                if (strPro != "-") {
                    updateListViewTeacher(strDep, strPro);
                } else {
                    updateListViewTeacher(strDep);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }

    public static void setSpinnerDepItems()
    {
        adapterDep.clear();
        for (Department dp : DataManager.listOfDepartments)
        {
            adapterDep.add(dp.getName().toString());
        }
        spDep.setAdapter(adapterDep);

    }
    public void updateListViewTeacher(String depName)
    {

        ArrayList<Teacher>listOfTea = new ArrayList<>();
        for(Department dep : DataManager.listOfDepartments)
        {
            if(dep.getName()==depName)
            {
                for(Teacher t : dep.getListOfTeachers())
                {
                    listOfTea.add(t);
                }

            }

        }
        listOfMatchedTeacher = listOfTea;
        updateListView(listOfTea);
    }
    public void setSpinnerPrograms(String depName)
    {
        adapterPro.clear();
        adapterPro.add("-");
        for(Department dep : DataManager.listOfDepartments)
        {
            if(dep.getName()==depName)
            {
                for(Program p : dep.getListOfPrograms())
                {
                    adapterPro.add(p.getTitle());
                }

            }

        }

        spPro.setAdapter(adapterPro);

    }
    public void updateListViewTeacher(String depName,String proName)
    {

        ArrayList<Teacher>listOfTea = new ArrayList<>();
        for(Department dep : DataManager.listOfDepartments)
        {
            if(dep.getName()==depName)
            {
                for(Program p:dep.getListOfPrograms())
                {

                    if(p.getTitle() == proName)
                    {
                        listOfTea = p.getListOfTeachers();

                    }


                }


            }

        }
        listOfMatchedTeacher = listOfTea;
        updateListView(listOfTea);
    }

    private void updateListView( ArrayList<Teacher> listOfMatchedTeachers)
    {

        //instantiate custom adapter
        MyListViewAdapter adapter = new MyListViewAdapter(listOfMatchedTeachers, getContext());

        //handle listview and assign adapter
        ListView lView = (ListView) getActivity().findViewById(R.id.listView);
        lView.setAdapter(adapter);
    }





}
