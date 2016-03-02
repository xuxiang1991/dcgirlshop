package com.daocheng.girlshop.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 请求结果
 * Dove
 * 2015/07/28
 */
public class ServiceResult implements Serializable {
    @SerializedName("recode")
    public int recode;

    public int getRecode() {
        return recode;
    }

    public void setRecode(int retcode) {
        this.recode = recode;
    }
}
