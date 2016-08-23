package com.example.lunboguanggao;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyViewPagerAdapter extends PagerAdapter {

	private ArrayList<ImageView> imageViews;
	private Context context;
	private int newPosition;

	public MyViewPagerAdapter(Context context, ArrayList<ImageView> imageViews) {
		this.context = context;
		this.imageViews = imageViews;
	}

	@Override
	public int getCount() {
		// 夹无限循环
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// 当划到新的条目，又返回来，view是否可以被复用

		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView((View) object);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.
	 * ViewGroup, int) 返回要显示的内容
	 * 
	 * 初始化adapter
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// container 容器：ViewPager
		newPosition = position % imageViews.size();

		ImageView imageView = imageViews.get(newPosition);
		container.addView(imageView);
		return imageView;
	}

}
