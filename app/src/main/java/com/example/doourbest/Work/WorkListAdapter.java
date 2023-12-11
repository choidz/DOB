package com.example.doourbest.Work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doourbest.R;

import java.util.ArrayList;

public class WorkListAdapter extends BaseAdapter {

    ArrayList<homework> list = new ArrayList<homework>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            view = inflater.inflate(R.layout.homework_item, viewGroup, false);
        }

        //이제 아이템에 존재하는 텍스트뷰 객체들을 view객체에서 찾아 가져온다
        TextView txtIRName = (TextView) view.findViewById(R.id.txtIRName);
        TextView txtIRName2 = (TextView) view.findViewById(R.id.txtIRName2);
        TextView txtTimer = (TextView) view.findViewById(R.id.txtTimer);

        //현재 포지션에 해당하는 아이템에 글자를 적용하기 위해 list배열에서 객체를 가져온다.
        homework listdata = list.get(i);

        //가져온 객체안에 있는 글자들을 각 뷰에 적용한다
        txtIRName.setText(listdata.getwName());
        txtIRName2.setText(listdata.getwContents());
        txtTimer.setText("제출기한 : " + listdata.getTime());

        return view;
    }

    //ArrayList로 선언된 list 변수에 목록을 채워주기 위함 다른방시으로 구현해도 됨
    public void addItemToList(String wName, String wContents, String time){
        homework listdata = new homework();

        listdata.setwName(wName);
        listdata.setwContents(wContents);
        listdata.setTime(time);

        //값들의 조립이 완성된 listdata객체 한개를 list배열에 추가
        list.add(listdata);

    }
}