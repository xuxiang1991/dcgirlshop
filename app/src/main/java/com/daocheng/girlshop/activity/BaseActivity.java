package com.daocheng.girlshop.activity;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.daocheng.girlshop.R;
import com.daocheng.girlshop.myApplication;
import com.daocheng.girlshop.Interface.RequestWebListener;

import com.daocheng.girlshop.utils.Config;


/**
 * Created by Administrator on 2015/9/10.
 */
public abstract class BaseActivity extends FragmentActivity {


    /**
     * LOG打印标签
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected com.daocheng.girlshop.utils.ActivityManager activityManager;
    protected BaseActivity self;
    protected int width, height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;
        activityManager = com.daocheng.girlshop.utils.ActivityManager.getScreenManager();
        activityManager.pushActivity(this);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            // 删除窗口背景
            getWindow().setBackgroundDrawable(null);
        }
        preliminary();

        // 设置分享的内容

        Config.setScreenSize(this);//获取屏幕宽高
        myApplication.getInstance().addActivity(self);//新启动activity就会加入栈
    }


    @Override
    protected void onResume() {
        super.onResume();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;


    }


    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onDestroy() {

        activityManager.popActivity(this);
        super.onDestroy();
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    /**
     * 向用户展示信息前的准备工作在这个方法里处理
     */
    protected void preliminary() {
        // 初始化组件
        setupViews();

        // 初始化数据
        initialized();
    }


    /**
     * 布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 初始化组件
     */
    protected abstract void setupViews();

    /**
     * 初始化数据
     */
    protected abstract void initialized();

    /**
     * Debug输出Log信息
     *
     * @param msg
     */
    protected void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    /**
     * Error输出Log信息
     *
     * @param msg
     */
    protected void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * Info输出Log信息
     *
     * @param msg
     */
    protected void showLog(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 长时间显示Toast提示(来自String)
     *
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(self, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(self, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(self, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 通过Class跳转界面
     **/
    public void StartActivity(Class<?> cls) {
        StartActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void StartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    /**
     * 通过Action跳转界面
     **/
    public void StartActivity(String action) {
        StartActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void StartActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void StartActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }


    /**
     * 默认退出
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    /**
     * 带有右进右出动画的退出
     */
    public void defaultFinish() {
        finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 加载动画
     */
    public RequestWebListener l = new RequestWebListener() {

        @Override
        public void requestWeb() {
            initialized();
        }
    };
}
