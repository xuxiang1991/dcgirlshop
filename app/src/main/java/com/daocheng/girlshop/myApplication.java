package com.daocheng.girlshop;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.daocheng.girlshop.activity.MainActivity;
import com.daocheng.girlshop.entity.Location;
import com.daocheng.girlshop.utils.Constant;
import com.daocheng.girlshop.utils.CrashHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 项目名称：girlshop
 * 类描述：app属性，应用入口
 * 创建人：Dove
 * 创建时间：2016/2/29 15:55
 * 修改人：Dove
 * 修改时间：2016/2/29 15:55
 * 修改备注：
 */
public class myApplication extends Application {
    public File cacheFile = null;
    private static Context mContext;
    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    public Vibrator mVibrator;


    private List<Activity> mList = new LinkedList<Activity>();
    private static myApplication instance;

    private static myApplication self;
    private CrashHandler catchHandler;


    @Override
    public void onCreate() {
        super.onCreate();


        self = this;
        mContext = getApplicationContext();

        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());//用来获取全局的错误处理

        initLocation();//自动定位
        initCache();//imageloader 初始化
    }

    private void  initCache() {
        File cacheDir = StorageUtils.getCacheDirectory(self);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
//                .showImageOnLoading(R.drawable.e)
                .showImageOnFail(R.drawable.img_error_big)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .discCache(new com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache(cacheDir))
                .discCacheFileCount(10000).threadPoolSize(5).build();

        // default
        ImageLoader.getInstance().init(config);
    }

    public void initLocation() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }



    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            Location loc = new Location();
            loc.setTypeCode(location.getLocType());
            if (location.getLocType() == BDLocation.TypeGpsLocation||location.getLocType() == BDLocation.TypeNetWorkLocation||location.getLocType() == BDLocation.TypeOffLineLocation) {// GPS定位结果

                loc.setTime(location.getTime());
                loc.setAddress(location.getAddrStr());
                loc.setProvince(location.getProvince());
                loc.setCity(location.getCity());
                loc.setStreet(location.getStreet());
                loc.setDescribe(location.getLocationDescribe());
                loc.setDistrct(location.getDistrict());
                loc.setCityCode(location.getCityCode());
                loc.setResult(true);


            } else if (location.getLocType() == BDLocation.TypeServerError) {
                loc.setDescribe(getResources().getString(R.string.location_TypeServerError));
                loc.setResult(false);

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                loc.setDescribe(getResources().getString(R.string.location_TypeNetWorkException));
                loc.setResult(false);
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                loc.setDescribe(getResources().getString(R.string.location_TypeCriteriaException));
                loc.setResult(false);
            }

            setLocationMessage(loc);

            if(loc.getDistrct()==null)
            {
                loc.setDistrct("常熟市");
            }
            sendCity(loc.getDistrct());
            mLocationClient.stop();
        }


    }

    public static Location location;

    public static Location getLocationMessage() {
        return location;
    }

    public void setLocationMessage(Location str) {
        location = str;
    }


//    public static myApplication getInstance() {
//        return self;
//    }

    public static Context getContext() {
        return mContext;
    }

    private void sendCity(String city)
    {
        Intent intent = new Intent();
        intent.setAction(Constant.CITYBROADCAST);
        intent.putExtra("city",city);
        sendBroadcast(intent);
    }





    public synchronized static myApplication getInstance() {
        if (null == instance) {
            instance = new myApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }


    public void exitExceptforMain()
    {
        try {
            for (Activity activity : mList) {
                if (activity != null&&!(activity instanceof MainActivity))
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void restart()
    {
        exitExceptforMain();

    } 
}
