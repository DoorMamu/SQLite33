package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
EditText ma_et_name,ma_et_roll,ma_et_marks;
Button ma_btn_add,ma_btn_del,ma_btn_mod,ma_btn_vi,ma_btn_viall,ma_btn_show;
//***********Step1*************************
//Prepare initial initialization and listener
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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //If Button is clicked then get the id and do further operations
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_add:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_modify:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_view_all:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_show:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
