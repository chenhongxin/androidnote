package com.haoxitech.canzhaopin.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Guojie on 2016/2/22.
 */
public class AdvImageView extends ImageView {

    public AdvImageView(Context context) {
        super(context);
    }

    public AdvImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
            //此句代码是为了通知他的父ViewPager由父控件进行处理
            getParent().requestDisallowInterceptTouchEvent(false);
        }

        if (arg0.getAction() == MotionEvent.ACTION_UP) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(arg0);
    }
}
