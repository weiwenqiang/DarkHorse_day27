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
		//����ֱ��new����Ҫ������Builder������
		AlertDialog.Builder builder = new Builder(TestAlertDialog.this);
		builder.setIcon(android.R.drawable.btn_default_small);
		builder.setTitle("�����˹������Թ�");
		builder.setMessage("��־ƽ�������ŷ");
		//ȷ����ť
		builder.setPositiveButton("�Թ�", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "��лʹ�ñ�������ټ�", 0).show();
			}
		});
		//ȡ����ť
		builder.setNegativeButton("����", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "�����Թ����ز��ɹ�", 0).show();
			}
		});
		//ʹ�ô�����������һ���Ի������
		AlertDialog ad = builder.create();
		ad.show();
	}
	public void click2(View v){
		AlertDialog.Builder builder2 = new Builder(TestAlertDialog.this);
		builder2.setIcon(android.R.drawable.btn_default_small);
		builder2.setTitle("��ѡ���Ա�");
		final String[]  items = new String[]{"��","Ů"};
		
		
		builder2.setSingleChoiceItems(items, -1, new OnClickListener() {
			//which:�û���ѡ����Ŀ���±�
			//dialog:������������ĶԻ���
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestAlertDialog.this, "��ѡ����ǣ�"+items[which], 0).show();
				//�رնԻ���
				dialog.dismiss();
			}
		});
		builder2.show();
		
	}
	public void click3(View v){
		AlertDialog.Builder builder3 = new Builder(TestAlertDialog.this);
		builder3.setIcon(android.R.drawable.btn_default_small);
		builder3.setTitle("��ѡ����֣�");
		final String[]  items = new String[]{"�ܽ���","������","�Ż���","���»�","������"};
		final boolean[] checkedItems = new boolean[]{
			true,false,true,false,false	
		};
		builder3.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			//isChecked:�û��Ƿ�ѡ����Ŀ
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				checkedItems[which] = isChecked;
			}
		});
		builder3.setPositiveButton("ȷ��", new OnClickListener() {
			
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
