package com.daocheng.girlshop.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2015/10/21.
 */
public class User extends ServiceResult {

    @SerializedName("ID")
    String id;
    @SerializedName("Name")
    String name;
    @SerializedName("Sex")
    String sex;
    @SerializedName("MobilePhone")
    String mobilePhone;
    @SerializedName("Phone")
    String phone;
    @SerializedName("Token")
    String Token;

    @SerializedName("ChefID")
    String ChefID;

    public String getChefID() {
        return ChefID;
    }

    public void setChefID(String chefID) {
        ChefID = chefID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @SerializedName("role")
    String role;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
