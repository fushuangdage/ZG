package com.example.admin.zgapplication.retrofit;

import com.example.admin.zgapplication.mvp.module.AgentListResponse;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.BillFinishResponse;
import com.example.admin.zgapplication.mvp.module.CityResponse;
import com.example.admin.zgapplication.mvp.module.CollectionListResponse;
import com.example.admin.zgapplication.mvp.module.ConfirmPayResponse;
import com.example.admin.zgapplication.mvp.module.ContactDetailResponse;
import com.example.admin.zgapplication.mvp.module.ContractListResponse;
import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.mvp.module.HouseResourseListBean;
import com.example.admin.zgapplication.mvp.module.LaunchTakeLookResponse;
import com.example.admin.zgapplication.mvp.module.OrderDetailResponse;
import com.example.admin.zgapplication.mvp.module.OrderList;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.mvp.module.RentBillResponse;
import com.example.admin.zgapplication.mvp.module.RentIntentListResponse;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SelfInfo;
import com.example.admin.zgapplication.mvp.module.StartIntent;
import com.example.admin.zgapplication.mvp.module.TakeLookDetail;
import com.example.admin.zgapplication.mvp.module.TakeLookInfoResponse;
import com.example.admin.zgapplication.mvp.module.TakeLookListResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Serenade on 17/6/10.
 */

public interface RetrofitApi {
    String Host="http://api.zhagen.com/";

//
//    @GET(Host)
//    Call<MusicSearchResult> getSearchResult(@Query("keyword") String songName, @Query("page") int page);
//
//
//    @GET(LyricHost)
//    Call<LyricResult> getLyricResult(@Query("musicid") int musicid);
//

    @GET("/member/index/index")
    Observable<HouseResourseListBean> getApartmentList(@Query("rent_start") Integer rent_start,
                                                       @Query("rent_end") Integer rent_end,
                                                       @Query("room")String room,
                                                       @Query("house_name") String house_name,
                                                       @Query("order") String order,
                                                       @Query("sort") String sort,
                                                       @Query("areaid") Integer areaid,
                                                       @Query("level") Integer level,
                                                       @Query("page") Integer page,
                                                       @Query("house_type") String house_type);

    @GET("/member/order/index")
    Observable<OrderList> getOrderList(@Query("status") Integer status,@Query("page") Integer page);

    @GET("/member/coupon/index")
    Observable<DiscountListResponse> getDiscountList(@Query("status") int status,@Query("page") Integer page);


    @FormUrlEncoded
    @POST("/member/coupon/change")
    Observable<BaseResponse> exChangeCoupon(@Field("uid") String uid,@Field("code") String code);


    @GET("/member/order/detail")
    Observable<OrderDetailResponse> getOrderDetail(@Query("order_id") String order_id);

    //删除订单,取消订单一个接口
    @FormUrlEncoded
    @POST("/member/order/del")
    Observable<BaseResponse> delOrder(@Field("uid") String uid,@Field("order_id") String order_id,@Field("type") Integer type);

    @GET("/member/user/index")
    Observable<SelfInfo> getSelfInfo();

    @GET("/member/user/save")
    Observable<BaseResponse> modifyUserInfo(@Query("nickname") String nickname);

    @GET("/member/contract/mycontract")
    Observable<ContractListResponse> getContractList();

    @GET("/member/intention/index")
    Observable<RentIntentListResponse> getIntentList(@Query("page") Integer page);

    @GET("/member/intention/del")
    Observable<BaseResponse> delIntentItem(@Query("i_id") String i_id);

    @GET("/district/city")
    Observable<CityResponse> getCityList();

    @GET("/district/region")
    Observable<RegionResponse> getRegionResponse(@Query("cityId") String cityId);

    //发起意向
    @GET("/member/intention/add")
    Observable<StartIntent> startIntention(@Query("district_id")String district_id, @Query("outset") Integer outset,
                                           @Query("cutoff") Integer cutoff, @Query("method") Integer method,
                                           @Query("room") String room);
    //合同详情
    @GET("/member/contract/detail")
    Observable<ContactDetailResponse> getContactDetail(@Query("order_num") String order_id);

    //房租账单列表
    @GET("/member/contract/bill")
    Observable<RentBillResponse> getRentBill(@Query("order_num") String order_num);

    //房租账单详情(已完成)
    @GET("/member/contract/fbill")
    Observable<BillFinishResponse> getDoneBill(@Query("bill_num") String bill_num);

    @GET("/member/contract/topay")
    Observable<BillFinishResponse> getToPayBill(@Query("bill_num") String bill_num);

    @FormUrlEncoded
    @POST("/member/contract/sure")
    Observable<ConfirmPayResponse> confirmPayResponse(@Field("bill_num") String bill_num, @Field("user_coupon_id") String user_coupon_id, @Field("user_coupon_money") String user_coupon_money);

    @GET("/member/index/all")
    Observable<RoomDetailResponse> getRoomDetail(@Query("type")String type,@Query("house_id") String house_id,@Query("room_id") String room_id);

    @FormUrlEncoded
    @POST("/member/collection/add")
    Observable<BaseResponse> addCollection(@Field("uid") String uid,@Field("house_id") String house_id,@Field("room_id") String room_id,@Field("type") String type);

    @GET("/member/collection/index")
    Observable<CollectionListResponse> getCollectList(@Query("page") Integer page);

    //删除收藏
    @GET("/member/collection/del")
    Observable<BaseResponse> delCollectionRecord(@Query("id") String id);


    //用户带看列表
    @GET("/member/visit-house/data")
    Observable<TakeLookListResponse> getTakeLookList(@Query("page") Integer page, @Query("status") Integer status);


    @GET("/member/visit-house/detail")
    Observable<TakeLookDetail> getTakelookDetail(@Query("id") String id);

    @FormUrlEncoded
    @POST("/member/visit-house/success")
    Observable<BaseResponse> confirmTakeLook(@Field("uid") String uid, @Field("id") String id);

    @FormUrlEncoded
    @POST("/member/visit-house/cancel")
    Observable<BaseResponse> cancelTakeLook(@Field("uid") String uid,@Field("id") String id,@Field("name") String name);


    //获取经纪人列表
    @GET("/member/visit-house/index")
    Observable<AgentListResponse> getAgentList(@Query("room_id") String room_id,@Query("is_centra")String is_centra,
                                               @Query("house_id") String house_id,@Query("page") Integer page);

    //预约带看页面
    @FormUrlEncoded
    @POST("/member/visit-house/create")
    Observable<TakeLookInfoResponse> getTakeLookInfo(@Field("room_id") String room_id, @Field("type")String type,
                                                     @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                     @Field("uid") String uid);


    //发起带看
    @FormUrlEncoded
    @POST("/member/visit-house/add")
    Observable<LaunchTakeLookResponse> lanchTakeLookResponse(@Field("room_id") String room_id, @Field("type")String type,
                                                             @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                             @Field("uid") String uid, @Field("name")String name, @Field("expect_time") String expect_time);


}
