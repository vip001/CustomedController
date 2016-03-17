package com.personal.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
	private Bitmap underlyingBitmap;
	private Bitmap topBitmap;
	private boolean isFull;
	private Paint underlyingPaint;
	private Paint topPaint;
	private float radius;
	private float radiusOffset;
	private int cx;
	private int cy;
	private int bmLeft;
	private int bmTop;

	private int startAngle;
	private int offsetAngle;

	public CircleView(Context context) {
		super(context);
		initView();
	}

	public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}

	public void setTopColor(int color) {
		topPaint.setColor(color);
	}

	public void setUnderlyingColor(int color) {
		underlyingPaint.setColor(color);
	}

	private void initView() {
		isFull = false;
		underlyingPaint = new Paint();
		underlyingPaint.setTextAlign(Paint.Align.CENTER);
		underlyingPaint.setAntiAlias(true);
		underlyingPaint.setStyle(Paint.Style.STROKE);
		underlyingPaint.setColor(Color.GRAY);

		topPaint = new Paint();
		topPaint.setTextAlign(Paint.Align.CENTER);
		topPaint.setAntiAlias(true);
		topPaint.setTextSize(20.0f);
		topPaint.setStrokeWidth(2.0f);
		topPaint.setStyle(Paint.Style.STROKE);
		topPaint.setColor(Color.BLUE);

		startAngle = 270;
		radiusOffset = 5;
	}

	public void setBitmap(Bitmap underlyingBitmap, Bitmap topBitmap) {
		this.underlyingBitmap = underlyingBitmap;
		this.topBitmap = topBitmap;
		radius = (float) ((Math.sqrt(Math.pow(underlyingBitmap.getHeight(), 2)
				+ Math.pow(underlyingBitmap.getWidth(), 2)) / 2) + radiusOffset);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		cx = w / 2;
		cy = h / 2;
		bmLeft = cx - underlyingBitmap.getWidth() / 2;
		bmTop = cy - underlyingBitmap.getHeight() / 2;
	}

	public void syncProgress(int progress, int max) {
		changeProgress(progress, max);
		invalidate();
	}

	private void changeProgress(int progress, int max) {
		float percent = (float) progress / (float) max;
		offsetAngle = (int) (360 * percent);
		if (progress == max) {
			isFull = true;
		} else {
			isFull = false;
		}
	}

	public void postProgress(int progress, int max) {
		changeProgress(progress, max);
		postInvalidate();
	}

	public void setRadiusOffset(float radiusOffset) {
		if (underlyingBitmap != null) {
			this.radiusOffset = radiusOffset;
			radius = (float) ((Math.sqrt(Math.pow(underlyingBitmap.getHeight(),
					2) + Math.pow(underlyingBitmap.getWidth(), 2)) / 2) + radiusOffset);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (underlyingBitmap != null) {
			canvas.drawBitmap(underlyingBitmap, bmLeft, bmTop, underlyingPaint);
			canvas.drawCircle(cx, cy, radius, underlyingPaint);
			canvas.drawArc(new RectF(cx - radius, cy - radius, cx + radius, cy
					+ radius), startAngle, offsetAngle, false, topPaint);
		}

		if (isFull) {
			canvas.drawBitmap(topBitmap, bmLeft, bmTop, underlyingPaint);
		}

	}
}
