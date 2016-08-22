package com.example.lunboguanggao;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
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
		ll_point_container.getChildAt(0).setEnabled(true);
		myViewPagerAdapter = new MyViewPagerAdapter(context, imageViews);
		viewPager.setAdapter(myViewPagerAdapter);

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
				params.leftMargin = 10;
			}
			pointView.setEnabled(false);
			
			//设置参数
			params = new LinearLayout.LayoutParams(5, 5);
			ll_point_container.addView(pointView, params);
			
		}
		
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO 自动生成的方法存根
				tv_description.setText(des[arg0]);
				for(int i = 0; i < ll_point_container.getChildCount(); i++){
//					View childAt=ll_point_container.getChildAt(arg0);
//					childAt.setEnabled(arg0==i);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
	

	}

	private void initUI() {
		// TODO 自动生成的方法存根
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		tv_description = (TextView) findViewById(R.id.tv_description);

	}
}
