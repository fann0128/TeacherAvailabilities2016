package lasalle2016studentproject.teacheravailabilities2016.BasicClasses;

import java.io.Serializable;

/**
 * Created by yangf on 2016/1/27.
 */
public class Relation implements Serializable {
    private int id;
    private String rel_name;
    private int programId;
    private int teacherId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRel_name() {
        return rel_name;
    }

    public void setRel_name(String rel_name) {
        this.rel_name = rel_name;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
