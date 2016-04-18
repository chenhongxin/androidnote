package com.haoxitech.canzhaopin.app;

import android.app.Activity;
import android.content.Context;

import com.haoxitech.HaoConnect.HaoUtility;

import java.util.Stack;

/**
 * Created by wangtao on 15/11/17.
 */
public class AppManager {
    private static Stack<Activity> stack;
    private static AppManager instance;

    /**
     * 单一实例
     *
     * @return
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 加入一个Activity到栈中
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        synchronized (AppManager.class) {
            if (stack == null) {
                stack = new Stack<Activity>();
            }
            if (activity == null) return;
            stack.push(activity);
        }
    }

    /**
     * 移除指定的Activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        synchronized (AppManager.class) {
            if (stack == null) {
                stack = new Stack<Activity>();
            }

            if (stack != null && !stack.empty() && stack.contains(activity)) {
                stack.remove(activity);
                if (activity != null && !activity.isFinishing())
                    activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 结束当前的Activity, 该Activity为最后一个加入的
     */
    public void popActivity() {
        synchronized (AppManager.class) {
            if (stack == null || stack.isEmpty()) return;
            Activity activity = stack.lastElement();
            popActivity(activity);
        }
    }

    /**
     * 移除指定类名的Activity
     *
     * @param cls
     */
    public void popActivity(Class<?> cls) {
        synchronized (AppManager.class) {
            for (Activity activity : stack) {
                if (activity.getClass().equals(cls)) {
                    popActivity(activity);
                    break;
                }
            }
        }
    }

    /**
     * 获取指定的Activity
     *
     * @param cls
     * @return
     */
    public static Activity getActivity(Class<?> cls) {
        synchronized (AppManager.class) {
            for (Activity activity : stack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
            return null;
        }
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        synchronized (AppManager.class) {
            for (int i = 0; i < stack.size(); i++) {
                if (stack.get(i) != null) {
                    popActivity(stack.get(i));
                }
            }
            stack.clear();
        }
    }

    public void appExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            HaoUtility.print("exit==>" + e);
        }
    }

    public void appLogout() {
        synchronized (AppManager.class) {
            for (int i = stack.size() - 1 ; i > 0 ; i--) {
                if (stack.get(i) != null) {
                    popActivity(stack.get(i));
                }
            }
        }
    }
}
