package lasalle2016studentproject.teacheravailabilities2016.BasicClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yangf on 2016/1/27.
 */
public class Program implements Serializable {
    private int id;
    private String title;
    private String desc;
    private int depId;
    private ArrayList<Teacher> listOfTeachers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public ArrayList<Teacher> getListOfTeachers() {
        return listOfTeachers;
    }

    public void setListOfTeachers(ArrayList<Teacher> listOfTeachers) {
        this.listOfTeachers = listOfTeachers;
    }
}
