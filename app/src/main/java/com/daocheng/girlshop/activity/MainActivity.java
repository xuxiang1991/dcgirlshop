package com.daocheng.girlshop.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;


import com.daocheng.girlshop.R;
import com.daocheng.girlshop.dialog.MessageDialog;
import com.daocheng.girlshop.entity.Logo;
import com.daocheng.girlshop.entity.ServiceResult;
import com.daocheng.girlshop.fragment.Frag;
import com.daocheng.girlshop.fragment.HomeFragment;
import com.daocheng.girlshop.myApplication;
import com.daocheng.girlshop.net.Api;
import com.daocheng.girlshop.net.DownloadManager;
import com.daocheng.girlshop.net.NetUtils;
import com.duowan.mobile.netroid.Listener;


/**
 * Created by Administrator on 2015/9/10.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Fragment homeFragment;



    private ViewPager mPager;
    private RadioGroup mGroup;

    private BroadcastReceiver indexofcast;


    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void setupViews() {
        mPager = (ViewPager) findViewById(R.id.content);
        mGroup = (RadioGroup) findViewById(R.id.group);
        mGroup.setOnCheckedChangeListener(new CheckedChangeListener());
        mGroup.check(R.id.one);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPager.setOnPageChangeListener(new PageChangeListener());
        mPager.setOffscreenPageLimit(4);
    }

    @Override
    protected void initialized() {

//        loadLogo();

        initIndexBroadcast();

     

    }

    //logo图片下载
    private void loadLogo() {
        Api.getLogo(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                final Logo logo = (Logo) rspData;
                if (!com.daocheng.girlshop.utils.Config.getCurrentLOGO().equals(logo.getts())) {
                    DownloadManager.getInstance().download(DownloadManager.LOGO, logo.getUrl(), new Listener<Void>() {
                        @Override
                        public void onSuccess(Void response) {
                            com.daocheng.girlshop.utils.Config.setCurrentLOGO(logo.getts());
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                        }

                        @Override
                        public void onProgressChange(long fileSize, long downloadedSize) {

                        }
                    });
                }
            }

            @Override
            public void failed(String msg) {
//                Toast.makeText(self, msg, Toast.LENGTH_SHORT).show();
            }
        }, TvContract.Channels.Logo.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.group:


        }
    }


    @Override
    protected void onStop() {

        super.onStop();
    }

    private class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.one:
                    mPager.setCurrentItem(0);

                    break;
                case R.id.two:
                    mPager.setCurrentItem(1);
                    break;
                case R.id.three:
                    mPager.setCurrentItem(2);
                    break;
                case R.id.four:
                    mPager.setCurrentItem(3);
                    break;
            }
        }
    }






    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mGroup.check(R.id.one);
                    break;
                case 1:
                    mGroup.check(R.id.two);
                    break;
                case 2:
                    mGroup.check(R.id.three);
                    break;
                case 3:
                    mGroup.check(R.id.four);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if(homeFragment==null)
                    homeFragment=new HomeFragment();
                return homeFragment;
            } else {
                Frag frag = new Frag();
                Bundle bundle = new Bundle();
                bundle.putString("key", "hello world " + position);
                frag.setArguments(bundle);
                return frag;
            }


        }

        @Override
        public int getCount() {
            return 4;
        }
    }


    //注册接收订单广播
    private void initIndexBroadcast() {
        indexofcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent != null) {
                    int index=intent.getIntExtra("index",0);
                    mPager.setCurrentItem(index);
//                    ActivityManager.getScreenManager().popTopActivity();
                    myApplication.getInstance().exitExceptforMain();
                }

            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(com.daocheng.girlshop.utils.Constant.HOMEINDEX);
        self.registerReceiver(indexofcast, filter);
    }



    @Override
    public void onBackPressed() {
        MessageDialog dialog = new MessageDialog(self,"提示","是否要退出神厨家宴",MessageDialog.HOME, new MessageDialog.onRequest() {
            @Override
            public void back() {

               System.exit(0);
            }
        });

        dialog.show();
    }
}

