package com.example.doourbest.Team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doourbest.R;
import com.example.doourbest.Work.TeamListActivity;
import com.example.doourbest.Work.WorkCreateActivity;

public class NoTeamActivity extends AppCompatActivity {

    Button NoTeamBtn;
    Button goList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteam);
        setTitle("팀 추가");
        Button NoTeamBtn = findViewById(R.id.NoTeamBtn);
        Button goList = findViewById(R.id.goList);


        NoTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkCreateActivity.class);
                startActivity(intent);


            }
        });

        goList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TeamListActivity.class);
                startActivity(intent);
            }
        });


    }
}
