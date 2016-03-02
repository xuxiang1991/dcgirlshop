package com.daocheng.girlshop.fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.daocheng.girlshop.R;
import com.daocheng.girlshop.adapter.AdvertImagePagerAdapter;
import com.daocheng.girlshop.dialog.UpdateDialog;
import com.daocheng.girlshop.entity.BannerInfo;
import com.daocheng.girlshop.entity.DistrectsInfo;
import com.daocheng.girlshop.entity.ServInfo;
import com.daocheng.girlshop.entity.ServiceResult;
import com.daocheng.girlshop.entity.Update;
import com.daocheng.girlshop.myApplication;
import com.daocheng.girlshop.net.Api;
import com.daocheng.girlshop.net.NetUtils;
import com.daocheng.girlshop.utils.Config;
import com.daocheng.girlshop.utils.Constant;
import com.daocheng.girlshop.utils.Utils;
import com.daocheng.girlshop.view.AutoScrollViewPager;
import com.daocheng.girlshop.view.CirclePageIndicatorB;
import com.daocheng.girlshop.view.LoadingView;
import com.daocheng.girlshop.view.PageIndicator;
import com.daocheng.girlshop.view.ScrollViewExtend;


import java.io.IOException;
import java.util.List;

/**
 * 项目名称：Cooke
 * 类描述：首页
 * 创建人：Dove
 * 创建时间：2015/9/28 10:15
 * 修改人：Dove
 * 修改时间：2015/9/28 10:15
 * 修改备注：
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private AutoScrollViewPager viewPager;
    private PageIndicator mIndicator;
    private ImageView tv_right;
    private TextView tv_left;
    private ImageView iv_location_more;
    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";

    private List<BannerInfo.banner> imageIdList;
    private BroadcastReceiver citybr;

    private AdvertImagePagerAdapter bannerAdapter;

    private TextView tv_large;
    private RelativeLayout rl_order_info;
    private RelativeLayout rl_ser_save;

    private LinearLayout ll_bigmeal, ll_smalmeal;
    private RelativeLayout ll_chadan;
    private boolean flag = true;
    private LoadingView layout_loading;
    private ScrollViewExtend scrollview;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void setupViews(View view) {
        viewPager = (AutoScrollViewPager) view.findViewById(R.id.view_pager_advert);
        viewPager.setInterval(3000);//切换速度
        viewPager.setAutoScrollDurationFactor(2.5);//动画速度
        mIndicator = (CirclePageIndicatorB) view.findViewById(R.id.indicator);
        tv_right = (ImageView) view.findViewById(R.id.tv_right);
        tv_left = (TextView) view.findViewById(R.id.tv_left);
        iv_location_more = (ImageView) view.findViewById(R.id.iv_location_more);
        iv_location_more.setVisibility(View.VISIBLE);
        tv_large = (TextView) view.findViewById(R.id.tv_large);
        rl_order_info = (RelativeLayout) view.findViewById(R.id.rl_order_info);
        rl_ser_save = (RelativeLayout) view.findViewById(R.id.rl_ser_save);
        layout_loading = (LoadingView) view.findViewById(R.id.layout_loading);
        scrollview = (ScrollViewExtend) view.findViewById(R.id.layout_scroll);


        ll_bigmeal = (LinearLayout) view.findViewById(R.id.ll_bigmeal);
        ll_bigmeal.setOnClickListener(this);
        ll_chadan = (RelativeLayout) view.findViewById(R.id.ll_chadan);
        ll_chadan.setOnClickListener(this);
        ll_smalmeal = (LinearLayout) view.findViewById(R.id.ll_smalmeal);
        ll_smalmeal.setOnClickListener(this);
        rl_ser_save.setOnClickListener(this);
        rl_order_info.setOnClickListener(this);
        tv_large.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        tv_left.setOnClickListener(this);
        tv_right.setVisibility(View.VISIBLE);
        tv_left.setVisibility(View.VISIBLE);


    }

    @Override
    protected void initialized() {


        initCityBroadcast();

        mLocationClient = ((myApplication) self.getApplication()).mLocationClient;
        initLocation();
        mLocationClient.start();



//        getUpdate();

    }

    @Override
    protected void lazyLoad() {

    }

    private void initBanner() {

        bannerAdapter = new AdvertImagePagerAdapter(self, imageIdList, flag);
        viewPager.setAdapter(bannerAdapter);
        mIndicator.setViewPager(viewPager);
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(0);


    }


//    private void setDatekit()
//    {
//        long old=Config.getUpdateTIMESTAMP();
//
//        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
//        if ((now.getTime()-old)>(3*24*60*60000))
//        {
//            getUpdate();
//        }
//        Config.setUpdateTIMESTAMP(now.getTime());
//        Log.v("a", now.getTime() + "");
//    }




    private void getUpdate() {
        Api.update(self, null, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                Update update = (Update) rspData;
                if (update != null && update.getRecode() == 0) {

//                     long old = Config.getUpdateTIMESTAMP();
//                        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
//                     if ((now.getTime() - old) > (2 * 24 * 60 * 60000))
                    showUpdateDialog(update);
                } else {


                }
            }

            @Override
            public void failed(String msg) {
//                Toast.makeText(self, msg, Toast.LENGTH_SHORT).show();
                layout_loading.postHandle(LoadingView.network_error);
                layout_loading.setL(l);
            }

        }, Update.class);
    }


    /**
     * 显示软件下载对话框
     */
    private void showUpdateDialog(Update update) {
        UpdateDialog dialog = new UpdateDialog(self, update);

        dialog.show();
    }

    //注册接收城市广播
    private void initCityBroadcast() {
        citybr = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent != null) {
                    String city = intent.getStringExtra("city");

                    tv_left.setText(city);

                    if (city != null && !city.equals(Config.getCityName())) {
                        Config.setCityName(city);
                    }
                    if (Utils.isNetworkConnected(self))
                        loadConstantInfo();
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.CITYBROADCAST);
        self.registerReceiver(citybr, filter);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);
        option.setCoorType(tempcoor);
        int span = 1000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(true);
        option.setEnableSimulateGps(false);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        mLocationClient.setLocOption(option);
    }


    //基础信息下载
    private void loadConstantInfo() {
        Api.getConstantsInfo(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                if (rspData.getRecode() == 0) {
                    final ServInfo servInfo = (ServInfo) rspData;
                    Config.initServInfo(servInfo);


                } else {
                    //                    showShortToast(R.string.info_getnewest);信息已最新
                }
                if (Utils.isNetworkConnected(self)) {
                    loadBanners();
                    loadDistrctsInfo();
                }

            }

            @Override
            public void failed(String msg) {
                layout_loading.postHandle(LoadingView.network_error);
                layout_loading.setL(l);

            }
        }, ServInfo.class);
    }

    //下载广告栏信息
    private void loadBanners() {
        Api.getBannerInfo(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                if (rspData.getRecode() == 0) {
                    final BannerInfo servInfo = (BannerInfo) rspData;
                    if (imageIdList == null) {
                        imageIdList = servInfo.getBannerList();

                        if (imageIdList.size() != 0) {
                            initBanner();


                        }
                    } else {
                        bannerAdapter.setData(servInfo.getBannerList());
                        bannerAdapter.notifyDataSetChanged();
                    }

                    layout_loading.postHandle(LoadingView.success);
                    scrollview.setVisibility(View.VISIBLE);
                } else {
                    layout_loading.postHandle(LoadingView.interfaceerror);
                    layout_loading.setL(l);
                }


            }

            @Override
            public void failed(String msg) {
                layout_loading.postHandle(LoadingView.network_error);
                layout_loading.setL(l);

            }
        }, BannerInfo.class);
    }


    //下载乡镇信息
    private void loadDistrctsInfo() {
        Api.getDistrictInfo(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) throws IOException, ClassNotFoundException {
                if (rspData.getRecode() == 0) {
                    final DistrectsInfo info = (DistrectsInfo) rspData;
                    if (info != null) {

                        Config.setDistrectTIMESTAMP(info.getTs());
                        Config.setTwoons((Object) info.getResult());

                    }

                } else {
                    //                    showShortToast(R.string.info_getnewest);信息已最新
                }


            }

            @Override
            public void failed(String msg) {
                showShortToast(msg);

            }
        }, DistrectsInfo.class);
    }


    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.stopAutoScroll();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewPager.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        self.unregisterReceiver(citybr);//注销广播
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:


                AlertDialog.Builder builder = new AlertDialog.Builder(self);
                builder.setMessage("是否要拨打客服热线？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + Config.getPHONENO());
                        intent.setData(data);
                        startActivity(intent);
                        self.overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.create().show();


                break;

            case R.id.rl_order_info:
//                Intent oinfo = new Intent(self, MessageActivity.class);
//                oinfo.putExtra("type", Constant.XIADAN);
//                oinfo.putExtra("name", Constant.XIADAN_NAME);
//                startActivity(oinfo);
                break;
            case R.id.rl_ser_save:
//                Intent sinfo = new Intent(self, MessageActivity.class);
//                sinfo.putExtra("type", Constant.BAOZHANG);
//                sinfo.putExtra("name", Constant.BAOZHANG_NAME);
//                startActivity(sinfo);
                break;
            case R.id.ll_bigmeal:
//                Intent intent = new Intent(self, QuickOrderActivity.class);
//                startActivity(intent);
                break;
            case R.id.ll_smalmeal:
//              new NinfoDialog(self).show();
//                Intent insmal = new Intent(self, MessageActivity.class);
//                insmal.putExtra("type", Constant.GIT);
//                insmal.putExtra("name", Constant.GIT_NAME);
//                startActivity(insmal);
//                Intent insmal = new Intent(self, BasicActivity.class);
//                startActivity(insmal);

                break;
            case R.id.ll_chadan:
//                Intent chadan = new Intent(self, ServerListActivity.class);
////                chadan.putExtra("type", Constant.Other);//Constant.Large   厨师
//                startActivity(chadan);

        }

    }
}
