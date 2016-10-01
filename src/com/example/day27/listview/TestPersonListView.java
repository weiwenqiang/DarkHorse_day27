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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestPersonListView extends Activity {
	List<Person> personList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_listview);
		
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
		ListView lv = (ListView) findViewById(R.id.b2_listview);
		lv.setAdapter(new MyAdapter());
		
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			//ϵͳ���ã�������ȡ�������ж���Ԫ��
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
			//��ϵͳ���ã���ȡһ��View������ΪListView����Ŀ
			//position:���η������������ص�View������listview�д��ڵڼ�����Ŀ����ôposition��Ϊ����
			Log.d("getView", "getView����"+position);
			
			TextView tv = new TextView(TestPersonListView.this);
			Person p = personList.get(position);
			tv.setText(p.toString());
			tv.setTextSize(18);
			return tv;
		}
		
	}
}
