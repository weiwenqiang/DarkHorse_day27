package com.example.day27.junit;

import com.example.day27.junit.utils.Utils;
import com.example.day27.sqlite.MyOpenHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestCase extends AndroidTestCase {
	// 此时测试框架还没有初始化完毕，还没有虚拟上下文对象，所以会报空指针
	// private MyOpenHelper oh = new MyOpenHelper(getContext(), "people.db",
	// null, 2);
	private MyOpenHelper oh;
	private SQLiteDatabase db;

	/*
	 * 测试框架初始化完毕之后，在测试方法执行之前，此方法会调用，所以在这里 (non-Javadoc)
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
	 * 测试方法之后，这个方法调用 (non-Javadoc)
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
		// 断言，检查实际值与期望值是否一致
		assertEquals(8, result);
	}

	public void test2() {
		Utils.division(2, 0);
	}

	public void test3() {
		// getContext()，获取一个虚拟的上下文
		// 如果数据库不存在，先创建，在获取一个可读可写数据库对象，如果数据库存在，就直接打开
		db = oh.getWritableDatabase();
		// 如果存储空间满了，那么返回只读数据库对象
		// SQLiteDatabase db = oh.getReadableDatabase();
	}

	public void insert() {
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "小志", "13000", 138438 });
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "小志的儿子", 16000, "138438" });
		db.execSQL("insert into person(name, salary, phone) values(?,?,?);",
				new Object[] { "小志的老婆", 198000, "138438" });
	}

	public void delete() {
		db.execSQL("delete from person where name = ?", new Object[] { "小志" });
	}

	public void update() {
		db.execSQL("update person set phone= ? where name= ?;", new Object[] {
				1984654, "小志的儿子" });
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
		// 要把插入的数据全部封装至ContentValues对象
		ContentValues values = new ContentValues();
		values.put("name", "游天龙");
		values.put("phone", "15999");
		values.put("salary", 16000);
		db.insert("person", null, values);
	}

	public void deleteAPI() {
		int i = db.delete("person", "name = ? and _id = ?", new String[] {
				"小志的儿子", "3" });
		Log.d("deleteAPI", String.valueOf(i));
	}

	public void updateAPI() {
		ContentValues values = new ContentValues();
		values.put("salary", 26000);
		int i = db.update("person", values, "name=?", new String[] { "游天龙" });
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

	// 事务
	public void transaction() {
		try {
			// 开启事务
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put("salary", 13000);
			db.update("person", values, "name=?", new String[] { "小志" });
			values.clear();
			// 如果不清空，第二次会携带第一次的数据，很危险
			values.put("salary", 15000);
			db.update("person", values, "name=?", new String[] { "小志的儿子" });
			// 设置事务成功执行
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭事务，同时提交，如果已经设置事务执行成功，那么sql语句生效，反之回滚
			db.endTransaction();
		}
	}
}
