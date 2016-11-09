package com.example.administrator.myapplication04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener { // AdapterView.OnItemClickListener
    private Button btnAdd, btnGetAll,btnOnduty,btnGrade;
    private TextView student_Id;
    private SearchView searchView;//搜索框
    //   private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置作用域，目标为XML对象
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
        btnOnduty = (Button) findViewById(R.id.btnOnduty);
        btnOnduty.setOnClickListener(this);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            private String str=getClass().getSimpleName();
            @Override
            public boolean onQueryTextSubmit(String query) {
                StudentRepo repo = new StudentRepo(MainActivity.this);
                int temp = repo.getStudentByName(query);
                Intent objIndent = new Intent(MainActivity.this, StudentDetail.class);
                objIndent.putExtra("student_Id", temp);
                startActivity(objIndent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //btnGrade = (Button) findViewById(R.id.btnGrade);
        //btnGrade.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btnAdd)) {
            Intent intent = new Intent(this, StudentDetail.class);
            intent.putExtra("student_Id", 0);
            startActivity(intent);

        }else if(v==findViewById(R.id.btnOnduty)){
            Intent intent1 = new Intent(this, StudentOnduty.class);
            intent1.putExtra("student_Id", 0);
            startActivity(intent1);
        }else {

            StudentRepo repo = new StudentRepo(this);
            ArrayList<HashMap<String,String>> studentList = repo.getStudentList();  //获取数据源
            if (studentList.size() != 0) {

                ListView lv= (ListView) findViewById(R.id.list);
                assert lv != null; //假设lv不为空
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        student_Id = (TextView) view.findViewById(R.id.student_Id);//*************
                        String studentId = student_Id.getText().toString();
                        // HashMap<String, String> itmap = (HashMap<String, String>) parent.getItemAtPosition(position);
                        Intent objIndent = new Intent(getApplicationContext(), StudentDetail.class);
                        objIndent.putExtra("student_Id", Integer.parseInt(studentId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, studentList, R.layout.view_student_entry, new String[]{"id", "name"}, new int[]{R.id.student_Id, R.id.student_name});
                lv.setAdapter(adapter);
            } else {
                Toast.makeText(this, "还没有学生哦", Toast.LENGTH_SHORT).show();
            }
        }
    }

}