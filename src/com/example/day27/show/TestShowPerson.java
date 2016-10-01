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
		//�����ݿ�����ݲ����
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
		//����Ļ����ʾ����
		for(Person p : personList){
			//1.������ÿ��һ��Ԫ�أ���newһ��TextView
			TextView tv = new TextView(this);
			//2.�����ı�����
			tv.setText(p.toString());
			//3.��TextView����Ϊ���Բ����ӽڵ�
			ll.addView(tv);
		}
	}

}
