package com.daocheng.girlshop.activity.user;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daocheng.girlshop.R;
import com.daocheng.girlshop.activity.BaseActivity;
import com.daocheng.girlshop.entity.ServiceResult;
import com.daocheng.girlshop.entity.User;
import com.daocheng.girlshop.net.Api;
import com.daocheng.girlshop.net.NetUtils;
import com.daocheng.girlshop.utils.Config;
import com.daocheng.girlshop.utils.Constant;
import com.daocheng.girlshop.utils.Utils;
import com.google.gson.Gson;


import java.io.IOException;

/**
 * 登录类
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText telEditText, codeEditText;
    private TextView codeBtn, loginBtn;
    private ImageView tv_left;
    private TextView tv_center;
    private RadioGroup rg_login;

    private String role= Constant.USER;//user chef

    private TimeCount time;
    private RadioButton rb_user;
    private RadioButton rb_chef;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupViews() {
        telEditText = (EditText) findViewById(R.id.telEditText);
        telEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        codeEditText = (EditText) findViewById(R.id.codeEditText);
        codeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        codeBtn = (TextView) findViewById(R.id.codeBtn);
        loginBtn = (TextView) findViewById(R.id.loginBtn);
        tv_left = (ImageView) findViewById(R.id.tv_left);
        tv_center = (TextView) findViewById(R.id.tv_center);
        rg_login = (RadioGroup) findViewById(R.id.rg_login);
        rb_user=(RadioButton) findViewById(R.id.rb_user);
        rb_chef=(RadioButton) findViewById(R.id.rb_chef);



        tv_left.setOnClickListener(this);
        codeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        codeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(telEditText.getText().length()>0&&codeEditText.getText().length()>0){
                    loginBtn.setBackgroundDrawable(self.getResources().getDrawable(R.drawable.shape_login));
                }else{
                    loginBtn.setBackgroundDrawable(self.getResources().getDrawable(R.drawable.shape_yanzhengma));
                }
            }
        });
        rg_login.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_user:
                        role = Constant.USER;
                        rb_user.setTextColor(getResources().getColor(R.color.white));
                        rb_chef.setTextColor(getResources().getColor(R.color.App_actvitytext_grey));
                        break;


                    case R.id.rb_chef:
                        role = Constant.CHEF;
                        rb_chef.setTextColor(getResources().getColor(R.color.white));
                        rb_user.setTextColor(getResources().getColor(R.color.App_actvitytext_grey));
                        break;
                }
            }
        });
    }

    @Override
    protected void initialized() {
        tv_center.setText("登录");
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.codeBtn:
                if (null != telEditText.getText().toString() && Utils.isMobileNO(telEditText.getText().toString())&&telEditText.getText().toString().length()==11) {
                    getCode(telEditText.getText().toString());

                } else {
                    showShortToast("请输入正确的号码");

                }

                break;
            case R.id.loginBtn:
                if (null != codeEditText.getText().toString() && null != telEditText.getText().toString() && Utils.isMobileNO(telEditText.getText().toString())&&telEditText.getText().toString().length()==11) {
                    userLogin(telEditText.getText().toString(), codeEditText.getText().toString());
                } else {
                    showShortToast("请输入正确的号码");
                }

                break;
            case R.id.tv_left:
                defaultFinish();
                break;
        }
    }


    private void getCode(String telephone) {

        if (role == null) {
            showShortToast("先选择角色");
        }
        time.start();//开始计时
        Api.getCode(this, telephone, role, ServiceResult.class, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) throws IOException, ClassNotFoundException {
                switch (rspData.getRecode()) {
                    case 0:
                        showShortToast("已发送验证码");
                        break;
                    case -1:
                        showShortToast("网络错误");
                        break;
                    case -2:
                        showShortToast("验证失败，没有该商户");
                        break;
                    default:
                        showShortToast("服务器错误");
                        break;
                }


            }

            @Override
            public void failed(String msg) {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void userLogin(String telephone, String code) {
        Api.loginIn(this, telephone, code, role, User.class, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) throws IOException, ClassNotFoundException {
                User user = (User) rspData;
                switch (user.getRecode()) {
                    case 0:
                        showShortToast("登录成功");
                        Config.setUserInfo(new Gson().toJson(user));
//                        User userInfo =Config.getUserInfo();

                        defaultFinish();
                        break;
                    case -1:
                        showShortToast("验证码错误");
                        break;
                    case -2:
                        showShortToast("验证码错误");
                        break;
                }
            }

            @Override
            public void failed(String msg) {
                showShortToast(msg);
            }
        });
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            codeBtn.setText("重新验证");
            codeBtn.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            codeBtn.setClickable(false);
            codeBtn.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
    }


}
