package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.justwayward.booker.R;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailActivity extends Activity {
    private MyDatabaseHelper dbHelper;
    private ListView msgListView;//aa
    private MsgAdapter adapter;//1
    private EditText inputText;
    private Button sumButton;
    private String data1;
    private static String content1,type1;
    private static int temp = Msg.TYPE_SENT;
    private List<Msg> msgList = new ArrayList<Msg>();//2

    static String[] smart_data = {
            "chapter",
            "solution",
            "otherbook",
            "writecont"
    };
    public static void actionStart(Context context, String data2, String data3) {
        Intent intent = new Intent(context, ShowDetailActivity.class);
        intent.putExtra("para2", data2);
        intent.putExtra("title", data3);//????????????
        context.startActivity(intent);//StartActivityForResult??activity??id????setResult??????
        //???????????????????Activity.actionStart(?????????,????)??
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.planing);
        final String[] smart_Msg = {
                getString(R.string.first_title)+"\n\n\n",
                getString(R.string.second_title)+"\n\n\n",
                getString(R.string.third_title)+"\n\n\n",
                getString(R.string.four_title)+"\n\n\n"
        };

        final String data = getIntent().getStringExtra("title");
        data1 = data;
        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        final SQLiteDatabase db = dbHelper.getWritableDatabase();//???§Õ???????
        final ContentValues values = new ContentValues();
        Cursor cursor = db.query("swot",null,null,null,null,null,null);

        inputText = (EditText)findViewById(R.id.input_sum);
        sumButton = (Button)findViewById(R.id.sumButton);
        adapter = new MsgAdapter(ShowDetailActivity.this,R.layout.msg_item,msgList);//activity,id,msg.   item and the msg
        msgListView = (ListView)findViewById(R.id.planing_list_view);//listview
        msgListView.setAdapter(adapter);//connect
        if(getIntent().getStringExtra("para2").equals("done"))
        {
            sumButton.setVisibility(View.INVISIBLE);
            inputText.setVisibility(View.INVISIBLE);
        }
        if(cursor.moveToFirst())
        {
            do{
                String name = cursor.getString(cursor.getColumnIndex("title"));
                if(name.equals(data))
                    {
                        Msg msg1 = new Msg(name,Msg.TYPE_RECEIVED);

                        for(int i=0; i<smart_data.length;i++) {
                            if (getIntent().getStringExtra("para2").equals(smart_Msg[i])) {
                                name = cursor.getString(cursor.getColumnIndex(smart_data[i]));
                                content1 = name;
                                type1=smart_data[i];
                                msg1 = new Msg(name, Msg.TYPE_RECEIVED);
                                msgList.add(msg1);
                                break;
                            }
                        }
                    break;
                }

            }
            while(cursor.moveToNext());
        }

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String context = inputText.getText().toString();
                if(!"".equals(context))
                {
                    context = content1 +"\n\n\n" +"" +context;
                    values.put(type1,context);
                    db.update("swot",values,"title = ?", new String[]{data1});
                    finish();
                }
            }
        });
    }
}
