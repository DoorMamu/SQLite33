package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/*   created by Mukund Kulkarni
 *   on  29-8-19
 *   copyright thunderousbandit
 */
public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
EditText ma_et_name,ma_et_roll,ma_et_marks;
Button ma_btn_add,ma_btn_del,ma_btn_mod,ma_btn_vi,ma_btn_viall,ma_btn_show,ma_btn_delall,ma_clrall;
SQLiteDatabase db;


    //***********Step1*************************
//Prepare  initialization and listener,and create db and table if not exist
    public void init()
    {
        ma_et_marks=findViewById(R.id.et_marks);
        ma_et_name=findViewById(R.id.et_name);
        ma_et_roll=findViewById(R.id.et_roll);
        ma_btn_add=findViewById(R.id.btn_add);
        ma_btn_del=findViewById(R.id.btn_del);
        ma_btn_mod=findViewById(R.id.btn_modify);
        ma_btn_vi=findViewById(R.id.btn_view);
        ma_btn_viall=findViewById(R.id.btn_view_all);
        ma_btn_show=findViewById(R.id.btn_show);
        ma_btn_delall=findViewById(R.id.btn_del_all);
        ma_clrall=findViewById(R.id.btn_clr);

        ma_btn_add.setOnClickListener(this);
        ma_btn_del.setOnClickListener(this);
        ma_btn_mod.setOnClickListener(this);
        ma_btn_vi.setOnClickListener(this);
        ma_btn_viall.setOnClickListener(this);
        ma_btn_show.setOnClickListener(this);
        ma_btn_delall.setOnClickListener(this);
        ma_clrall.setOnClickListener(this);
        //*****************Step 2********************
        db=openOrCreateDatabase("StudendDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");

    }
    //****************step 3***************************
    public boolean checkForEmptyInput()
    {
        if(ma_et_roll.getText().toString().trim().length()==0 || ma_et_name.getText().toString().trim().length()==0 ||
                ma_et_marks.getText().toString().trim().length()==0)
        {
            return true;
        }else {return  false;}
    }
    //***************step 4***************************
    public void clearEditText()
    {
        ma_et_marks.setText("");
        ma_et_name.setText("");
        ma_et_roll.setText("");
    }
    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.show();
    }
    public void insert()
    {
        //insert the record into the database
        db.execSQL("INSERT INTO student VALUES ('"+ma_et_roll.getText()+"','"+ma_et_name.getText()+"','"+ma_et_marks.getText()+"')");
        showMessage("Success","Record Added");
        clearEditText();
    }
    public void delete()
    {
        //get the row(s) retrieved into the cursor
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+ma_et_roll.getText()+"'",null);
        //if move to the first row op is success then delete else display invalid roll
        if(c.moveToFirst())
        {
            db.execSQL("DELETE FROM student WHERE rollno='"+ma_et_roll.getText()+"'");
            showMessage("Success","Record Deleted");
        }
        else
        {
            showMessage("Error","Invalid Roll");
        }
        clearEditText();
    }

    public void modify()
    {
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+ma_et_roll.getText()+"'",null);
        //if move to the first row op is success then update else display invalid roll

        if(c.moveToFirst())
        {
            db.execSQL("UPDATE student SET name='"+ma_et_name.getText()+"',marks='"+ma_et_marks.getText()+"' WHERE rollno='"+ma_et_roll.getText()+"'");
            showMessage("Success","Record Modified");
        }
        else
        {
            showMessage("Error","Invalid Roll");
        }
        clearEditText();
    }
    public void view()
    {
        //get the row(s) retrieved into the cursor
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+ma_et_roll.getText()+"'",null);
        //if move to the first row op is success then Display else display invalid roll
        if(c.moveToFirst())
        {
            ma_et_name.setText(c.getString(1));
            ma_et_marks.setText(c.getString(2));
        }
        else
        {
            showMessage("Error","Invalid Roll");
            clearEditText();
        }

    }
    public void viewAll()
    {
        //get the row(s) retrieved into the cursor
        Cursor c=db.rawQuery("SELECT * FROM student",null);
        //if move to the first row op is success then Display else display invalid roll
        if(c.getCount()==0)
        {
            showMessage("Student details","No records Exists");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Roll No : "+c.getString(0)+"\n");
            buffer.append("Name : "+c.getString(1)+"\n");
            buffer.append("Marks : "+c.getString(2)+"\n\n");
        }
        showMessage("Student Details",buffer.toString());
    }

    public void delAll()
    {
        //get the row(s) retrieved into the cursor
        Cursor c=db.rawQuery("SELECT * FROM student",null);
        //if move to the first row op is success then Delete all
        if(c.getCount()==0)
        {
            showMessage("Info","Records already deleted");
            return;
        }
        while(c.moveToNext())
        {
            db.execSQL("DELETE FROM student WHERE rollno='"+c.getString(0)+"'");
        }
        showMessage("Student Details","All records deleted");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    //*****************Step 1********************
    //If Button is clicked then get the id and do further operations
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_add:
                //************Step3*********************
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //************stp 4*********************
                insert();
                //Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                if(ma_et_roll.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Please enter Roll no");
                    return;
                }
                delete();
                //Toast.makeText(this, "Del", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_modify:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                modify();
                //Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view:
                if(ma_et_roll.getText().toString().trim().length()==0)
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                view();
                //Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view_all:
                /*if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //Toast.makeText(this, "View All", Toast.LENGTH_SHORT).show();
                viewAll();
                break;
            case R.id.btn_show:
                /*if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();
                showMessage("Student Record Application ","Developed By Chandan Prasad Enhanced By Mukund Kulkarni");
                break;
            case R.id.btn_del_all:
                delAll();
                break;
            case R.id.btn_clr:
                clearEditText();
                break;
        }
    }

    //**************Step 5*****************************
    //create menu ops
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.me_set:
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                //        startActivity(new Intent(MainActivity.this,Setting.class));
                break;
            case R.id.me_wait:
        //        startActivity(new Intent(MainActivity.this,Waiting.class));
                AlertDialog.Builder bldr=new AlertDialog.Builder(this);
                bldr.setIcon(R.mipmap.ic_launcher_round);
                bldr.setTitle("Waiting");
                bldr.setMessage("Keep Waiting");
                bldr.setCancelable(true);
                bldr.show();
                    break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu myMenu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,myMenu);
        return true;
    }
}
