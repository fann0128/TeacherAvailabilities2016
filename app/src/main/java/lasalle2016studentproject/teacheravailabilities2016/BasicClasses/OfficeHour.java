package lasalle2016studentproject.teacheravailabilities2016.BasicClasses;

import java.io.Serializable;

/**
 * Created by yangf on 2016/1/27.
 */
public class OfficeHour implements Serializable {
    private int id;
    private int roomNumber;
    private int dayOfWeek;
    private int startTime;
    private int endTime;
    private int duration;
    private String desc;
    private int teacherId;



    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }




    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
