package com.example.doourbest.Login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doourbest.R;
import com.example.doourbest.DB.memberDB;

public class RegisterActivity extends AppCompatActivity {
    EditText joinId, joinName, joinPwd;
    Button joinBtn, cancelBtn;

    SQLiteOpenHelper myDB;
    SQLiteDatabase db;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        //회원가입
         joinId = findViewById(R.id.joinId);
         joinName = findViewById(R.id.joinName);
         joinPwd = findViewById(R.id.joinPwd);
         joinBtn = findViewById(R.id.joinBtn);
         cancelBtn = findViewById(R.id.cancelBtn);


        myDB = new memberDB(this, "MemberDB", null, 1);
        db = myDB.getWritableDatabase();



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = joinId.getText().toString().trim();
                String name = joinName.getText().toString().trim();
                String pw = joinPwd.getText().toString().trim();
                if (id.equals("") || pw.equals(""))
                    Toast.makeText(getApplicationContext(), "아이디와 패스워드를 반드시 입력하세요", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        String sql = "insert into member(id,pw,name) values(?,?,?);";
                        String[] args = {id, pw, name};
                        db.execSQL(sql, args);
                        Toast.makeText(RegisterActivity.this, "가입성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "가입실패", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinId.setText("");
                joinName.setText("");
                joinPwd.setText("");
            }
        });
    }



}
