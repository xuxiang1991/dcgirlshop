package com.daocheng.girlshop.net;

import android.content.Context;
import android.content.Intent;

import com.daocheng.girlshop.activity.user.LoginActivity;
import com.daocheng.girlshop.entity.ServiceResult;
import com.google.gson.Gson;


import org.apache.http.protocol.HTTP;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Api {


    public static final String BASE_URL = "http://shenchujiayan.com:8000/dir/";//"http://192.168.1.101:8040/dir/""http://shenchujiayan.com:8000/dir/"
    public static final String URL_head="http://192.168.1.101:8050";
    
    /**
     * 首页图片
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getLogo(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("ts", com.daocheng.girlshop.utils.Config.getCurrentLOGO());
        NetUtils.post(context,BASE_URL+ "welcome", map, null, netCallBack, rspCls);
    }

    public static void update(Context context,String message, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("channel", "TW");
        map.put("vcode", com.daocheng.girlshop.utils.Utils.getClientVersionCode(context));

        NetUtils.post(context, BASE_URL + "ClientInfo", map, message, netCallBack, rspCls);
    }

    /**
     * 发送反馈
     *
     * @param context
     * @param content
     * @param netCallBack
     * @param rspCls
     */
    public static void sendFeedback(Context context, String personId, String content, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        if (personId != null) {
            map.put("personID", personId);
        } else {
            Intent loginIntent = new Intent(context, LoginActivity.class);
            context.startActivity(loginIntent);
        }
        map.put("content", content);
        NetUtils.post(context, BASE_URL + "feedback", map, null, netCallBack, rspCls);
    }


    /**
     * 首页基本信息
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getConstantsInfo(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("cc", 224);//CookeApplication.getLocationMessage().getCityCode()
        map.put("cn", "常熟");//Utils.CityTirm(CookeApplication.getLocationMessage().getDistrct())
        map.put("ts", com.daocheng.girlshop.utils.Config.getTIMESTAMP());
        NetUtils.post(context,BASE_URL+ "AreaInfo", map,null, netCallBack, rspCls);
    }


    /**
     * 广告页
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getBannerInfo(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {

        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        NetUtils.post(context,BASE_URL+ "AD", map, null, netCallBack, rspCls);
    }


    /**
     * 获取乡镇列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getDistrictInfo(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        map.put("ts", com.daocheng.girlshop.utils.Config.getDistrectTIMESTAMP());
        NetUtils.post(context,BASE_URL+ "AreaList", map, null, netCallBack, rspCls);
    }
    /**
     * 获取乡镇列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getSearchList(Context context,String Name,int pn ,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        map.put("Type","Chef");
        map.put("ty","L");
        map.put("Name",Name);
        map.put("Address","全城覆盖");
        map.put("pn",pn);
        NetUtils.post(context, BASE_URL + "ChefListWeb", map, null, netCallBack, rspCls);
    }


    /**
     *
     * 获取厨师列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCookerList(Context context, String town, String stime, String etime, String sprice, String eprice, int page, int state, String ty, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        if (town != null) {
            map.put("al", town);
        } else {
            map.put("al", 0);//0表示全部镇的数据
        }


        if (stime != null && etime != null) {
            map.put("tis", stime);
            map.put("tie", etime);
        }
        if (sprice != null && eprice != null) {
            map.put("prs", sprice);
            map.put("pre", eprice);
        }
        map.put("pn", page + 1);//因为接口数据是从1开始的
        map.put("st", state);
        map.put("ty", ty);
        NetUtils.post(context,BASE_URL+ "ChefList", map, null, netCallBack, rspCls);
//        String type=null;
//        try{
//            type = URLEncoder.encode("ChefList?chef=1&mama=妈妈", "utf-8");
//        }
//        catch (Exception e)
//        {
//
//        }
//        NetUtils.post(context,BASE_URL+type, map, null, netCallBack, rspCls);
    }

    /**
     * 获取茶旦列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getWaiterList(Context context, String town, String stime, String etime, String sprice, String eprice, int page, int state, String ty, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        if (town != null) {
            map.put("al", town);
        } else {
            map.put("al", 0);//0表示全部镇的数据
        }


        if (stime != null && etime != null) {
            map.put("tis", stime);
            map.put("tie", etime);
        }
        if (sprice != null && eprice != null) {
            map.put("prs", sprice);
            map.put("pre", eprice);
        }
        map.put("pn", page + 1);//因为接口数据是从1开始的
        map.put("st", state);
        map.put("Type", ty);//判断是否是商户还是厨师
        NetUtils.post(context,BASE_URL+ "Business", map, null, netCallBack, rspCls);
    }

    /**
     * 获取评论
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCommentList(Context context, String personId, int ps, int pn, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        if (personId != null) {
            map.put("personID", personId);
        }
        map.put("ps", ps);
        map.put("pn", pn + 1);
        NetUtils.post(context,BASE_URL+ "CommentsByUser", map, null, netCallBack, rspCls);
    }


    /**
     * 获取订单列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getOrderList(Context context, String roleName, String roleId, String id, String sta, int page, String token, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        if (roleName != null) {
            map.put("role", roleName);
        }
        if (id != null) {
            map.put("UserID", id);
        }
        if (sta != null) {
            map.put("status", sta);
        }
        map.put("pn", page + 1);
        map.put("Token", token);
        map.put("roleID", roleId);
        NetUtils.post(context,BASE_URL+ "OrderList", map, null, netCallBack, rspCls);
    }

    /**
     * 获取订单详情
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getOrderDetailInfo(Context context, String orderId, String
            userid, String token, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        if (orderId != null) {
            map.put("orderID", orderId);
        }
        map.put("Token", token);
        map.put("UserID", userid);
        NetUtils.post(context,BASE_URL+ "OrderInfo", map, null, netCallBack, rspCls);
    }

    /**
     * 获取收藏厨师列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCookerListByUser(Context context, String personID, int page, final Class<?> rspCls, final NetUtils.NetCallBack<ServiceResult> netCallBack) {
        HashMap map = new HashMap();
        map.put("personID", personID);
        map.put("pn", page + 1);//因为接口数据是从1开始的


        NetUtils.post(context,BASE_URL+ "FavChef", map, null, netCallBack, rspCls);
    }


    /**
     * 添加删除收藏厨师列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void setcollectCookerByUser(Context context, String personID, String chefid, String ac, String ty, final Class<?> rspCls, final NetUtils.NetCallBack<ServiceResult> netCallBack) {
        HashMap map = new HashMap();
        map.put("personID", personID);
        map.put("chefID", chefid);
        map.put("ac", ac);
        map.put("ty", ty);

        NetUtils.post(context,BASE_URL+ "modifyFavChef", map, null, netCallBack, rspCls);
    }


    /**
     * 获取厨师信息
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCookeInfo(Context context, String chefID, String type, String Userid, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("chefID", chefID);
        map.put("personID", Userid);//userID
        map.put("ty", type);//userID
        NetUtils.post(context,BASE_URL+ "ChefInfo", map, null, netCallBack, rspCls);
    }


    /**
     * 获取套餐列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getMenuLInfo(Context context, String chefID, String ty, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("chefID", chefID);
        map.put("ty", ty);
        NetUtils.post(context,BASE_URL+ "Menu", map, null, netCallBack, rspCls);
    }


    /**
     * 获取菜单列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getMenuListInfo(Context context, String MenuID, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("MenuID", MenuID);
        NetUtils.post(context,BASE_URL+ "MenuDetail", map, null, netCallBack, rspCls);
    }

    /**
     * 获取小饭菜单
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getMultiMenu(Context context, String MenuID, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("MenuID", MenuID);
        NetUtils.post(context,BASE_URL+ "MultiMenuDetail", map, null, netCallBack, rspCls);
    }

    /**
     * 发送验证码
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCode(Context context, String telephone, String role, final Class<?> rspCls, final NetUtils.NetCallBack<ServiceResult> netCallBack) {
        HashMap map = new HashMap();
        map.put("telephone", telephone);
//        map.put("role", role);
        map.put("imei", com.daocheng.girlshop.utils.Utils.getIMEI(context));
        NetUtils.post(context,BASE_URL+ "sendCode", map, null, netCallBack, rspCls);
    }

    /**
     * 登录
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void loginIn(Context context, String telephone, String code, String role, final Class<?> rspCls, final NetUtils.NetCallBack<ServiceResult> netCallBack) {
        HashMap map = new HashMap();
        map.put("telephone", telephone);
        map.put("code", code);
        map.put("role", role);

        NetUtils.post(context,BASE_URL+ "Login", map, null, netCallBack, rspCls);
    }


    /**
     * 获取厨师评论列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCommentInfo(Context context, String cookId, int page, int count, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("ChefID", cookId);
        map.put("pn", page + 1);//从第一页开始
        map.put("ps", count);
        NetUtils.post(context,BASE_URL+ "Comments", map, null, netCallBack, rspCls);
    }


    /**
     * 获取价钱区间的列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getPricequInfo(Context context, String type, String timestep, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("AID", com.daocheng.girlshop.utils.Config.getID());
        map.put("name", type);
        map.put("ts", timestep);
        NetUtils.post(context,BASE_URL+ "Condition", map, null, netCallBack, rspCls);
    }




    /**
     * 获取常用地址列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCommonAddressList(Context context, String userId, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.post(context,BASE_URL+ "AddList?UserID=" + userId, map, "正在获取地址...", netCallBack, rspCls);
    }

    /**
     * 获取默认地址
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getdefaultAddress(Context context, String userId, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.post(context,BASE_URL+ "getDefAdd?UserID=" + userId, map, "正在获取地址...", netCallBack, rspCls);
    }




    /**
     * 删除常用地址
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void deleteCommonAddressList(Context context, String delID, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.post(context,BASE_URL+ "AddDel?addID=" + delID, map, "地址删除中...", netCallBack, rspCls);
    }

    /**
     * 获取地区列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCommonVillageList(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.post(context,BASE_URL+ "AreaList?AID=1&ts=0", map, null, netCallBack, rspCls);
    }

    /**
     * 上传文件
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void uploadFile(Context context, ArrayList<String> paths, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.upload(context, BASE_URL + "PicIns", map, paths, "正在上传图片...", netCallBack, rspCls);
    }

    /**
     * 提交评论
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void submitCommit(Context context, String type, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        NetUtils.post(context, BASE_URL + "commIns?" + type, map, "正在提交评论...", netCallBack, rspCls);
    }

    /**
     * 设置订单状态
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void setOderstate(Context context, String orderid, String state, String token, String userid, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("OrderID", orderid);
        map.put("Status", state);
        map.put("Token", token);
        map.put("UserID", userid);
        NetUtils.post(context,BASE_URL+ "SetOrderStatus", map, "正在提交...", netCallBack, rspCls);
    }

    /**
     * 设置订单状态伴随价格异动
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void setOderCharge(Context context, String orderid, String state, String userid, String token, int money, String accType, String accNO, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("OrderID", orderid);
        map.put("Status", state);
        map.put("Token", token);
        map.put("UserID", userid);
        map.put("Money", money);
        map.put("accType", accType);
        map.put("accNO", accNO);//退款帐号
        NetUtils.post(context,BASE_URL+ "OrderCharge", map, "正在提交请求...", netCallBack, rspCls);
    }


    /**
     * 订单支付
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void payfororder(Context context, String orderid, String userid, String token, int money, int CouponID, String CouponType, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("OrderID", orderid);
        map.put("Token", token);
        map.put("UserID", userid);
        map.put("Money", money);
        if (CouponID != 0) {
            map.put("CouponID", CouponID);
            map.put("CouponType", CouponType);//优惠券
        }

        NetUtils.post(context,BASE_URL+ "PayDeposit", map, "正在提交请求...", netCallBack, rspCls);
    }


    /**
     * 默认优惠券
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void DefaultCoupon(Context context, String userid, int Count, String orderdate,String BusinessType, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("UserID", userid);
        map.put("Count", Count);//count为总金额
        map.put("orderDate", orderdate);
        map.put("BusinessType",BusinessType);
        NetUtils.post(context,BASE_URL+ "DefaultCoupon", map, null, netCallBack, rspCls);
    }


    /**
     * 根据User获取优惠券列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCouponListByUser(Context context, String userid, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("UserID", userid);
        NetUtils.post(context,BASE_URL+ "PersonalCouponList", map, null, netCallBack, rspCls);
    }



    /**
     * 根据获取优惠券列表
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getCouponList(Context context, String userid,int Count,String orderDate,String BusinessType, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("UserID", userid);
        map.put("Count", Count);
        map.put("orderDate", orderDate);
        map.put("BusinessType",BusinessType);
        NetUtils.post(context,BASE_URL+ "CouponList", map, null, netCallBack, rspCls);
    }


    /**
     *微信支付
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getwxapi(Context context,String OrderID,String couponstr,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("OrderID", OrderID);
        map.put("Ty", "APP");
        if (couponstr!=null)
        map.put("Coupon", couponstr);
        NetUtils.post(context,BASE_URL+ "WxPay", map, "正在请求支付", netCallBack, rspCls);
    }



    /**
     *提交优惠券
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void setCoupon(Context context,String OrderID,int CouponID,String Userid,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
        map.put("OrderID", OrderID);
        map.put("CouponID", CouponID);
        map.put("CouponType", Userid);
        NetUtils.post(context,BASE_URL+"SetCoupon", map, null, netCallBack, rspCls);
    }


    /**
     *老黄历
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void GetLunar(Context context,String day,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
//        map.put("date", day);
//        map.put("key", "db006b9806a19c864cd2c909a660149a");//http://www.haoservice.com/docs/42/h 申请的key

        NetUtils.post(context,"http://v.juhe.cn/laohuangli/d?date="+day+"&key=db006b9806a19c864cd2c909a660149a", map, null, netCallBack, rspCls);
    }



    /**
     *一键预约
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void setQuickOrder(Context context,String name ,String tele,String town ,String price ,String Num  ,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();
//        map.put("step-Name", name);
//        map.put("step-tele", tele);
//        map.put("hidstep-town", town);
//        map.put("hidstep-price", price);
//        map.put("hidstep-Num", Num);
        String strUTF8=null;
        try
        {
            strUTF8 = "http://192.168.1.119/pc/function/FastOrder.php?step-Name="+URLDecoder.decode(name, HTTP.UTF_8)+"&step-tele="+URLDecoder.decode(tele, HTTP.UTF_8)+"&hidstep-town="+URLDecoder.decode(town, HTTP.UTF_8)+"&hidstep-price="+URLDecoder.decode(price, HTTP.UTF_8)+"&hidstep-Num="+URLDecoder.decode(Num, HTTP.UTF_8);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        NetUtils.post(context,strUTF8, null, "提交中", netCallBack, rspCls);
    }


    /**
     *获取当前月份的吉日
     *
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getLucyDaysOfMonth(Context context,String date  ,final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        HashMap map = new HashMap();

        NetUtils.post(context,"http://www.shenchujiayan.com/Comm/Laohuangli.php?date="+date, map, null, netCallBack, rspCls);//2015-10
    }


}
