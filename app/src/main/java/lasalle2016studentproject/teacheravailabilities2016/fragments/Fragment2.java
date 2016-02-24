package lasalle2016studentproject.teacheravailabilities2016.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.ArrayList;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;
import lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.DataManager;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.MyListViewAdapter;
import lasalle2016studentproject.teacheravailabilities2016.R;

/**
 * Created by Admin on 2016-01-20.
 */
public class Fragment2 extends Fragment {

    private static ArrayList<Teacher> listOfTeas = new ArrayList<>();
    private static ArrayList<Teacher> listOfFavs = new ArrayList<>();
    private static ListView listView;

    private static MyListViewAdapter adapter ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_layout2, container, false);
        listView = (ListView)v.findViewById(R.id.listViewFav);

        adapter = new MyListViewAdapter(listOfFavs,getContext());
        listView.setAdapter(adapter);
        updateListViewResource();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent("lasalle2016studentproject.teacheravailabilities2016.CourseInTableActivity");

                    Bundle teacher = new Bundle();
                    // ArrayList<OfficeHour> listOfHours = new ArrayList<OfficeHour>();

                    Teacher t = listOfFavs.get(position);
                    teacher.putSerializable("teacher", t);
                    intent.putExtras(teacher);

                    CourseInTableActivity.teacher_name = t.getfName() + " " + t.getlName();
                    startActivity(intent);

            }
        });


        return v;
    }

    public static void updateListViewResource()
    {
        listOfTeas = DataManager.listOfTeachers;
        checkFav();
    }

    private static void checkFav() {
        listOfFavs.clear();
        for(Teacher t:listOfTeas)
        {
            if(t.getFavorite().toString().equals("true"))
            {
                listOfFavs.add(t);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
