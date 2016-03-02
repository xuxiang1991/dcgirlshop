package com.daocheng.girlshop.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

import com.daocheng.girlshop.entity.ServInfo;
import com.daocheng.girlshop.entity.User;
import com.daocheng.girlshop.myApplication;
import com.google.gson.Gson;


import java.io.IOException;

/**
 * 配置信息
 * Created by Dvoe on 2015/9/10.
 */
public class Config {

    //用户信息
    private static String USER_INFO = "USER_INFO";

    public static int width = 0;
    public static int height = 0;
    public static float density;
    public static int statusHeight = 0;

    public static SharedPreferences UserInfoPreferences = myApplication.getContext().getSharedPreferences(USER_INFO, 0);

    public static String getCityName() {
        return UserInfoPreferences.getString("cityname", "");
    }

    public static void setCityName(String cityname) {
        UserInfoPreferences.edit().putString("cityname", cityname).commit();
    }

    // 第一次启动
    private static String FIRST_START = "FIRST_START";

    public static boolean isFirst() {
        return UserInfoPreferences.getBoolean(FIRST_START, true);
    }

    public static void setFirst(boolean isFirst) {
        UserInfoPreferences.edit().putBoolean(FIRST_START, isFirst).commit();
    }


    /**
     * 得到屏幕长宽
     *
     * @param activity
     */
    public static void setScreenSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        density = displayMetrics.density;
        statusHeight = getStatusHeight(activity);
    }

    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    // 当前图片资源版本号
    private static String CURRENT_LOGO = "CURRENT_LOGO";

    public static String getCurrentLOGO() {
        return UserInfoPreferences.getString(CURRENT_LOGO, "0");
    }

    public static void setCurrentLOGO(String currentLogo) {
        UserInfoPreferences.edit().putString(CURRENT_LOGO, currentLogo).commit();
    }


    //基础信息 ID是根据地区而来
    public static String ID = "ID";
    public static String PHONENO = "PHONENO";
    public static String EMAIL = "EMAIL";
    public static String WARRANTY = "WARRANTY";
    public static String NOTES = "NOTES";
    public static String ABOUTUS = "ABOUTUS";
    public static String REFUND = "REFUND";
    public static String TIMESTAMP = "TIMESTAMP";
    public static String USERINFO = "USERINFO";
    public static String TOKEN="TOKEN";


    public static void initServInfo(ServInfo serinfo) {
        setID(serinfo.getID());
        setPHONENO(serinfo.getTelephone());
        setEMAIL(serinfo.getEmail());
        setWARRANTY(serinfo.getWarranty());
        setNOTES(serinfo.getNotes());
        setABOUTUS(serinfo.getAboutUs());
        setREFUND(serinfo.getRefund());
        setTIMESTAMP(serinfo.getTimeStamp());
    }


    public static void ExitUser() {

        setUserInfo(null);
        setToken(null);
    }


    public static void setUserInfo(String userInfo) {
        UserInfoPreferences.edit().putString(USERINFO, userInfo).commit();
    }

    public static User getUserInfo() {
        String userInfo = UserInfoPreferences.getString(USERINFO, "");
        if ("".equals(userInfo)) {
            return null;
        } else {
            return new Gson().fromJson(userInfo, User.class);
        }

    }

    public static String getToken() {
        return UserInfoPreferences.getString(TOKEN, null);
    }

    public static void setToken(String token) {
        UserInfoPreferences.edit().putString(TOKEN, token).commit();
    }


    public static String getID() {
        return UserInfoPreferences.getString(ID, "0");
    }

    public static void setID(String id) {
        UserInfoPreferences.edit().putString(ID, id).commit();
    }

    public static String getPHONENO() {
        return UserInfoPreferences.getString(PHONENO, "400-875-0026");
    }

    public static void setPHONENO(String phoneno) {
        UserInfoPreferences.edit().putString(PHONENO, phoneno).commit();
    }


    public static String getEMAIL() {
        return UserInfoPreferences.getString(EMAIL, "1234546789@qq.com");
    }

    public static void setEMAIL(String email) {
        UserInfoPreferences.edit().putString(EMAIL, email).commit();
    }

    public static String getWARRANTY() {
        return UserInfoPreferences.getString(WARRANTY, "服务条款");
    }

    public static void setWARRANTY(String warranty) {
        UserInfoPreferences.edit().putString(WARRANTY, warranty).commit();
    }


    public static String getNOTES() {
        return UserInfoPreferences.getString(NOTES, "下载须知");
    }

    public static void setNOTES(String notes) {
        UserInfoPreferences.edit().putString(NOTES, notes).commit();
    }


    public static String getABOUTUS() {
        return UserInfoPreferences.getString(ABOUTUS, "关于我们");
    }

    public static void setABOUTUS(String aboutus) {
        UserInfoPreferences.edit().putString(ABOUTUS, aboutus).commit();
    }


    public static String getREFUND() {
        return UserInfoPreferences.getString(REFUND, "退款说明");
    }

    public static void setREFUND(String refund) {
        UserInfoPreferences.edit().putString(REFUND, refund).commit();
    }

    //首页基础信息的时间戳
    public static String getTIMESTAMP() {
        return UserInfoPreferences.getString(TIMESTAMP, "0");
    }

    public static void setTIMESTAMP(String timeStamp) {
        UserInfoPreferences.edit().putString(TIMESTAMP, timeStamp).commit();
    }


    public static String PriceTIMESTAMP = "PRICETIMESTAMP";

    //价格区间的时间戳
    public static String getPriceTIMESTAMP() {
        return UserInfoPreferences.getString(PriceTIMESTAMP, "0");
    }

    public static void setPriceTIMESTAMP(String timeStamp) {
        UserInfoPreferences.edit().putString(PriceTIMESTAMP, timeStamp).commit();
    }


    public static String DistrectTIMESTAMP = "DistrectTIMESTAMP";
    public static String Twoons = "TWOONS";
    public static String Cals = "Cals";

    //获取乡镇地区的时间戳
    public static String getDistrectTIMESTAMP() {
        return UserInfoPreferences.getString(DistrectTIMESTAMP, "0");
    }

    public static void setDistrectTIMESTAMP(String timeStamp) {
        UserInfoPreferences.edit().putString(DistrectTIMESTAMP, timeStamp).commit();
    }


    public static Object getTwoons() throws IOException, ClassNotFoundException {
        String rs = UserInfoPreferences.getString(Twoons, null);
        if (rs != null)
            return Utils.deSerialization(rs);
        return null;
    }

    public static void setTwoons(Object twoons) throws IOException {
        UserInfoPreferences.edit().putString(Twoons, Utils.serialize(twoons)).commit();
    }

    public static void setCalendars(Object cals) throws IOException {
        UserInfoPreferences.edit().putString(Cals, Utils.serialize(cals)).commit();
    }

    public static Object getCalendars() throws IOException, ClassNotFoundException {
        String rs = UserInfoPreferences.getString(Cals, null);
        if (rs != null)
        {
            Object obj=Utils.deSerialization(rs);
            return obj;
        }
        return null;
    }

    public static Object getConditions(String type) throws IOException, ClassNotFoundException {
        String rs = UserInfoPreferences.getString(type, null);
        if (rs != null)
            return Utils.deSerialization(rs);
        return null;
    }

    public static void setConditions(Object condition, String type) throws IOException {

        UserInfoPreferences.edit().putString(type, Utils.serialize(condition)).commit();
    }

    //更新每隔三天

    public static String UPDATETIMESTAMP = "UPDATETIMESTAMP";

    //价格区间的时间戳
    public static long getUpdateTIMESTAMP() {

        return UserInfoPreferences.getLong(UPDATETIMESTAMP, 0);
    }

    public static void setUpdateTIMESTAMP(long timeStamp) {
        UserInfoPreferences.edit().putLong(UPDATETIMESTAMP, timeStamp).commit();
    }


}
