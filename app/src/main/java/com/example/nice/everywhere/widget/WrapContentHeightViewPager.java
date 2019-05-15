package com.example.nice.everywhere.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WrapContentHeightViewPager extends ViewPager {
    private boolean noScroll = false;

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super( context, attrs );
        // TODO Auto-generated constructor stub
        addOnPageChangeListener( new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                requestLayout();//保证每次选中当前页时，计算高度，达到高度自适应效果
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        } );
    }

    public WrapContentHeightViewPager(Context context) {
        super( context );
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo( x, y );
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent( arg0 );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent( arg0 );
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem( item, smoothScroll );
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem( item );
    }


    //重写onMeasure，解决高度显示为0，同时高度动态显示为当前子项的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt( i );
            child.measure( widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec( 0, MeasureSpec.UNSPECIFIED ) );
            int h = child.getMeasuredHeight();
            if (i == getCurrentItem()) {
                height = h;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec( height,
                MeasureSpec.EXACTLY );
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
    }

}
