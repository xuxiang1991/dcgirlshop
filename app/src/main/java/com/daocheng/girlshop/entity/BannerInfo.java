package com.daocheng.girlshop.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：Cooke
 * 类描述：广告页
 * 创建人：Dove
 * 创建时间：2015/10/9 13:46
 * 修改人：Dove
 * 修改时间：2015/10/9 13:46
 * 修改备注：广告页信息
 */
public class BannerInfo extends ServiceResult{

    @SerializedName("result")
    public List<banner> result;

    public List<banner> getBannerList() {
        return result;
    }

    public void setBannerList(List<banner> result) {
        this.result = result;
    }

    public class banner implements Serializable
    {
        @SerializedName("ID")
        public String ID;

        @SerializedName("Name")
        public String Name;

        @SerializedName("ImgUrl")
        public String ImgUrl;

        @SerializedName("Action")
        public String Action;

        @SerializedName("Type")
        public String Type;

        @SerializedName("TypeValue")
        public String TypeValue;

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getTypeValue() {
            return TypeValue;
        }

        public void setTypeValue(String typeValue) {
            TypeValue = typeValue;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String imgUrl) {
            ImgUrl = imgUrl;
        }

        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }
    }
}
