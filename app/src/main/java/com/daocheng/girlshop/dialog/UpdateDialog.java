package com.daocheng.girlshop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.daocheng.girlshop.R;
import com.daocheng.girlshop.entity.Update;
import com.daocheng.girlshop.net.DownloadManager;
import com.daocheng.girlshop.utils.Config;
import com.duowan.mobile.netroid.Listener;


import java.io.File;
import java.sql.Timestamp;

/**
 * 自定义弹出框
 * daiye
 * 2015/07/16
 */
public class UpdateDialog
        extends Dialog implements View.OnClickListener {

    private Context context;
    private Update update;
    private TextView content;

    public UpdateDialog(Context context, Update update) {
        super(context);
        this.context = context;
        this.update =update;
    }


    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View rootView = LayoutInflater.from(context).inflate(R.layout.setting_asr_dialog, null);
//        LinearLayout content =(LinearLayout) rootView.findViewById(R.id.contentView);
//        content.addView(view);

        setContentView(R.layout.setting_update_dialog);

        ((TextView)findViewById(R.id.alertTitle)).setText(update.getVER_NAME() + context.getResources().getString(R.string.about_update_text));
        content=  ((TextView)findViewById(R.id.content));
        content.setText(update.getDESCRIPTION());
        findViewById(R.id.button_cancel).setOnClickListener(this);
        findViewById(R.id.button_confrim).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_confrim:
                downloadApk(update.getDOWN_URLS());
                break;


            case R.id.button_cancel:
                Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
                Config.setUpdateTIMESTAMP(now.getTime());//记录取消的当前时间
                this.dismiss();
                break;
        }
    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void show() {
        if (context != null && ((Activity) context).isFinishing()) {
            return;
        }
        setCanceledOnTouchOutside(true);
        super.show();
    }

    private void downloadApk(String url)
    {

        DownloadManager.getInstance().download(DownloadManager.UPDATE, url, new Listener<Void>() {
            @Override
            public void onSuccess(Void response) {
                UpdateDialog.this.dismiss();
                Uri uri = Uri.fromFile(new File(DownloadManager.mSaveAPkFile));
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                context.startActivity(intent);

            }

            @Override
            public void onProgressChange(long fileSize, long downloadedSize) {
                super.onProgressChange(fileSize, downloadedSize);
//                progress.setProgress((int) (downloadedSize * 1.0f / fileSize * 100));
                content.setGravity(Gravity.CENTER);
                content.setText((int) (downloadedSize * 1.0f / fileSize * 100)+"%");
            }
        });
    }



}
