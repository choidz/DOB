package com.example.doourbest.Login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doourbest.Team.NoTeamActivity;
import com.example.doourbest.R;
import com.example.doourbest.DB.memberDB;
import com.example.doourbest.Work.TeamListActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtPwd, edtId;
    Button btnJoin, btnLogin;

    SQLiteDatabase db;
    SQLiteOpenHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");



        //로그인
        edtPwd = findViewById(R.id.edtPwd);
        edtId = findViewById(R.id.edtId);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);

        myDB = new memberDB(this, "MemberDB", null, 1);
        db = myDB.getWritableDatabase();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtId.getText().toString();
                String pw = edtPwd.getText().toString();
                String teamN = "DoOurBest";
                String workN = "Android 팀플과제";
                String workC ="팀플 과제 관리 어플";
                String roomPwd ="dob";
                String date =null;
                String memberName = null;

                try {
                    String sql = "select name from member where id=? and pw=?;";
                    String args[] = {id, pw};
                    Cursor cursor = db.rawQuery(sql, args);
                    while (cursor.moveToNext()) {
                        memberName = cursor.getString(0);
                    }
                    if (memberName == null) {
                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), NoTeamActivity.class);
                        in.putExtra("id", id);
                        in.putExtra("name", memberName);
                        startActivity(in);
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "아이디와 패스워드를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                edtId.setText("");
                edtPwd.setText("");
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            } // 인텐트에 메뉴액티비티 넣어서 호출
        });


    }


}

