package com.example.day27.dialog;

import com.example.day27.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class TestAlertDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b6_alertdialog);
	}

	public void click1(View v){
		//不能直接new所以要用它的Builder创建器
		AlertDialog.Builder builder = new Builder(TestAlertDialog.this);
		builder.setIcon(android.R.drawable.btn_default_small);
		builder.setTitle("欲练此功必先自宫");
		builder.setMessage("李志平，想清楚欧");
		//确定按钮
		builder.setPositiveButton("自宫", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "感谢使用本软件，再见", 0).show();
			}
		});
		//取消按钮
		builder.setNegativeButton("放弃", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "若不自宫，必不成功", 0).show();
			}
		});
		//使用创建器，生成一个对话框对象
		AlertDialog ad = builder.create();
		ad.show();
	}
	public void click2(View v){
		AlertDialog.Builder builder2 = new Builder(TestAlertDialog.this);
		builder2.setIcon(android.R.drawable.btn_default_small);
		builder2.setTitle("请选择性别：");
		final String[]  items = new String[]{"男","女"};
		
		
		builder2.setSingleChoiceItems(items, -1, new OnClickListener() {
			//which:用户所选的条目的下标
			//dialog:触发这个方法的对话框
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "您选择的是："+items[which], 0).show();
				//关闭对话框
				dialog.dismiss();
			}
		});
		builder2.show();
		
	}
	public void click3(View v){
		AlertDialog.Builder builder3 = new Builder(TestAlertDialog.this);
		builder3.setIcon(android.R.drawable.btn_default_small);
		builder3.setTitle("请选择歌手：");
		final String[]  items = new String[]{"周杰伦","孙燕姿","张惠妹","刘德华","郭富城"};
		final boolean[] checkedItems = new boolean[]{
			true,false,true,false,false	
		};
		builder3.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			//isChecked:用户是否选中条目
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				checkedItems[which] = isChecked;
			}
		});
		builder3.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String text=null;
				for(int i=0; i<items.length; i++){
					text += checkedItems[i] ? items[i]+",":"";
				}
				Toast.makeText(TestAlertDialog.this, text, 0).show();
				dialog.dismiss();
			}
		});
		builder3.show();
	}
}
