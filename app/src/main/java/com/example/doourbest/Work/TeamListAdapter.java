package com.example.doourbest.Work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doourbest.R;

import java.util.ArrayList;

public class TeamListAdapter extends BaseAdapter {

    ArrayList<homework> teamlist = new ArrayList<homework>();

    @Override
    public int getCount() {
        return teamlist.size();
    }

    @Override
    public Object getItem(int i) {
        return teamlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        //리스트뷰에 아이템이 인플레이트 되어있는지 확인한후
        //아이템이 없다면 아래처럼 아이템 레이아웃을 인플레이트 하고 view객체에 담는다.
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.team_item, viewGroup, false);
        }

        //이제 아이템에 존재하는 텍스트뷰 객체들을 view객체에서 찾아 가져온다
        TextView teamTitle =  view.findViewById(R.id.teamTitle);
        TextView teamSub =  view.findViewById(R.id.teamSub);

        //현재 포지션에 해당하는 아이템에 글자를 적용하기 위해 list배열에서 객체를 가져온다.
        homework listdata2 = teamlist.get(i);

        //가져온 객체안에 있는 글자들을 각 뷰에 적용한다
        teamTitle.setText(listdata2.gettName());
        teamSub.setText(listdata2.getwName());

        return view;
    }

    //ArrayList로 선언된 list 변수에 목록을 채워주기 위함 다른방시으로 구현해도 됨
    public void addItemToList2(String tName, String wName) {
        homework listdata = new homework();

        listdata.settName(tName);
        listdata.setwName(wName);


        //값들의 조립이 완성된 listdata객체 한개를 list배열에 추가
        teamlist.add(listdata);

    }
}