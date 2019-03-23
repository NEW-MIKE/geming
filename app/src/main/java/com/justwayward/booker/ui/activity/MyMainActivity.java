package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.justwayward.booker.R;

public class MyMainActivity extends Activity {
    private Button Strength;
    private Button Weakness;
    private Button Opportunity,threats;
    private MyDatabaseHelper dbHelper;
    String data;
    public static void actionStart(Context context, String data2, String data3)
    {
        Intent intent = new Intent(context,MyMainActivity.class);
        intent.putExtra("para1",data2);
        intent.putExtra("para2",data3);//����һ�����ֵ
        context.startActivity(intent);//StartActivityForResult��activity��id����setResult������
        //�Ժ����������ʱ��ֱ��Activity.actionStart(����֮ǰ�Ļ,����)��
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_main_activity);
        Strength = (Button)findViewById(R.id.strength);
        Weakness = (Button)findViewById(R.id.weakness);
        Opportunity = (Button)findViewById(R.id.opportunity);
        threats = (Button)findViewById(R.id.threats);
        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        dbHelper.getWritableDatabase();//ִ��д�Ĳ�����


        data = getIntent().getStringExtra("para1");//remember this.
        Strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("edit"))
                {
                    PrePlanActivity.actionStart(MyMainActivity.this,data,"strength");
                }
                else
                DonePlanActivity.actionStart(MyMainActivity.this,data,"strength");
            }
        });

        Weakness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("edit"))
                {
                    PrePlanActivity.actionStart(MyMainActivity.this,data,"weakness");
                }
                else
                DonePlanActivity.actionStart(MyMainActivity.this,data,"weakness");
            }
        });

        Opportunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("edit"))
                {
                    PrePlanActivity.actionStart(MyMainActivity.this,data,"opportunity");
                }
                else
                DonePlanActivity.actionStart(MyMainActivity.this,data,"opportunity");
            }
        });

        threats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("edit"))
                {
                    PrePlanActivity.actionStart(MyMainActivity.this,data,"threats");
                }
                else
                DonePlanActivity.actionStart(MyMainActivity.this,data,"threats");
            }
        });
    }
}
