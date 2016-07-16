package com.example.lovehometown.adapter;


import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * viewpager适配器    继承FragmentPagerAdapter 可以存放相对动态的页面
 * @author Administrator
 *
 */

public class Viewadapter extends FragmentPagerAdapter{
   List<Fragment> fragmentlist;
	public Viewadapter(FragmentManager fm,List<Fragment> fragmentlist) {
		super(fm);
		this.fragmentlist=fragmentlist;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragmentlist.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentlist.size();
	}
}
