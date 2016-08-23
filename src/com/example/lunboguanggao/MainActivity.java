package com.example.lunboguanggao;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Context context;
	private ViewPager viewPager;
	private int[] intDrawables;
	private ArrayList<ImageView> imageViews;
	private ArrayList<View> pointViews;
	private LinearLayout ll_point_container;
	private android.widget.LinearLayout.LayoutParams params;
	private MyViewPagerAdapter myViewPagerAdapter;
	private String[] des;
	private int position;
	private TextView tv_description;
	private View pointView;
	private int previousSelectedPosition = 0;
	/**
	 * 界面可见  可以自动 论循环
	 */
	boolean inVisible = true;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			viewPager.setCurrentItem(1 + viewPager.getCurrentItem());
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = this;

		initUI();
		initData();
		initAdapter();
	}

	private void initAdapter() {
		myViewPagerAdapter = new MyViewPagerAdapter(context, imageViews);
		viewPager.setAdapter(myViewPagerAdapter);
		ll_point_container.getChildAt(0).setEnabled(true);
		tv_description.setText(des[0]);

		// 设置在 总数中间开始，以便左右循环
		int item = 5000000 / imageViews.size();
		viewPager.setCurrentItem(item);
		
		if (inVisible) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(2000);

						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						handler.sendEmptyMessage(0);

					}
				}
			}).start();
		}

	}

	private void initData() {
		intDrawables = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		des = new String[intDrawables.length];
		for (int i = 0; i < intDrawables.length; i++) {
			des[i] = String.valueOf(intDrawables[i]);
		}

		ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
		imageViews = new ArrayList<ImageView>();
		ImageView imageView;

		for (int i = 0; i < intDrawables.length; i++) {
			imageView = new ImageView(context);
			imageView.setBackgroundResource(intDrawables[i]);
			imageViews.add(imageView);

			pointView = new View(context);
			pointView.setBackgroundResource(R.drawable.selector_bg_point);

			if (i != 0) {
				params.rightMargin = 5;
			}
			pointView.setEnabled(false);

			// 设置参数
			params = new LinearLayout.LayoutParams(5, 5);
			ll_point_container.addView(pointView, params);

		}

		viewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 取余获得位置
				int newPosition = position % imageViews.size();
				tv_description.setText(des[newPosition]);
				for (int i = 0; i < ll_point_container.getChildCount(); i++) {

					// 设置文本
					tv_description.setText(des[newPosition]);

					// 设置小白点
					ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
					ll_point_container.getChildAt(newPosition).setEnabled(true);

					// 记录之前的位置
					previousSelectedPosition = newPosition;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	private void initUI() {

		viewPager = (ViewPager) findViewById(R.id.view_pager);
		tv_description = (TextView) findViewById(R.id.tv_description);

	}

	@Override
	protected void onDestroy() {

		inVisible = !inVisible;
		super.onDestroy();
	}
}
