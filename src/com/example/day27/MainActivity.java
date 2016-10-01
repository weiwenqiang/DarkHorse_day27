package com.example.day27;

import com.example.day27.adapter.TestArrayAdapter;
import com.example.day27.adapter.TestSimpleAdapter;
import com.example.day27.dialog.TestAlertDialog;
import com.example.day27.listview.TestListView;
import com.example.day27.listview.TestPersonListView;
import com.example.day27.show.TestShowPerson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.day27_b1).setOnClickListener(this);
		findViewById(R.id.day27_b2).setOnClickListener(this);
		findViewById(R.id.day27_b3).setOnClickListener(this);
		findViewById(R.id.day27_b4).setOnClickListener(this);
		findViewById(R.id.day27_b5).setOnClickListener(this);
		findViewById(R.id.day27_b6).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.day27_b1:
			startActivity(new Intent(MainActivity.this, TestShowPerson.class));
			break;
		case R.id.day27_b2:
			startActivity(new Intent(MainActivity.this, TestPersonListView.class));
			break;
		case R.id.day27_b3:
			startActivity(new Intent(MainActivity.this, TestListView.class));
			break;
		case R.id.day27_b4:
			startActivity(new Intent(MainActivity.this, TestArrayAdapter.class));
			break;
		case R.id.day27_b5:
			startActivity(new Intent(MainActivity.this, TestSimpleAdapter.class));
			break;
		case R.id.day27_b6:
			startActivity(new Intent(MainActivity.this, TestAlertDialog.class));
			break;
		default:
			break;
		}
	}
}
