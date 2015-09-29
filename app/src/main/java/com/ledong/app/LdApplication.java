/*****************************************************************************
 * LdApplication 类 , 这里对外提供是单例的模式
 * 这个类是继承自Application ,这里面有一些全局的东西都可以从这里设置或者获取
 *
 *****************************************************************************
 * Copyright (C) 2015-2016
 *
 * Authors: ChrisZhang <zhangyanlongcodec@gmail.com>
 *
 *
 */
package com.ledong.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LdApplication extends Application {

    /*  应用实例  */
    private static LdApplication instance;

    /* 当前应用位于最上层的activity */
    private Activity topActivity;

    //----------------------------线程相关 start----
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 3;

    /**
     * 最大线程数
     */

    private static final int MAXIMUM_POOL_SIZE = 6;

    /**
     * 线程生存时间，单位秒
     */
    private static final long KEEP_ALIVE_TIME = 10;

    /**
     * 线程池对象
     */
    private static ThreadPoolExecutor threadPoolExecutor;
    //----------------------------线程相关 end----


    /**
     * 获取当前正在运行的Application
     *
     * @return  获取application实例
     */
    public static Application getApplication() {
        return instance;
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     *         当前整个应用的上下文信息
     */
    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    /**
     * 获取当前位于顶层的Activity
     *
     * @return  当前位于顶层的Activity
     */
    public static Activity getTopActivity() {
        return instance.topActivity;
    }

    public void onCreate() {
        super.onCreate();

        // 线程池初始化
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());


        /*
        *   TODO
        *   registerActivityLifecycleCallbacks是Application的一个接口,
        *   注册之后应用里的所有activity的生命周期都会被监控起来，
        *   我们可以在此接口方法里实现一些特殊的需求。
        *   比如统计用户对每个Activity使用情况.
        *
        * */
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityStarted(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityResumed(Activity activity) {
                // TODO Auto-generated method stub
                LdApplication.this.topActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // TODO Auto-generated method stub

            }
        });
        instance = this;    // 在onCreate中初始化application
//        UiManager.init();
//        UiManager.showInfo();
    }

    /**
     * 退出
     */
    public void exit() {
    }


    //----------------------------线程相关函数 start----
    /**
     * @Description
     *              往任务队列里添加任务
     */
    public static void runInThreadPool(Runnable task) {

        threadPoolExecutor.execute(task);
    }

    /**
     * @Description
     *              关闭线程池
     */
    public static void shutdown() {

        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        threadPoolExecutor.shutdown();
    }
    //----------------------------线程相关函数 end----
}
