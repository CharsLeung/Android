package com.example.administrator.myapplication04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/22.
 */
public class StudentDetail extends ActionBarActivity implements View.OnClickListener  {
    private Button btnSave,btnDelete;
    private Button btnClose;
    private EditText etName;
    private EditText etEmail;
    private EditText etAge;
    private TextView tvgrade;
    private int _student_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置作用域，目标为XML对象
        setContentView(R.layout.activity_student_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAge = (EditText) findViewById(R.id.etAge);
        tvgrade = (TextView) findViewById(R.id.tvgrade);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _student_id =0;
        Intent intent = getIntent();
        _student_id =intent.getIntExtra("student_Id", 0);
        StudentRepo repo = new StudentRepo(this);
        Student student = new Student();
        student = repo.getStudentById(_student_id);
        String temp = repo.getStudentOnduty(_student_id);

        etAge.setText(String.valueOf(student.age));
        etName.setText(student.name);
        etEmail.setText(student.email);
        tvgrade.setText(""+temp);
    }




    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.btnSave)){
            StudentRepo repo=new StudentRepo(this);
            Student student=new Student();
            student.age=Integer.parseInt(etAge.getText().toString());
            student.email=etEmail.getText().toString();
            student.name=etName.getText().toString();
            student.student_ID=_student_id;

            if(_student_id==0){
                _student_id=repo.insert(student);
                Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
            }else{
                repo.update(student);
                Toast.makeText(this,"学生记录更新！",Toast.LENGTH_SHORT).show();
            }
        }else if (v== findViewById(R.id.btnDelete)){
            StudentRepo repo = new StudentRepo(this);
            repo.delete(_student_id);
            Toast.makeText(this, "学生记录删除", Toast.LENGTH_SHORT);
            finish();
        }else if (v== findViewById(R.id.btnClose)){
            finish();
        }
    }
}
