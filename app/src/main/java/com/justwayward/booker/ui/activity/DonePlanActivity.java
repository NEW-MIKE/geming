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
import android.widget.Toast;

import com.justwayward.booker.R;

import java.util.ArrayList;
import java.util.List;

public class DonePlanActivity extends Activity {

    public static void actionStart(Context context, String data2, String data3)
    {
        Intent intent = new Intent(context,DonePlanActivity.class);
        intent.putExtra("para1",data2);
        intent.putExtra("para2",data3);
        context.startActivity(intent);

    }
    private MyDatabaseHelper dbHelper;
    private ListView msgListView;
    private MsgAdapter adapter;
    private static int temp = Msg.TYPE_SENT;
    private List<Msg> msgList = new ArrayList<Msg>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.doneplan);
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.planingxml);
        layout.setBackgroundResource(R.drawable.done);
        int control = 0;
        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        final SQLiteDatabase db = dbHelper.getWritableDatabase();//ִ
        final ContentValues values = new ContentValues();
        Cursor cursor = db.query("swot",null,null,null,null,null,null);

        adapter = new MsgAdapter(DonePlanActivity.this,R.layout.msg_item,msgList);//activity,id,msg.   item and the msg
        msgListView = (ListView)findViewById(R.id.doneplan_list_view);//listview
        msgListView.setAdapter(adapter);//connect
        Msg msg1;
        if(cursor.moveToLast())
        {
            do{
                    String name = cursor.getString(cursor.getColumnIndex("title"));
                    if(control == 0) {
                        msg1 = new Msg(name, Msg.TYPE_RECEIVED);
                        control = 1;
                    }
                    else
                    {
                        msg1 = new Msg(name, Msg.TYPE_SENT);
                        control = 0;
                    }
                    msgList.add(msg1);
            }
            while(cursor.moveToPrevious());
        }

        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Msg msg = msgList.get(position);//
                NextTitle.actionStart(DonePlanActivity.this,msg.getContent(),"done1");
            }
        });
    }
}
