package com.example.day27.adapter;

import com.example.day27.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestArrayAdapter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_listview);
		
		String[] objects = new String[]{"小志","小志的儿子","萌萌","阿斯蒂芬","二万个","男歌手"};
		
		ListView lv = (ListView) findViewById(R.id.b2_listview);
		lv.setAdapter(new ArrayAdapter<String>(TestArrayAdapter.this, R.layout.b4_array_item, R.id.array_text, objects));
	}

}
