package com.example.day27.junit;

import com.example.day27.junit.utils.Utils;
import com.example.day27.sqlite.MyOpenHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestCase extends AndroidTestCase {
	// ��ʱ���Կ�ܻ�û�г�ʼ����ϣ���û�����������Ķ������Իᱨ��ָ��
	// private MyOpenHelper oh = new MyOpenHelper(getContext(), "people.db",
	// null, 2);
	private MyOpenHelper oh;
	private SQLiteDatabase db;

	/*
	 * ���Կ�ܳ�ʼ�����֮���ڲ��Է���ִ��֮ǰ���˷�������ã����������� (non-Javadoc)
	 * 
	 * @see android.test.AndroidTestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		oh = new MyOpenHelper(getContext(), "people.db", null, 2);
		db = oh.getWritableDatabase();
	}

	/*
	 * ���Է���֮������������� (non-Javadoc)
	 * 
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		db.close();
	}

	public void test1() {
		int result = Utils.add(3, 5);
		// ���ԣ����ʵ��ֵ������ֵ�Ƿ�һ��
		assertEquals(8, result);
	}

	public void test2() {
		Utils.division(2, 0);
	}

	public void test3() {
		// getContext()����ȡһ�������������
		// ������ݿⲻ���ڣ��ȴ������ڻ�ȡһ���ɶ���д���ݿ����������ݿ���ڣ���ֱ�Ӵ�
		db = oh.getWritableDatabase();
		// ����洢�ռ����ˣ���ô����ֻ�����ݿ����
		// SQLiteDatabase db = oh.getReadableDatabase();
	}

	public void insert() {
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "С־", "13000", 138438 });
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "С־�Ķ���", 16000, "138438" });
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "С־������", 198000, "138438" });
	}

	public void delete() {
		db.execSQL("delete from person where name = ?", new Object[] { "С־" });
	}

	public void update() {
		db.execSQL("update person set phone= ? where name= ?;", new Object[] {
				1984654, "С־�Ķ���" });
	}

	public void select() {
		Cursor cursor = db.rawQuery("select * from person;", null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String salary = cursor.getString(2);
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			Log.i("TAG", "name:" + name + " #salary" + salary + " #phone:"
					+ phone);
		}
	}

	/**
	 * API
	 */
	public void insertAPI() {
		// Ҫ�Ѳ��������ȫ����װ��ContentValues����
		ContentValues values = new ContentValues();
		values.put("name", "������");
		values.put("phone", "15999");
		values.put("salary", 16000);
		db.insert("person", null, values);
	}

	public void deleteAPI() {
		int i = db.delete("person", "name = ? and _id = ?", new String[] {
				"С־�Ķ���", "3" });
		Log.d("deleteAPI", String.valueOf(i));
	}

	public void updateAPI() {
		ContentValues values = new ContentValues();
		values.put("salary", 26000);
		int i = db.update("person", values, "name=?", new String[] { "������" });
		Log.d("updateAPI", String.valueOf(i));
	}

	public void selectAPI() {
		Cursor cursor = db.query("person", null, null, null, null, null, null,
				null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String salary = cursor.getString(cursor.getColumnIndex("salary"));
			Log.d("selectAPI", name + ";" + phone + ";" + salary);
		}
	}

	// ����
	public void transaction() {
		try {
			// ��������
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put("salary", 13000);
			db.update("person", values, "name=?", new String[] { "С־" });
			values.clear();
			// �������գ��ڶ��λ�Я����һ�ε����ݣ���Σ��
			values.put("salary", 15000);
			db.update("person", values, "name=?", new String[] { "С־�Ķ���" });
			// ��������ɹ�ִ��
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر�����ͬʱ�ύ������Ѿ���������ִ�гɹ�����ôsql�����Ч����֮�ع�
			db.endTransaction();
		}
	}
}
