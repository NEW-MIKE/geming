package com.justwayward.booker.ui.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_SWOT= "create table swot ("
			+"id integer primary key autoincrement,"
			+ "title text,"
			+ "chapter text,"
			+ "solution text,"
			+ "otherbook text,"
	        + "writecont text)";
	private Context mContext;
	
	public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(context,name,factory,version);
		mContext = context;
	}

	@Override 
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_SWOT);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("drop table if exists swot");
		onCreate(db);
	}
}