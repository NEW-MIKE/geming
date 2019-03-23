package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.justwayward.booker.R;
import com.justwayward.booker.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import static com.justwayward.booker.R.drawable.planing;

public class PlaningActivity extends Activity {
    public static void actionStart(Context context, String data2, String data3)
    {
        Intent intent = new Intent(context,PlaningActivity.class);
        intent.putExtra("para1",data2);
        intent.putExtra("para2",data3);//����һ�����ֵ
        context.startActivity(intent);//StartActivityForResult��activity��id����setResult������
        //�Ժ����������ʱ��ֱ��Activity.actionStart(����֮ǰ�Ļ,����)��
    }

    private MyDatabaseHelper dbHelper;
    private int flg =1;
    private ListView msgListView;//aa
    private MsgAdapter adapter;//1
    private static int temp = Msg.TYPE_SENT;
    private List<Msg> msgList = new ArrayList<Msg>();//2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.doneplan);
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.planingxml);
        layout.setBackgroundResource(R.drawable.planing);
        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        final SQLiteDatabase db = dbHelper.getWritableDatabase();//ִ��д�Ĳ�����
        Cursor cursor = db.query("swot",null,null,null,null,null,null);

        adapter = new MsgAdapter(PlaningActivity.this,R.layout.msg_item,msgList);//activity,id,msg.   item and the msg
        msgListView = (ListView)findViewById(R.id.doneplan_list_view);//listview
        msgListView.setAdapter(adapter);//connect
        if(cursor.moveToFirst())
        {
            do{
                if(cursor.getString(cursor.getColumnIndex("sum")).equals(" ")) {
                    String name = cursor.getString(cursor.getColumnIndex("title"));
                    Msg msg1 = new Msg(name, Msg.TYPE_RECEIVED);
                    msgList.add(msg1);
                }
            }
            while(cursor.moveToNext());
        }

		msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Msg msg = msgList.get(position);//
                ShowDetailActivity.actionStart(PlaningActivity.this,msg.getContent(),"planing");
                Toast.makeText(PlaningActivity.this,msg.getContent(),Toast.LENGTH_SHORT).show();
			}
		});

    }

}
