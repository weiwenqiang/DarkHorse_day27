package com.example.day27.show;

import java.util.ArrayList;
import java.util.List;

import com.example.day27.R;
import com.example.day27.sqlite.MyOpenHelper;
import com.example.entity.Person;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestShowPerson extends Activity {
	List<Person> personList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b1_showperson);
		
		personList = new ArrayList<Person>();
		//把数据库的内容查出来
		MyOpenHelper oh = new MyOpenHelper(this);
		SQLiteDatabase db = oh.getWritableDatabase();
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);//"3,20"
		while(cursor.moveToNext()){
			String _id = cursor.getString(0);
			String name = cursor.getString(1);
			String salary = cursor.getString(2);
			String phone = cursor.getString(3);
			Person p = new Person(_id, name, salary, phone);
			personList.add(p);
		}
		LinearLayout ll = (LinearLayout) findViewById(R.id.b1_showperson);
		//在屏幕上显示数据
		for(Person p : personList){
			//1.集合中每有一条元素，就new一个TextView
			TextView tv = new TextView(this);
			//2.设置文本内容
			tv.setText(p.toString());
			//3.把TextView设置为线性布局子节点
			ll.addView(tv);
		}
	}

}
