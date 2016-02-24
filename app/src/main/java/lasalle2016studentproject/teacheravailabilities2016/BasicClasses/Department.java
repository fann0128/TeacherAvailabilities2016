package lasalle2016studentproject.teacheravailabilities2016.BasicClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yangf on 2016/1/27.
 */
public class Department implements Serializable {
    private int id;
    private String name;
    private String desc;
    private ArrayList<Teacher> listOfTeachers = new ArrayList<>();
    private ArrayList<Program> listOfPrograms = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Teacher> getListOfTeachers() {
        return listOfTeachers;
    }

    public void setListOfTeachers(ArrayList<Teacher> listOfTeachers) {
        this.listOfTeachers = listOfTeachers;
    }

    public ArrayList<Program> getListOfPrograms() {
        return listOfPrograms;
    }

    public void setListOfPrograms(ArrayList<Program> listOfPrograms) {
        this.listOfPrograms = listOfPrograms;
    }
}
