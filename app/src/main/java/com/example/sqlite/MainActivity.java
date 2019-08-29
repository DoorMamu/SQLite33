package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
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
Button ma_btn_add,ma_btn_del,ma_btn_mod,ma_btn_vi,ma_btn_viall,ma_btn_show;
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

        ma_btn_add.setOnClickListener(this);
        ma_btn_del.setOnClickListener(this);
        ma_btn_mod.setOnClickListener(this);
        ma_btn_vi.setOnClickListener(this);
        ma_btn_viall.setOnClickListener(this);
        ma_btn_show.setOnClickListener(this);
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
        builder.show();
    }
    public void insert()
    {
        db.execSQL("INSERT INTO student VALUES ('"+ma_et_roll.getText()+"','"+ma_et_name.getText()+"','"+ma_et_marks.getText()+"')");
        showMessage("Success","Record Added");
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
                clearEditText();
                //Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "Del", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_modify:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view_all:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "View All", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_show:
                if(checkForEmptyInput())
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
