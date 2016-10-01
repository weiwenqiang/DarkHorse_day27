package com.example.day27.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.day27.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TestSimpleAdapter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_listview);
		ListView lv = (ListView) findViewById(R.id.b2_listview);
		//集合中每个元素都包含ListView条目需要的所有数据，所以使用一个map来封装多种数据
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		//加值
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("phone", R.drawable.fruit_01);
		map1.put("name", "小志");
		data.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("phone", R.drawable.fruit_02);
		map2.put("name", "阿斯蒂芬");
		data.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("phone", R.drawable.fruit_03);
		map3.put("name", "光度法");
		data.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("phone", R.drawable.fruit_04);
		map4.put("name", "划分为");
		data.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("phone", R.drawable.fruit_05);
		map5.put("name", "劲头儿");
		data.add(map5);
		lv.setAdapter(new SimpleAdapter(TestSimpleAdapter.this, data, R.layout.b4_array_item, 
				new String[]{"phone","name"}, new int[]{R.id.array_image, R.id.array_text}));
	}

}
