package com.example.jifenshop.user.util;

import com.example.jifenshop.user.entity.PointsShopVO;
import com.example.jifenshop.user.entity.ResponseMessageEntity;
import com.example.jifenshop.user.entity.SearchResultVO;
import com.example.jifenshop.user.entity.CouponVO;
import com.example.jifenshop.user.entity.UserCouponVO;
import com.example.jifenshop.user.entity.UserSimpleInfoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hncboy on 2019-05-07.
 */
public class JsonParser {

    private static final Gson mGson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static ArrayList<CouponVO> getCoupon(String json) {
        Type token = new TypeToken<ResponseMessageEntity<ArrayList<CouponVO>>>() {
        }.getType();
        ResponseMessageEntity<ArrayList<CouponVO>> entity = mGson.fromJson(json, token);
        if (entity.getStatus() == 1) {
            return entity.getData();
        } else {
            return new ArrayList<>();
        }
    }

    public static ArrayList<UserCouponVO> getUserCoupon(String json) {
        Type token = new TypeToken<ResponseMessageEntity<ArrayList<UserCouponVO>>>() {
        }.getType();
        ResponseMessageEntity<ArrayList<UserCouponVO>> entity = mGson.fromJson(json, token);
        if (entity.getStatus() == 1) {
            return entity.getData();
        } else {
            return new ArrayList<>();
        }
    }
    public static UserSimpleInfoVO getSimpleUserInfo(String json) {
        Type token = new TypeToken<ResponseMessageEntity<UserSimpleInfoVO>>() {
        }.getType();
        ResponseMessageEntity<UserSimpleInfoVO> entity = mGson.fromJson(json, token);
        if (entity.getStatus() == 1) {
            return entity.getData();
        } else {
            return new UserSimpleInfoVO();
        }
    }

    public static ArrayList<SearchResultVO> getSearchResult(String json) {
        Type token = new TypeToken<ResponseMessageEntity<ArrayList<SearchResultVO>>>() {
        }.getType();
        ResponseMessageEntity<ArrayList<SearchResultVO>> entity = mGson.fromJson(json, token);
        if (entity.getStatus() == 1) {
            return entity.getData();
        } else {
            return null;
        }
    }

    public static PointsShopVO getIntegrationShopInfo(String json) {
        Type token = new TypeToken<ResponseMessageEntity<PointsShopVO>>() {
        }.getType();
        ResponseMessageEntity<PointsShopVO> entity = mGson.fromJson(json, token);
        if (entity.getStatus() == 1) {
            return entity.getData();
        } else {
            return null;
        }
    }

    public static boolean isExchangeGoods(String json) {
        Type token = new TypeToken<ResponseMessageEntity<String>>() {
        }.getType();
        ResponseMessageEntity<String> entity = mGson.fromJson(json, token);
        return entity.getStatus() == 1;
    }


}
