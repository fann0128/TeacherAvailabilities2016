package lasalle2016studentproject.teacheravailabilities2016;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.OfficeHour;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Program;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;
import lasalle2016studentproject.teacheravailabilities2016.DataManagement.DataManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by yangf on 2016/1/20.
 */
public class CourseInTableActivity extends AppCompatActivity {
    protected TextView empty;

    protected TextView monColum;

    protected TextView tueColum;

    protected TextView wedColum;

    protected TextView thrusColum;

    protected TextView friColum;

    protected RelativeLayout course_table_layout;

    protected int aveWidth;

    protected int screenWidth;

    protected int gridHeight ;

    private ArrayList<OfficeHour> listOfOfficeHours;
    private Teacher teacher;

    private TextView tvTeacherRoom;
    private TextView tvTeacherDep;
    private TextView tvTeacherNbOfHours;

    private ScrollView myScrollView;


    public static String teacher_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setElevation(0);
        setTitle(teacher_name);

        setContentView(R.layout.activity_course_detail_in_table);
        tvTeacherRoom = (TextView)findViewById(R.id.textViewTeacherRoom);
        tvTeacherDep = (TextView)findViewById(R.id.textViewTeacherDep);
        tvTeacherNbOfHours = (TextView)findViewById(R.id.tvNbOfOHs);



        empty = (TextView) this.findViewById(R.id.test_empty);
        monColum = (TextView) this.findViewById(R.id.test_monday_course);
        tueColum = (TextView) this.findViewById(R.id.test_tuesday_course);
        wedColum = (TextView) this.findViewById(R.id.test_wednesday_course);
        thrusColum = (TextView) this.findViewById(R.id.test_thursday_course);
        friColum = (TextView) this.findViewById(R.id.test_friday_course);

        myScrollView = (ScrollView)findViewById(R.id.scroll_body);

        course_table_layout = (RelativeLayout) this.findViewById(R.id.test_course_rl);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;

        int aveWidth = width / 6;

        empty.setWidth(aveWidth);
        monColum.setWidth(aveWidth);
        tueColum.setWidth(aveWidth);
        wedColum.setWidth(aveWidth);
        thrusColum.setWidth(aveWidth);
        friColum.setWidth(aveWidth);

        this.screenWidth = width;
        this.aveWidth = aveWidth;
        init();

        Bundle bd = getIntent().getExtras();

        teacher = (Teacher)bd.getSerializable("teacher");
        listOfOfficeHours = teacher.getListOfOfficeHours();


        ArrayList<Program> listOfPrgs = teacher.getListOfPrograms();
        String strPrg = "";
        for(Program p : listOfPrgs)
        {
            strPrg+= p.getTitle()+"\n";
        }

        tvTeacherNbOfHours.setText(strPrg);


        tvTeacherDep.setText(DataManager.getDepNameById(teacher.getDepId()));

        ArrayList<Integer> listOfRooms = new ArrayList<>();

        for(OfficeHour oh : listOfOfficeHours)
        {
            addNewCourseIntoTable(oh.getDayOfWeek(),oh.getStartTime(),oh.getDuration(),String.valueOf(oh.getTeacherId()),oh.getRoomNumber());
            listOfRooms.add(oh.getRoomNumber());
        }
        List noRepeatRooms = removeDuplicateWithOrder(listOfRooms);

        String Rooms = "";
        for(int i=0;i<noRepeatRooms.size();i++)
        {
            Rooms+=String.valueOf(noRepeatRooms.get(i))+"\t";
        }


        tvTeacherRoom.setText(Rooms);

        tvTeacherRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myScrollView.smoothScrollTo(0, scrollViewToFirstHour());
            }
        });

        myScrollView.post(new Runnable() {
            @Override
            public void run() {
                myScrollView.smoothScrollTo(0, scrollViewToFirstHour());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.detail_email_icon:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{teacher.getEmail()});
                i.putExtra(Intent.EXTRA_SUBJECT, "Office Hour");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    this.startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet(list.size());
        set.addAll(list);
        List newList = new ArrayList(set.size());
        newList.addAll(set);
        return newList;
    }

    private int scrollViewToFirstHour()
    {
        int firstHour=18;
        int y;
        for(OfficeHour oh:this.listOfOfficeHours)
        {
            if(oh.getStartTime()<firstHour)
            {
                firstHour = oh.getStartTime();
            }
        }
        y=(firstHour-8)*gridHeight;

        return y;

    }


    protected void init() {

        int maxHours = 10;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //height of screen

        int height = dm.heightPixels;
        gridHeight = (height / maxHours)-10;

        for(int i = 1; i <= maxHours; i ++){

            for(int j = 1; j <= 6; j ++){

                TextView tx = new TextView(CourseInTableActivity.this);
                tx.setId((i - 1) * 6  + j);

                if(j < 6)
                    tx.setBackgroundResource(R.drawable.course_text_view_bg);
                else
                    tx.setBackgroundResource(R.drawable.course_table_last_colum);
                //relativeLayout
                RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                        aveWidth ,gridHeight);
                //set text-align
                tx.setGravity(Gravity.CENTER);
                //set font
                if (Build.VERSION.SDK_INT < 23) {
                    tx.setTextAppearance(this, R.style.courseTableText);
                }
                else
                {
                    tx.setTextAppearance(R.style.courseTableText);
                }
                //if the first column（1 to 12）
                if(j == 1)
                {
                    String hourDef = (i+7)+":00 ";

                    //tx.setText(String.valueOf(i));
                    tx.setText(hourDef);
                    rp.width = aveWidth ;
                    //setRelativeLayout
                    if(i == 1)
                        rp.addRule(RelativeLayout.BELOW, empty.getId());
                    else
                        rp.addRule(RelativeLayout.BELOW, (i - 1) * 6);
                }
                else
                {
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 6  + j - 1);
                    rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 6  + j - 1);
                    tx.setText("");
                }

                tx.setLayoutParams(rp);
                course_table_layout.addView(tx);
            }
        }

    }


    public void addNewCourseIntoTable(int dayOfWeek, int hourOfDay, int duration, String teacherName, int officeRoom)
    {

        int[] background = {R.drawable.courseBlue,R.drawable.courseGrey,R.drawable.courseGreen,R.drawable.courseYellow,
                R.drawable.courseRed};
        //---------add new courseInfo into table ---------------------
        TextView courseInfo = new TextView(CourseInTableActivity.this);
        courseInfo.setId(1000*dayOfWeek + hourOfDay);

        courseInfo.setText(hourOfDay + ":00\n"+"Room\n"+officeRoom);
        //textview hight
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(aveWidth,duration*gridHeight);


        rlp.topMargin = gridHeight*(hourOfDay-8);
        rlp.addRule(RelativeLayout.RIGHT_OF, dayOfWeek);
        //align-text center
        courseInfo.setGravity(Gravity.CENTER);
        //set backgroud color

        courseInfo.setBackgroundResource(background[0]);
        courseInfo.setTextSize(12);
        courseInfo.setLayoutParams(rlp);
        courseInfo.setTextColor(Color.WHITE);
        // setAlpha
        courseInfo.getBackground().setAlpha(222);

        course_table_layout.addView(courseInfo);


        if(dayOfWeek == 1)
        {
            monColum.setBackgroundResource(background[2]);
        }
        else if(dayOfWeek==2)
        {
            tueColum.setBackgroundResource(background[2]);
        }
        else if(dayOfWeek==3)
        {
            wedColum.setBackgroundResource(background[2]);
        }
        else if(dayOfWeek==4)
        {
            thrusColum.setBackgroundResource(background[2]);
        }
        else
        {
            friColum.setBackgroundResource(background[2]);
        }

    }



}
