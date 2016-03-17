package com.personal.sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.personal.customedcontroller.R;
import com.personal.view.CircleView;

public class CircleActivity extends Activity {
	private CircleView circleView;
	private Button btn_click;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_circle);
		btn_click = (Button) this.findViewById(R.id.btn);
		circleView = (CircleView) this.findViewById(R.id.circleview);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		Bitmap topBm = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher_yellow);
		circleView.setBitmap(bm, topBm);

		btn_click.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				i++;
				i = i % 11;
				circleView.syncProgress(i * 10, 100);
			}
		});

	}
}
