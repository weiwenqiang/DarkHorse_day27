package com.example.day27.listview;

import java.util.ArrayList;
import java.util.List;

import com.example.day27.R;
import com.example.day27.sqlite.MyOpenHelper;
import com.example.entity.Person;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
//import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestListView extends Activity {
	List<Person> personList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_listview);
		
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
		ListView lv = (ListView) findViewById(R.id.b2_listview);
		lv.setAdapter(new MyAdapter());
		
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			//系统调用，用来获取集合中有多少元素
			return personList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//由系统调用，获取一个View对象，作为ListView的条目
			//position:本次方法调用所返回的View对象，在listview中处于第几个条目，那么position就为多少
			Log.d("getView", "getView调用"+position+";convertView="+convertView);
			Person p = personList.get(position);
			View v;
			//优化，判断是否有缓存，如果有，直接使用，没有再创建
			if(convertView == null){
				//把布局文件填充成一个View对象
				v = View.inflate(TestListView.this, R.layout.b3_person_item, null);
				
				TextView tv_name = (TextView) v.findViewById(R.id.person_name);
				tv_name.setText(p.getName());
				TextView tv_id = (TextView) v.findViewById(R.id.person_id);
				tv_id.setText(p.get_id());
				TextView tv_salary = (TextView) v.findViewById(R.id.person_salary);
				tv_salary.setText(p.getSalary());
				TextView tv_phone = (TextView) v.findViewById(R.id.person_phone);
				tv_phone.setText(p.getPhone());
			}else{
				v = convertView;
			}

			//方法一：把布局文件填充成一个View对象
//			View v =View.inflate(TestListView.this, R.layout.b3_person_item, null);
			//通过资源id查找组件，注意调用的是View对象的
//			TextView tv_name = (TextView) v.findViewById(R.id.person_name);
//			tv_name.setText(p.getName());
//			TextView tv_id = (TextView) v.findViewById(R.id.person_id);
//			tv_id.setText(p.get_id());
//			TextView tv_salary = (TextView) v.findViewById(R.id.person_salary);
//			tv_salary.setText(p.getSalary());
//			TextView tv_phone = (TextView) v.findViewById(R.id.person_phone);
//			tv_phone.setText(p.getPhone());
			
			//方法二：获取布局填充器对象
//			LayoutInflater inflater = LayoutInflater.from(TestListView.this);
			//使用布局填充器填充布局文件
//			View v2 = inflater.inflate(R.layout.b3_person_item, null);
			
			//方法三：可以获取多种工具
//			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v3 = inflater2.inflate(R.layout.b3_person_item, null);
			
			return v;
		}
		
	}
}
