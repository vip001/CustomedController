package com.personal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class PointShimmerView extends View {
	private Paint paint;
	private Paint topPaint;
	private int pointCount;
	private int offset;
	private int cy;
	private int radius;
	private int w;
	private int currentEffectCount;
	private int effectCount;
	private long interval;
	private boolean isUpdate;
	private int disAlpha;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			refreshEffectCount();
		};
	};

	public PointShimmerView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public PointShimmerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PointShimmerView(Context context) {
		super(context);
		initView();
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setPointCount(int pointCount) {
		if (pointCount < 1)
			return;
		this.pointCount = pointCount;
		this.offset = w / (pointCount + 1);
	}

	public int getPointCount() {
		return pointCount;
	}

	public int getEffectCount() {
		return effectCount;
	}

	public void setEffectCount(int effectCount) {
		this.effectCount = effectCount;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		this.w = w;
		cy = h / 2;
		offset = w / (pointCount + 1);
	}

	private void initView() {
		paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setAntiAlias(true);
		paint.setTextSize(80);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setStyle(Paint.Style.FILL);

		topPaint = new Paint();
		topPaint.setColor(Color.WHITE);
		topPaint.setAntiAlias(true);
		topPaint.setTextSize(80);
		topPaint.setTextAlign(Paint.Align.CENTER);
		topPaint.setStyle(Paint.Style.FILL);

		pointCount = 9;
		radius = 3;
		currentEffectCount = 0;
		effectCount = 5;
		interval = 50;
		isUpdate = true;
		disAlpha = 30;
	}

	public void setPrimaryColor(int color) {
		paint.setColor(color);
	}

	public void setTopColor(int color) {
		topPaint.setColor(color);
	}

	private void refreshEffectCount() {
		currentEffectCount++;
		currentEffectCount = currentEffectCount % (pointCount + effectCount);
		invalidate();
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public void showAllEffect() {
		currentEffectCount = pointCount;
		effectCount = pointCount;
		invalidate();
		isUpdate = false;
	}

	public void setDisAlpha(int disAlpha) {
		this.disAlpha = disAlpha;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		for (int i = 1; i <= pointCount; i++) {
			canvas.drawCircle(offset * i, cy, radius, paint);
		}
		topPaint.setAlpha(255);

		for (int i = currentEffectCount, count = 1; i > 0; i--) {
			if (currentEffectCount > 9) {
				continue;
			}
			if (count > effectCount) {
				break;
			}
			canvas.drawCircle(offset * i, cy, radius, topPaint);
			count++;
			int alpha = topPaint.getAlpha() - disAlpha;
			topPaint.setAlpha(alpha);
		}
		if (isUpdate) {
			handler.sendMessageDelayed(Message.obtain(), interval);
		}

	}
}
