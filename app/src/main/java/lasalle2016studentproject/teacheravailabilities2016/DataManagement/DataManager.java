package lasalle2016studentproject.teacheravailabilities2016.DataManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import java.sql.SQLException;
import java.util.ArrayList;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Department;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.OfficeHour;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Program;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Relation;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;

/**
 * Created by yangf on 2016/1/27.
 */
public  class DataManager {

    public static ArrayList<Department> listOfDepartments = new  ArrayList<Department>();
    public static ArrayList<Teacher> listOfTeachers = new ArrayList<>();
    public static ArrayList<Program> listOfPrograms = new ArrayList<>();
    public static ArrayList<Relation> listOfRelations = new ArrayList<>();
    public static ArrayList<Teacher> listOfFavs = new ArrayList<>();
    public static ArrayList<OfficeHour> listOfAllOHs = new ArrayList<>();

    private static DatabaseHelper dbHelper;

    public static ArrayList<Department> dbDepartmentInsert(Context context)
    {
        dbHelper = new DatabaseHelper(context);
        ArrayList<Department> listOfDepartments = new ArrayList<Department>();


        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        try {
            Cursor cursor = dbHelper.QueryData("select * from DEPARTMENT");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Department oneDep = new Department();
                        oneDep.setId(Integer.valueOf(cursor.getString(0)));
                        oneDep.setName(cursor.getString(1));
                        oneDep.setDesc(cursor.getString(2));
                        listOfDepartments.add(oneDep);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        return listOfDepartments;
    }

    public static ArrayList<Program> dbProgramInsert()
    {

        ArrayList<Program> listOfPrograms = new ArrayList<Program>();




        try {
                    Cursor cursor = dbHelper.QueryData("select * from PROGRAM");
                    if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Program onePrg = new Program();
                        onePrg.setId(Integer.valueOf(cursor.getString(0)));
                        onePrg.setTitle(cursor.getString(1));
                        onePrg.setDesc(cursor.getString(2));
                        onePrg.setDepId(cursor.getInt(3));
                        listOfPrograms.add(onePrg);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        return listOfPrograms;
    }

    public static ArrayList<Teacher> dbTeacherInsert(boolean fav)
    {

        ArrayList<Teacher> listOfTeachers = new ArrayList<Teacher>();

        try {
            Cursor cursor = null;
            if(!fav)
                cursor = dbHelper.QueryData("select * from Teacher");
            else
                cursor = dbHelper.QueryData("select * from teacher where favorite = 'true'");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Teacher oneTeacher = new Teacher();
                        oneTeacher.setId(Integer.valueOf(cursor.getString(0)));
                        oneTeacher.setfName(cursor.getString(1));
                        oneTeacher.setlName(cursor.getString(2));
                        oneTeacher.setEmail(cursor.getString(3));
                        oneTeacher.setDesc(cursor.getString(4));
                        oneTeacher.setFavorite(cursor.getString(5));
                        oneTeacher.setDepId(cursor.getInt(6));
                        listOfTeachers.add(oneTeacher);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        return listOfTeachers;
    }

    public static ArrayList<OfficeHour> dbOfficeHourInsert()
    {

        ArrayList<OfficeHour> listOfOfficeHours = new ArrayList<OfficeHour>();


        try {
            Cursor cursor = dbHelper.QueryData("select * from OFFICEHOUR");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        OfficeHour oneOfficeHour = new OfficeHour();
                        oneOfficeHour.setId(Integer.valueOf(cursor.getString(0)));
                        oneOfficeHour.setRoomNumber(cursor.getInt(1));
                        oneOfficeHour.setDayOfWeek(cursor.getInt(2));
                        oneOfficeHour.setStartTime(Integer.valueOf(cursor.getString(3)));
                        oneOfficeHour.setEndTime(Integer.valueOf(cursor.getString(4)));
                        oneOfficeHour.setDesc(cursor.getString(5));
                        oneOfficeHour.setTeacherId(Integer.valueOf(cursor.getString(6)));

                        oneOfficeHour.setDuration(oneOfficeHour.getEndTime() - oneOfficeHour.getStartTime());

                        listOfOfficeHours.add(oneOfficeHour);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        return listOfOfficeHours;
    }

    public static ArrayList<Relation> dbRelationInsert()
    {

        ArrayList<Relation> listOfRelations = new ArrayList<Relation>();
        try {
            Cursor cursor = dbHelper.QueryData("select * from relation");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Relation oneRel = new Relation();
                        oneRel.setId(Integer.valueOf(cursor.getString(0)));
                        oneRel.setRel_name(cursor.getString(1));
                        oneRel.setProgramId(cursor.getInt(2));
                        oneRel.setTeacherId(cursor.getInt(3));
                        listOfRelations.add(oneRel);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        return listOfRelations;


    }
    //-----------clear all lists--------------
    private static void clearAllArrayLists()
    {   listOfDepartments.clear();
        listOfTeachers.clear();
        listOfFavs.clear();
        listOfPrograms.clear();
        listOfRelations.clear();
        listOfAllOHs.clear();
    }



    public static ArrayList<OfficeHour> loadTeacherOfficeHours(Teacher tea)
    {
        ArrayList<OfficeHour> listOfOHs = new ArrayList<>();
        int teacherId = tea.getId();

        try {
            Cursor cursor = dbHelper.QueryData("select * from OFFICEHOUR where TEACHER_ID = "+teacherId);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        OfficeHour oneOfficeHour = new OfficeHour();
                        oneOfficeHour.setId(Integer.valueOf(cursor.getString(0)));
                        oneOfficeHour.setRoomNumber(cursor.getInt(1));
                        oneOfficeHour.setDayOfWeek(cursor.getInt(2));
                        oneOfficeHour.setStartTime(Integer.valueOf(cursor.getString(3)));
                        oneOfficeHour.setEndTime(Integer.valueOf(cursor.getString(4)));
                        oneOfficeHour.setDesc(cursor.getString(5));
                        oneOfficeHour.setTeacherId(Integer.valueOf(cursor.getString(6)));

                        oneOfficeHour.setDuration(oneOfficeHour.getEndTime() - oneOfficeHour.getStartTime());

                        listOfOHs.add(oneOfficeHour);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}


        return listOfOHs;
    }

    public static ArrayList<Teacher> loadProgramTeachers(Program pro)
    {
        ArrayList<Teacher> listOfProTeachers = new ArrayList<>();
        int  proId = pro.getId();
        ArrayList<Relation> listOfRelations = new ArrayList<Relation>();
        try {
            Cursor cursor = dbHelper.QueryData("select * from relation where PROGRAM_ID ="+proId);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Relation oneRel = new Relation();
                        oneRel.setId(Integer.valueOf(cursor.getString(0)));
                        oneRel.setRel_name(cursor.getString(1));
                        oneRel.setProgramId(cursor.getInt(2));
                        oneRel.setTeacherId(cursor.getInt(3));
                        listOfRelations.add(oneRel);
                    } while (cursor.moveToNext());
                }
            }

        } catch (SQLException e) {}

        //------input teachers into listOfTeachers without officeHour
        for(Relation rel : listOfRelations)
        {
            for(Teacher t:listOfTeachers)
            {
                if(rel.getTeacherId() == t.getId())
                {
                    listOfProTeachers.add(t);
                }

            }

        }

        //-----load office by each teacher
        for(Teacher t:listOfProTeachers)
        {
            t.setListOfOfficeHours(loadTeacherOfficeHours(t));
        }

        return listOfProTeachers;

    }

    public  static  void addTeacherFav(Teacher t,Context context)
    {
        ContentValues cv = new ContentValues();
        cv.put("FAVORITE", "true");
        dbHelper.Update(t, cv);
        reLoadAllData(context);
    }
    public  static  void removeTeacherFav(Teacher t,Context context)
    {
        ContentValues cv = new ContentValues();
        cv.put("FAVORITE","false");
        dbHelper.Update(t, cv);
        reLoadAllData(context);
    }

    public static String getDepNameById(int id)
    {
        String name = null;
        for(Department d:listOfDepartments)
        {
            if(d.getId()==id)
            {
                name = d.getName();
            }
        }
        return name;
    }

    public static void reLoadAllData(Context context)
    {
        clearAllArrayLists();


        // -------use insert to load data from sqlite-----------
        listOfDepartments = dbDepartmentInsert(context);
        listOfTeachers = dbTeacherInsert(false);
        listOfPrograms = dbProgramInsert();
        listOfRelations = dbRelationInsert();
        listOfAllOHs = dbOfficeHourInsert();
        listOfFavs = dbTeacherInsert(true);


        for(Teacher t:listOfTeachers)
        {
            ArrayList<Integer> prgs = new ArrayList<>();
            ArrayList<Program> listOfPrgs = new ArrayList<>();
            for(Relation r:listOfRelations)
            {
                if(r.getTeacherId()==t.getId())
                {
                    prgs.add(r.getProgramId());
                }

            }
            for(Integer i : prgs)
            {
                for(Program p: listOfPrograms)
                {
                    if(i == p.getId())
                    {
                        listOfPrgs.add(p);
                    }
                }

            }
            t.setListOfPrograms(listOfPrgs);

        }



        for(Department dep : listOfDepartments)
        {
            ArrayList<Teacher> ts = new ArrayList<>();
            for(Teacher t : listOfTeachers)
            {
               t.setListOfOfficeHours(loadTeacherOfficeHours(t));

                if(t.getDepId() == dep.getId())
                {
                    ts.add(t);
                }
            }
            dep.setListOfTeachers(ts);


            ArrayList<Program> lps = new ArrayList<>();
            for (Program p : listOfPrograms)
            {

                if(p.getDepId()==dep.getId())
                {
                    p.setListOfTeachers(loadProgramTeachers(p));
                    lps.add(p);
                }
            }
            dep.setListOfPrograms(lps);
        }



    }



}
