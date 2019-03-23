package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.justwayward.booker.R;
import com.justwayward.booker.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends Activity {
	/*在此添加启动一个活动的最佳方式：
	public static void actionStart(Context context, String data2,String data3)
	{
		Intent intent = new Intent(context,xxx.class);
		intent.putExtra("para1",data2);
		intent.putExtra("para2",data3);//向下一个活动传值
		context.startActivity(intent);//StartActivityForResult（activity，id）；setResult结束。
		//以后调用这个活动的时候，直接Activity.actionStart(进入之前的活动,数据)；
		
	}*/
    private ListView msgListView;//aa
    private EditText inputText;
    private Button send;
    private Button jump;
    private MsgAdapter adapter;//1
    private static int temp = Msg.TYPE_SENT;
    private List<Msg> msgList = new ArrayList<Msg>();//2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diaglog);

        initMsgs();
        adapter = new MsgAdapter(DialogActivity.this,R.layout.msg_item,msgList);//activity,id,msg.   item and the msg
        msgListView = (ListView)findViewById(R.id.msg_list_view);//listview
        msgListView.setAdapter(adapter);//connect
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        jump = (Button)findViewById(R.id.jump);
		/*在此添加一个item的点击事件
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@override
			public void onItemClick(AdapterView<?> parent,View view,int position, long id)
			{
				list = listview.getPosition(position);//获取当前子项的位置
			}
		})*/
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String context = inputText.getText().toString();
                if(!"".equals(context))
                {
                    SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putString("save",context);
                    editor.commit();
                    Msg msg = new Msg(context,temp);
                    msgList.add(msg);//default connection
                    adapter.notifyDataSetChanged();//adapter
                    msgListView.setSelection(msgList.size());//msg ,msglist,msglistview. this can add the listview.
                    inputText.getText().clear();
                    if (Msg.TYPE_SENT == temp)
                    {
                        temp = Msg.TYPE_RECEIVED;
                    }
                    else
                    {
                        temp = Msg.TYPE_SENT;
                    }
                }
            }
        });
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initMsgs()
    {
        LogUtils.i("initMsgs() ");
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name = pref.getString("save","");
        Msg msg1 = new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2= new Msg("Hello who is that.",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("I am a Thinker.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4 = new Msg(name,Msg.TYPE_SENT);
        msgList.add(msg4);

    }

}
