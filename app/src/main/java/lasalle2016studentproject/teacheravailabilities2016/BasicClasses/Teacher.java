package lasalle2016studentproject.teacheravailabilities2016.BasicClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yangf on 2016/1/27.
 */
public class Teacher implements Serializable {
    private int id;
    private String fName;
    private String lName;
    private int depId;
    private String email;
    private String desc;
    private String favorite;
    private ArrayList<OfficeHour> listOfOfficeHours = new ArrayList<>();

    public ArrayList<Program> getListOfPrograms() {
        return listOfPrograms;
    }

    public void setListOfPrograms(ArrayList<Program> listOfPrograms) {
        this.listOfPrograms = listOfPrograms;
    }

    private ArrayList<Program> listOfPrograms = new ArrayList<>();

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public ArrayList<OfficeHour> getListOfOfficeHours() {
        return listOfOfficeHours;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getDepId() {
        return depId;
    }

    public String getEmail() {
        return email;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setListOfOfficeHours(ArrayList<OfficeHour> listOfOfficeHours) {
        this.listOfOfficeHours = listOfOfficeHours;
    }
}
