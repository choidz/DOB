package com.example.doourbest.Work;

import android.content.ContentValues;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "doourbest.db";
    private static final int VERSION = 1;
    private static final String ID = "_id";
    private static final String TEAMNAME = "teamName";
    private static final String WORKNAME = "workName";
    private static final String WORKCONTENTS = "workContents";
    private static final String ROOMPW = "roomPW";
    private static final String TIME = "time";

    private static final String TABLE_NAME = "homework";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " integer primary key autoincrement, " +
                    TEAMNAME + " text not null, " +
                    WORKNAME + " text not null, " +
                    WORKCONTENTS + " text not null, " +
                    ROOMPW + " text not null, " +
                    TIME + " text not null )";

    private SQLiteDatabase db;

    public WorkAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        db.close();
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // create
    public boolean insertInfo(String teamName, String workName, String workContents, String roomPW, String time) {
        ContentValues cv = new ContentValues();
        cv.put(TEAMNAME, teamName);
        cv.put(WORKNAME, workName);
        cv.put(WORKCONTENTS, workContents);
        cv.put(ROOMPW, roomPW);
        cv.put(TIME, time);
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    // read
    public ArrayList<homework> getAllwork() {
        ArrayList<homework> info = new ArrayList<homework>();
        Cursor c = db.query(TABLE_NAME, new String[]{ID, TEAMNAME, WORKNAME, WORKCONTENTS, ROOMPW, TIME}, null, null, null, null, ID + " DESC");

        if (c.moveToFirst()) { //homework테이블의 첫번째행부터 id,teamname,,,,이 다 있으면 밑에 do-while문 실행
            final int indexId = c.getColumnIndex(ID);
            final int indexTeamName = c.getColumnIndex(TEAMNAME);
            final int indexWorkName = c.getColumnIndex(WORKNAME);
            final int indexWorkContents = c.getColumnIndex(WORKCONTENTS);
            final int indexRoomPW = c.getColumnIndex(ROOMPW);
            final int indexTime = c.getColumnIndex(TIME);
            do { // 인덱스에 있는 값을 가져오며, 인덱스가 없을때까지 반복
                int id = c.getInt(indexId);
                String teamname = c.getString(indexTeamName);
                String workname = c.getString(indexWorkName);
                String workcontents = c.getString(indexWorkContents);
                String roompw = c.getString(indexRoomPW);
                String time = c.getString(indexTime);
                info.add(new homework(id, teamname, workname, workcontents, roompw, time));
            } while (c.moveToNext());
        }
        c.close();
        return info;
    }

    // update
    public boolean updateInfo(homework i) {
        ContentValues cv = new ContentValues();
        cv.put(TEAMNAME, i.gettName());
        cv.put(WORKNAME, i.getwName());
        cv.put(WORKCONTENTS, i.getwContents());
        cv.put(ROOMPW, i.getrPw());
        cv.put(TIME, i.getTime());
        String[] params = new String[]{Integer.toString(i.getId())};
        int result = db.update(TABLE_NAME, cv, ID + "=?", params);
        return result > 0;
    }

    // delete
    public boolean deleteInfo(int id) {
        String[] params = new String[]{Integer.toString(id)};
        int result = db.delete(TABLE_NAME, ID + "=?", params);
        return result > 0;
    }

    public boolean deleteAll() {
        int result = db.delete(TABLE_NAME, null, null);
        return result > 0;
    }
}

