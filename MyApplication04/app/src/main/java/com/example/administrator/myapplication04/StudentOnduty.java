package com.example.administrator.myapplication04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/25.
 */
public class StudentOnduty extends AppCompatActivity implements View.OnClickListener {//implements  AdapterView.OnItemClickListener
    private ListView lv_onduty;
    private TextView tv_showTime;
    public Button saveInfo,cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studnet_onduty);
        tv_showTime = (TextView) findViewById(R.id.tv_showtime);
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate =new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        tv_showTime.setText(str); //显示时间
        lv_onduty = (ListView) findViewById(R.id.lv_onduty);
        StudentRepo repo = new StudentRepo(this);
        ArrayList<HashMap<String,String>> studentList = repo.getStudentList();
        saveInfo=(Button)findViewById(R.id.btnSave_detail) ;
        saveInfo.setOnClickListener(this);
        cancel=(Button)findViewById(R.id.btnCancel);
        cancel.setOnClickListener(this);
        MyAdapter01 adapter = new MyAdapter01(this);
        adapter.setList(studentList);
        lv_onduty.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave_detail: {
                Toast.makeText(this, "考勤情况已保存", Toast.LENGTH_SHORT).show();
                finish();
            }
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
}
