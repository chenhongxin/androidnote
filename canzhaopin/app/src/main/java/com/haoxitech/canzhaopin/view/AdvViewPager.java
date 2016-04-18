package com.haoxitech.canzhaopin.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


import com.haoxitech.canzhaopin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Guojie on 2016/2/16.
 * 算价格页面的viewpager
 */
public class AdvViewPager extends ChildViewPager {
    private boolean isContinue = true;
    private AtomicInteger what = new AtomicInteger(0);
    public List<ImageView> listPoint;

    public AdvViewPager(Context context) {
        super(context);
        setOnPageChangeListener(new ViewPagerChange());
    }

    public AdvViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener(new ViewPagerChange());
        setOnTouchListener(new ViewPagerTouch());
    }

    class ViewPagerTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isContinue = false;
                case MotionEvent.ACTION_MOVE:
                    isContinue = false;
                    break;
                case MotionEvent.ACTION_UP:
                    isContinue = true;
                    break;
                default:
                    isContinue = true;
                    break;
            }
            return false;
        }
    }

    // 定时滑动线程
    public Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (isContinue) {
                    viewHandler.sendEmptyMessage(what.get());
                    whatOption();
                }
            }
        }
    });

    /**
     * Viewpager
     */
    class ViewPagerChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            what.getAndSet(position);
            for (int i = 0; i < listPoint.size(); i++) {
                if (position == i) {
                    listPoint.get(i).setBackgroundResource(R.drawable.ic_point_checked);
                } else {
                    listPoint.get(i).setBackgroundResource(R.drawable.ic_point_normal);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    /**
     * 操作圆点轮换变背景
     */

    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > listPoint.size() - 1) {
            what.getAndAdd(-(listPoint.size()));
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理定时切换广告栏图片的Handler
     */
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };
}
