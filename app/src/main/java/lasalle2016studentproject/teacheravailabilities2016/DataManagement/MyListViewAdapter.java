package lasalle2016studentproject.teacheravailabilities2016.DataManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Program;
import lasalle2016studentproject.teacheravailabilities2016.BasicClasses.Teacher;
import lasalle2016studentproject.teacheravailabilities2016.R;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment1;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment2;
import lasalle2016studentproject.teacheravailabilities2016.fragments.Fragment3;


/**
 * Created by Admin on 2016-01-20.
 */
public class MyListViewAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Teacher> list = new ArrayList<Teacher>();
    private Context context;



    public MyListViewAdapter(ArrayList<Teacher> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;

    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_item, null);
        }
        //Sign TextView to parameter from custom_list_item
        TextView tv_dep = (TextView)view.findViewById(R.id.tv_Dep);


        tv_dep.setText(DataManager.getDepNameById(list.get(position).getDepId()));

        TextView program = (TextView)view.findViewById(R.id.tv_prgs);





        TextView tv_TeacherName = (TextView)view.findViewById(R.id.tv_name);
        tv_TeacherName.setText(list.get(position).getfName()+" "+list.get(position).getlName());

        ArrayList<Program> listOfPrgs=list.get(position).getListOfPrograms();
        String strPrg = "";
        for(Program p : listOfPrgs)
        {
            strPrg+= p.getTitle()+"/";
        }

        program.setText(strPrg);

        //Handle buttons and add onClickListeners
        final ImageButton btnFav = (ImageButton)view.findViewById(R.id.imageButton);
        final ImageButton btnEmail = (ImageButton)view.findViewById(R.id.imageButton2);


        btnEmail.setImageResource(R.drawable.new_email_icon);

        boolean fav = false;
        for(Teacher t : DataManager.listOfFavs)
        {
            if(t.getId() == list.get(position).getId())
            {
                fav = true;
            }

        }

        if(fav)
        {
            btnFav.setImageResource(R.drawable.fav_on);
        }
        else
        {
            btnFav.setImageResource(R.drawable.fav_off);
        }



        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{list.get(position).getEmail()});
                i.putExtra(Intent.EXTRA_SUBJECT,"Availability/Disponibilités");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    context.startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context,"There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
       btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something

                boolean fav = false;
                for(Teacher t : DataManager.listOfFavs)
                {
                    if(t.getId() == list.get(position).getId())
                    {
                        fav = true;
                    }

                }
                if(!fav) {
                    DataManager.addTeacherFav(list.get(position), context);
                    btnFav.setImageResource(R.drawable.fav_on);
                    Toast toast = Toast.makeText(context,R.string.addFav,Toast.LENGTH_LONG);
                    toast.show();
                    Fragment1.setSpinnerDepItems();
                    Fragment2.updateListViewResource();
                    Fragment3.updateListOfTeacherSearch();
                }
                else
                {
                    DataManager.removeTeacherFav(list.get(position),context);
                    btnFav.setImageResource(R.drawable.fav_off);
                    Toast toast = Toast.makeText(context,R.string.removeFav,Toast.LENGTH_LONG);
                    toast.show();
                    Fragment1.setSpinnerDepItems();
                    Fragment2.updateListViewResource();
                    Fragment3.updateListOfTeacherSearch();
                }

            }
        });



        return view;
    }

}
