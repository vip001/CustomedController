package com.personal.sample;

import android.app.Activity;
import android.os.Bundle;

import com.personal.customedcontroller.R;
import com.personal.view.PointShimmerView;

public class ShimmerActivity extends Activity {
	private PointShimmerView shimmerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_shimmer);
		shimmerView = (PointShimmerView) this.findViewById(R.id.shimmer);
		// shimmerView.showAllEffect();
	}
}
