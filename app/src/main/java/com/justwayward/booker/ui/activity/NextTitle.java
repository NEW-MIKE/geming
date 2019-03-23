package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.justwayward.booker.R;

import java.util.ArrayList;
import java.util.List;

public class NextTitle extends Activity {
    public static void actionStart(Context context, String data2, String data3) {
        Intent intent = new Intent(context, NextTitle.class);
        intent.putExtra("para1", data2);
        intent.putExtra("para2", data3);//????????????
        context.startActivity(intent);//StartActivityForResult??activity??id????setResult??????
    }
    private MyDatabaseHelper dbHelper;
    private ListView msgListView;//aa
    private MsgAdapter adapter;//1
    private String parameter;
    private static int temp = Msg.TYPE_SENT;
    private List<Msg> msgList = new ArrayList<Msg>();//2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.doneplan);
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.planingxml);
        layout.setBackgroundResource(R.drawable.done);

        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        final SQLiteDatabase db = dbHelper.getWritableDatabase();//???§Õ???????
        final ContentValues values = new ContentValues();
        Cursor cursor = db.query("swot",null,null,null,null,null,null);


        adapter = new MsgAdapter(NextTitle.this,R.layout.msg_item,msgList);//activity,id,msg.   item and the msg
        msgListView = (ListView)findViewById(R.id.doneplan_list_view);//listview
        msgListView.setAdapter(adapter);//connect

        Msg msg1 = new Msg(getString(R.string.first_title)+"\n\n\n", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg(getString(R.string.second_title)+"\n\n\n", Msg.TYPE_RECEIVED);
        msgList.add(msg2);
        Msg msg3 = new Msg(getString(R.string.third_title)+"\n\n\n", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4 = new Msg(getString(R.string.four_title)+"\n\n\n", Msg.TYPE_RECEIVED);
        msgList.add(msg4);
        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Msg msg = msgList.get(position);//
                ShowDetailActivity.actionStart(NextTitle.this,msg.getContent(),getIntent().getStringExtra("para1"));
            }
        });

    }
}
