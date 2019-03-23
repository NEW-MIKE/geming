package com.justwayward.booker.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.justwayward.booker.R;

public class MyViewActivity extends Activity {

    private Button edit, view;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_view);
        edit = (Button) findViewById(R.id.edit);
        view = (Button) findViewById(R.id.view);
        dbHelper = new MyDatabaseHelper(this, "swot.db", null, 2);//more than 1
        dbHelper.getWritableDatabase();//???��???????

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrePlanActivity.actionStart(MyViewActivity.this, "edit", "edit");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonePlanActivity.actionStart(MyViewActivity.this, "read", "read");
            }
        });

    }
}
