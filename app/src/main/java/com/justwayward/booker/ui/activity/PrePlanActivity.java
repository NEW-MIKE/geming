package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.justwayward.booker.R;

import java.util.Calendar;
import java.util.TimeZone;

public class PrePlanActivity extends Activity {

    private Button nextButton;
    private TextView smart_textview;
    private static String ltaData="";
    private EditText preEditText;
    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;
    String my_time_1;
    String my_time_2;
    static int i = 0;
    private MyDatabaseHelper dbHelper;
  //  Resources res = getResources();
  //  String[] smart_Msg = getResources().getStringArray(R.array.geming_array);

/*            {
            getString(R.string.first_title),
            "chapter",
            "solution",
            "otherbook",
            "writecont"
    };*/
    static String[] smart_data = {
            "title",
            "chapter",
            "solution",
            "otherbook",
            "writecont"
    };

    public static void actionStart(Context context, String data2, String data3) {
        Intent intent = new Intent(context, PrePlanActivity.class);
        intent.putExtra("para1", data2);
        intent.putExtra("para2", data3);//????????????
        context.startActivity(intent);//StartActivityForResult??activity??id????setResult??????
        //???????????????????Activity.actionStart(?????????,????)??
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preplan);
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final String[] smart_Msg = {
                getString(R.string.geming_topic),
                getString(R.string.first_title),
                getString(R.string.second_title),
                getString(R.string.third_title),
                getString(R.string.four_title),
        };
        preEditText = (EditText)findViewById(R.id.preEdit);
        smart_textview = (TextView)findViewById(R.id.textViewSmart);

        nextButton = (Button)findViewById(R.id.button_next);
        dbHelper = new MyDatabaseHelper(this,"swot.db",null,2);//more than 1
        final SQLiteDatabase db = dbHelper.getWritableDatabase();//????????????
        final ContentValues values = new ContentValues();
        Cursor cursor = db.query("swot",null,null,null,null,null,null);


        smart_textview.setText(smart_Msg[i]);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cal = Calendar.getInstance();
                year = String.valueOf(cal.get(Calendar.YEAR));
                month = String.valueOf(cal.get(Calendar.MONTH)+1);
                day = String.valueOf(cal.get(Calendar.DATE));
                if (cal.get(Calendar.AM_PM) == 0)
                    hour = String.valueOf(cal.get(Calendar.HOUR));
                else
                hour = String.valueOf(cal.get(Calendar.HOUR)+12);
                minute = String.valueOf(cal.get(Calendar.MINUTE));
                second = String.valueOf(cal.get(Calendar.SECOND));

                my_time_1 = year + "-" + month + "-" + day;
                my_time_2 = hour + ":" + minute + ":" + second;

                ltaData = getString(R.string.geming_topic)+"\n¡¶"+preEditText.getText().toString()+"¡·"+"\n";

                values.put(smart_data[0],ltaData);
                for(i = 1;i < smart_data.length;i++)
                values.put(smart_data[i],smart_Msg[i]);
                ltaData="";
                if((i) == smart_data.length)
                {
                    db.insert("swot",null,values);
                    values.clear();
                    i = 0;
                    ltaData="";
                    finish();
                }
                smart_textview.setText(smart_Msg[i]);
                preEditText.setText("");
            }
        });
    }
}
