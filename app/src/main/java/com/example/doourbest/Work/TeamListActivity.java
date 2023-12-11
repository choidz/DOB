package com.example.doourbest.Work;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

import com.example.doourbest.R;

import java.util.ArrayList;

public class TeamListActivity extends Activity {
    private WorkAdapter tDb;
    private ArrayList<homework> mInfo;
    private ArrayAdapter<homework> mAdapter;

    ListView listView;
    Intent intent;
    Button btn1;


    int[] a = new int[3];x

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        setTitle("팀플게시판");




        btn1 = findViewById(R.id.btn1);
        listView = (ListView) findViewById(R.id.listView);

        intent = getIntent();

        tDb = new WorkAdapter(this);
        mInfo = tDb.getAllwork();
        mAdapter = new ArrayAdapter<homework>(this, android.R.layout.simple_list_item_1, mInfo);
        listView.setAdapter(mAdapter);


        tDb.insertInfo(
                intent.getStringExtra("teamName").toString(),
                intent.getStringExtra("workName").toString(),
                intent.getStringExtra("workContents").toString(),
                intent.getStringExtra("roomPW").toString(),
                intent.getStringExtra("text").toString());
        refreshList();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(TeamListActivity.this, WorkCreateActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(TeamListActivity.this, WorkRoomActivity.class);
                intent.putExtra("tName", mInfo.get(position).getwName());
                intent.putExtra("wName", mInfo.get(position).getwName());
//                intent.putExtra("wContents", mInfo.get(position).getwContents());
                intent.putExtra("rPw", mInfo.get(position).getrPw());
//                intent.putExtra("time", mInfo.get(position).getTime());
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                AlertDialog diaBox = new AlertDialog.Builder(TeamListActivity.this)
                        .setTitle("삭제")
                        .setMessage("방을 삭제하시겠습니까?")
                        .setIcon(R.drawable.login_bg)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                homework i = mInfo.get(position);
                                tDb.deleteInfo(i.getId());
                                refreshList();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .create();
                diaBox.show();
                return false;
            }
        });
        displayTeamList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "All delete!");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        tDb.deleteAll();
        refreshList();
        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy() {
        tDb.close();
        super.onDestroy();
    }


    private void refreshList() {
        mInfo.clear();
        mInfo.addAll(tDb.getAllwork());
        mAdapter.notifyDataSetInvalidated();
    }

    void displayTeamList() {
        //DB의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        WorkAdapter helper = new WorkAdapter(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        //Cursor에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM homework", null);
        //리스트뷰에 목록 채워주는 도구인 adapter준비
        TeamListAdapter adapter = new TeamListAdapter();
        //목록의 개수만큼 반복, adapter에 있는 list배열에 add
        while (cursor.moveToNext()) {
            //num 행은 가장 첫번째에 있으니 0번 다음인 1번 팀명 2번 과제명
            adapter.addItemToList2(cursor.getString(1), cursor.getString(2));
        }
        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        listView.setAdapter(adapter);
    }


}
