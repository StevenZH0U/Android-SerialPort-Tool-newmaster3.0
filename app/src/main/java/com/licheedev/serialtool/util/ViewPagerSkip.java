package com.licheedev.serialtool.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

public class ViewPagerSkip extends ViewPager {
    private ViewPagerScroller scroller;

    public ViewPagerSkip(Context context) {
        this(context, null);
    }

    public ViewPagerSkip(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (scroller == null)
            scroller = new ViewPagerScroller(context);
        scroller.initViewPagerScroll(this);
    }


    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, true);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (Math.abs(getCurrentItem() - item) > 1) {
            int duration = scroller.getmDuration();
            scroller.setScrollDuration(0);
            super.setCurrentItem(item, smoothScroll);
            scroller.setScrollDuration(duration);
        } else {
            super.setCurrentItem(item, smoothScroll);
        }
    }

    /**
     * 设置翻页的时间
     */
    public void setScrollDuration(int dur) {
        scroller.setScrollDuration(dur);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        try {
            return super.getChildDrawingOrder(childCount, i);
        } catch (Exception e) {
//            Toast.makeText(getContext(), "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return i;
        }
    }
}