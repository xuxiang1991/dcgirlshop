package com.daocheng.girlshop.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daocheng.girlshop.R;
import com.daocheng.girlshop.activity.user.LoginActivity;
import com.daocheng.girlshop.entity.User;
import com.daocheng.girlshop.utils.Config;
import com.daocheng.girlshop.utils.Constant;
import com.daocheng.girlshop.view.RoundImageView;


/**
 * Created by Administrator on 2015/10/15.
 */
public class MyOwnFragment extends BaseFragment implements View.OnClickListener {
    private RoundImageView iv_center1;
    private RoundImageView iv_center2;
    private RoundImageView iv_center3;
    private ImageView myOwnRImg;
    private ViewPager mPager;
    private Fragment myownFragment;
    private TextView tvCenter;
    private TextView tv_text;
    private RelativeLayout moreLayout;
    private RelativeLayout myCommentLayout;
    private RelativeLayout mactivityCenterLayout;
    private TextView tv_center1, tv_center2, tv_center3;
    private LinearLayout ll_center1, ll_center2, ll_center3;
    private ImageView tv_left;
    private LinearLayout rl_user;

    private RelativeLayout rl_share;
    private LinearLayout ll_bottom0;

    private boolean isPrepared = false;

    private User user;
    private LinearLayout ll_bottom1;
    private TextView tv_bottom1, tv_bottom2, tv_bottom3, tv_bottom4, tv_bottom5, tv_bottom6;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myown;
    }

    @Override
    protected void initialized() {

        tvCenter.setText("我的");
        tv_left.setVisibility(View.INVISIBLE);
        isPrepared = true;
    }

    @Override
    protected void setupViews(View view) {

        ll_bottom0 = (LinearLayout)view.findViewById(R.id.ll_bottom0);
        rl_user = (LinearLayout)view.findViewById(R.id.rl_user);
        iv_center1 = (RoundImageView) view.findViewById(R.id.iv_center1);
        iv_center2 = (RoundImageView) view.findViewById(R.id.iv_center2);
        iv_center3 = (RoundImageView) view.findViewById(R.id.iv_center3);
        myOwnRImg = (ImageView) view.findViewById(R.id.myOwnRImg);
        mPager = (ViewPager) view.findViewById(R.id.content);
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        tv_text = (TextView) view.findViewById(R.id.tv_text);
        moreLayout = (RelativeLayout) view.findViewById(R.id.moreLayout);
        myCommentLayout = (RelativeLayout) view.findViewById(R.id.myCommentLayoout);
        mactivityCenterLayout = (RelativeLayout) view.findViewById(R.id.activityCenterLayout);
        tv_center1 = (TextView) view.findViewById(R.id.tv_center1);
        tv_center2 = (TextView) view.findViewById(R.id.tv_center2);
        tv_center3 = (TextView) view.findViewById(R.id.tv_center3);
        ll_bottom1 = (LinearLayout) view.findViewById(R.id.ll_bottom1);
        tv_bottom3 = (TextView) view.findViewById(R.id.tv_bottom3);
        tv_bottom1 = (TextView) view.findViewById(R.id.tv_bottom1);
        tv_bottom2 = (TextView) view.findViewById(R.id.tv_bottom2);
        tv_bottom4 = (TextView) view.findViewById(R.id.tv_bottom4);
        tv_bottom5 = (TextView) view.findViewById(R.id.tv_bottom5);
        tv_bottom6 = (TextView) view.findViewById(R.id.tv_bottom6);
        ll_center1 = (LinearLayout) view.findViewById(R.id.ll_center1);
        ll_center2 = (LinearLayout) view.findViewById(R.id.ll_center2);
        ll_center3 = (LinearLayout) view.findViewById(R.id.ll_center3);
        tv_left = (ImageView) view.findViewById(R.id.tv_left);
        rl_share = (RelativeLayout) view.findViewById(R.id.rl_share);

        rl_share.setOnClickListener(this);
        ll_center1.setOnClickListener(this);
        rl_user.setOnClickListener(this);
        moreLayout.setOnClickListener(this);
        myCommentLayout.setOnClickListener(this);
        mactivityCenterLayout.setOnClickListener(this);
        myOwnRImg.setOnClickListener(this);
        ll_center2.setOnClickListener(this);
        ll_center3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user:
                if (Config.getUserInfo()==null)
                {
                    Intent login = new Intent(self, LoginActivity.class);
                    startUsefulactivity(login);
                }
                break;
            case R.id.moreLayout:
//                Intent moreLayoutIntent = new Intent(self, MoreActivity.class);
//                startUsefulactivity(moreLayoutIntent);
                break;
            case R.id.myCommentLayoout:
//                Intent myCommentLayoutIntent = new Intent(self, MyCommentActivity.class);
//                startUsefulactivity(myCommentLayoutIntent);
                break;
            case R.id.activityCenterLayout:
//                Intent oinfo=new Intent(self, MessageActivity.class);
//                oinfo.putExtra("type", Constant.JOINUS);
//                oinfo.putExtra("name",Constant.JOINUS_NAME);
//                startActivity(oinfo);
                break;
            case R.id.ll_center1:
//                Intent Chefintent = new Intent(self, ChefOrderActivity.class);
//                startUsefulactivity(Chefintent);
                break;
            case R.id.myOwnRImg:
                User user=Config.getUserInfo();
                if (user!=null)
                    return;
                Intent loginIv = new Intent(self, LoginActivity.class);
                startActivity(loginIv);
                break;
            case R.id.rl_share:
//                CustomShareBoard shareBoard = new CustomShareBoard(getActivity());
//                shareBoard.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ll_center2:
//                user = Config.getUserInfo();
//                if(user!=null&&user.getRole() != null )
//                {
//                  if ( user.getRole().equals(Constant.CHEF)){
//                    Intent calendarIntent  = new Intent(self, CalendarActivity.class);
//                    startActivity(calendarIntent);
//                }else {
//                    Intent cookerIntent = new Intent(self, CollcetCookerActivity.class);
//                    startUsefulactivity(cookerIntent);
//                }
//                }else
//                {
//                    Intent login = new Intent(self, LoginActivity.class);
//                    startUsefulactivity(login);
//                }
                break;
            case R.id.ll_center3:
//                user = Config.getUserInfo();
//                if (user==null)
//                {
//                    Intent login = new Intent(self, LoginActivity.class);
//                    startUsefulactivity(login);
//                    return;
//                }
//
//                if(user.getRole() != null && user.getRole().equals(Constant.CHEF)){
//
//                }else{
//                    Intent intent = new Intent(self, CouponActivity.class);
//                    intent.putExtra("type", CouponActivity.TYPE_NORMAL);
//                    startUsefulactivity(intent);
//                }
                break;
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        loadUserInfo();
    }

    private void loadUserInfo() {
        user = Config.getUserInfo();
        if (user != null)
            tv_text.setText(user.getName());
        else {
            tv_text.setText("登录");
            return;
        }

        if (user.getRole() != null && user.getRole().equals(Constant.CHEF)) {
            tv_center1.setText("我的订单");
            tv_center2.setText("我的时间表");
            tv_center3.setText("我的账户");

            ll_bottom1.setVisibility(View.GONE);

            tv_bottom4.setText("评分");
            tv_bottom5.setText("分享给好友");
            tv_bottom6.setText("更多");
        }else{
            tv_center1.setText("我的订单");
            tv_center2.setText("我的厨师");
            tv_center3.setText("我的礼包");
            ll_bottom0.setVisibility(View.GONE);

            tv_bottom4.setText("评分");
            tv_bottom5.setText("分享给好友");
            tv_bottom6.setText("更多");
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        loadUserInfo();
    }


    private void startUsefulactivity( Intent it) {
        User user=Config.getUserInfo();
        if (user!=null)
        {
            startActivity(it);
        }else
        {
            Intent intent = new Intent(self, LoginActivity.class);
            startActivity(intent);
        }
    }
}
