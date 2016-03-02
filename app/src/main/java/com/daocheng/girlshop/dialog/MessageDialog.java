package com.daocheng.girlshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.daocheng.girlshop.R;


/**
 * 项目名称：CookeClient
 * 类描述：提示信息对话框
 * 创建人：Dove
 * 创建时间：2015/11/16 20:45
 * 修改人：Dove
 * 修改时间：2015/11/16 20:45
 * 修改备注：
 */
public class MessageDialog extends Dialog implements View.OnClickListener{

    private Context mcontext;
    private TextView tvtitle;
    private TextView tvcontent;
    private ImageView ivdismiss;
    private TextView tvcancel;
    private TextView tvok;
    private String title;
    private String content;

    private onRequest onrequest;

    public static int ORDER=0;
    public static int HOME=1;

    private int type;

    public MessageDialog(Context context, String title, String content, int Type, onRequest onrequest) {
        super(context);
        mcontext=context;
        this.title=title;
        this.content=content;
        this.onrequest=onrequest;
        this.type=Type;
    }

    @Override
    public void onClick(View v) {
      if (type==ORDER)
      {
          switch (v.getId())
          {
              case R.id.tv_cancel:
              case R.id.tv_ok:
              case R.id.iv_dismiss:this.dismiss();onrequest.back();break;
          }
      }else if (type==HOME)
      {
          switch (v.getId())
          {
              case R.id.iv_dismiss:
              case R.id.tv_cancel:this.dismiss();break;
              case R.id.tv_ok:this.dismiss();onrequest.back();break;

          }
      }
    }

    public interface onRequest {
        public void back();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_message);
        initialize();

        tvtitle.setText(title);
        tvcontent.setText(content);

        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        this.setCanceledOnTouchOutside(false);
    }

    private void initialize() {

        tvtitle = (TextView) findViewById(R.id.tv_title);
        tvcontent = (TextView) findViewById(R.id.tv_content);
        ivdismiss = (ImageView) findViewById(R.id.iv_dismiss);
        tvcancel = (TextView) findViewById(R.id.tv_cancel);
        tvok = (TextView) findViewById(R.id.tv_ok);
        tvok.setOnClickListener(this);
        tvcancel.setOnClickListener(this);
        ivdismiss.setOnClickListener(this);
    }
}
