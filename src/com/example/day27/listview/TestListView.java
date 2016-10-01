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
			Log.d("getView", "getView����"+position+";convertView="+convertView);
			Person p = personList.get(position);
			View v;
			//�Ż����ж��Ƿ��л��棬����У�ֱ��ʹ�ã�û���ٴ���
			if(convertView == null){
				//�Ѳ����ļ�����һ��View����
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

			//����һ���Ѳ����ļ�����һ��View����
//			View v =View.inflate(TestListView.this, R.layout.b3_person_item, null);
			//ͨ����Դid���������ע����õ���View�����
//			TextView tv_name = (TextView) v.findViewById(R.id.person_name);
//			tv_name.setText(p.getName());
//			TextView tv_id = (TextView) v.findViewById(R.id.person_id);
//			tv_id.setText(p.get_id());
//			TextView tv_salary = (TextView) v.findViewById(R.id.person_salary);
//			tv_salary.setText(p.getSalary());
//			TextView tv_phone = (TextView) v.findViewById(R.id.person_phone);
//			tv_phone.setText(p.getPhone());
			
			//����������ȡ�������������
//			LayoutInflater inflater = LayoutInflater.from(TestListView.this);
			//ʹ�ò����������䲼���ļ�
//			View v2 = inflater.inflate(R.layout.b3_person_item, null);
			
			//�����������Ի�ȡ���ֹ���
//			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v3 = inflater2.inflate(R.layout.b3_person_item, null);
			
			return v;
		}
		
	}
}
