package com.example.doourbest.Work;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doourbest.R;

public class WorkRoomActivity extends Activity {
    Intent intent;

    ImageView imgP1, imgP2;
    TextView txtRName, txtSName, txtRCode;
    ListView listWork;
    ImageButton imageButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setTitle("팀플방");

        imgP1 = findViewById(R.id.imgP1);
        imgP2 = findViewById(R.id.imgP2);
        txtRName = findViewById(R.id.txtRName);
        txtSName = findViewById(R.id.txtSName);
        txtRCode = findViewById(R.id.txtRCode);
        listWork = findViewById(R.id.listWork);
        intent = getIntent();

        imgP1.setImageResource(R.drawable.a1);
        imgP2.setImageResource(R.drawable.a2);
        txtRName.setText(intent.getStringExtra("tName").toString());
        txtSName.setText(intent.getStringExtra("wName").toString());
        txtRCode.setText("방 비밀번호 : " + intent.getStringExtra("rPw").toString());


        displayList();
    }
    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        WorkAdapter helper = new WorkAdapter(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM homework",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        WorkListAdapter adapter = new WorkListAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getString(2),cursor.getString(3), cursor.getString(5));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        listWork.setAdapter(adapter);

    }

}
