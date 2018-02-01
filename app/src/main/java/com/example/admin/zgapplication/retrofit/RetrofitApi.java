package com.example.admin.zgapplication.retrofit;

import com.example.admin.zgapplication.base.CompanyEvaluationList;
import com.example.admin.zgapplication.base.EvaluationListResponse;
import com.example.admin.zgapplication.mvp.module.AboutUsResponse;
import com.example.admin.zgapplication.mvp.module.AddEvaluationResponse;
import com.example.admin.zgapplication.mvp.module.AgentDetailResponse;
import com.example.admin.zgapplication.mvp.module.AgentEvaluationResponse;
import com.example.admin.zgapplication.mvp.module.AgentListResponse;
import com.example.admin.zgapplication.mvp.module.AgentLocationResponse;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.BillFinishResponse;
import com.example.admin.zgapplication.mvp.module.BillPayImmediately;
import com.example.admin.zgapplication.mvp.module.CityResponse;
import com.example.admin.zgapplication.mvp.module.CollectionListResponse;
import com.example.admin.zgapplication.mvp.module.CompanyHomePageResponse;
import com.example.admin.zgapplication.mvp.module.CompanyHouseListResponse;
import com.example.admin.zgapplication.mvp.module.CompanyListResponse;
import com.example.admin.zgapplication.mvp.module.ConfirmPayResponse;
import com.example.admin.zgapplication.mvp.module.ContactDetailResponse;
import com.example.admin.zgapplication.mvp.module.ContractListResponse;
import com.example.admin.zgapplication.mvp.module.CrabCountResponse;
import com.example.admin.zgapplication.mvp.module.CrabListResponse;
import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.mvp.module.EmptyResponse;
import com.example.admin.zgapplication.mvp.module.EvaluationSuccessDetail;
import com.example.admin.zgapplication.mvp.module.GenerateOrderResponse;
import com.example.admin.zgapplication.mvp.module.HotSearchListResponse;
import com.example.admin.zgapplication.mvp.module.HouseEvaluationRespose;
import com.example.admin.zgapplication.mvp.module.HouseResourseListBean;
import com.example.admin.zgapplication.mvp.module.IntentionDetailResponse;
import com.example.admin.zgapplication.mvp.module.LaunchTakeLookResponse;
import com.example.admin.zgapplication.mvp.module.LifeBillRecordResponse;
import com.example.admin.zgapplication.mvp.module.LifeRentBillResponse;
import com.example.admin.zgapplication.mvp.module.LoginResponse;
import com.example.admin.zgapplication.mvp.module.MakeEvaluationDetailResponse;
import com.example.admin.zgapplication.mvp.module.ModifyIconResponse;
import com.example.admin.zgapplication.mvp.module.MsgCodeResponse;
import com.example.admin.zgapplication.mvp.module.OrderDetailResponse;
import com.example.admin.zgapplication.mvp.module.OrderList;
import com.example.admin.zgapplication.mvp.module.PayInfoResponse;
import com.example.admin.zgapplication.mvp.module.RecommendAgentsListResponse;
import com.example.admin.zgapplication.mvp.module.RecommendHouseListResponse;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.mvp.module.RentBillResponse;
import com.example.admin.zgapplication.mvp.module.RentIntentListResponse;
import com.example.admin.zgapplication.mvp.module.RentReadyPayResponse;
import com.example.admin.zgapplication.mvp.module.RestartIntentResponse;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SearchResultResponse;
import com.example.admin.zgapplication.mvp.module.SelfInfo;
import com.example.admin.zgapplication.mvp.module.StartIntent;
import com.example.admin.zgapplication.mvp.module.TakeLookDetail;
import com.example.admin.zgapplication.mvp.module.TakeLookInfoResponse;
import com.example.admin.zgapplication.mvp.module.TakeLookListResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Serenade on 17/6/10.
 */

public interface RetrofitApi {
    String Host = "http://api.zhagen.com/";

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
                                                       @Query("room") String room,
                                                       @Query("house_name") String house_name,
                                                       @Query("order") String order,
                                                       @Query("sort") String sort,
                                                       @Query("areaid") Integer areaid,
                                                       @Query("level") Integer level,
                                                       @Query("page") Integer page,
                                                       @Query("house_type") String house_type,
                                                       @Query("special") String special);

    @GET("/member/order/index")
    Observable<OrderList> getOrderList(@Query("status") Integer status, @Query("page") Integer page);

    @GET("/member/coupon/index")
    Observable<DiscountListResponse> getDiscountList(@Query("status") int status, @Query("page") Integer page);


    @FormUrlEncoded
    @POST("/member/coupon/change")
    Observable<BaseResponse> exChangeCoupon(@Field("uid") String uid, @Field("code") String code);


    @GET("/member/order/detail")
    Observable<OrderDetailResponse> getOrderDetail(@Query("order_id") String order_id);

    //删除订单,取消订单一个接口
    @FormUrlEncoded
    @POST("/member/order/del")
    Observable<BaseResponse> delOrder(@Field("uid") String uid, @Field("order_id") String order_id, @Field("type") Integer type);

    @GET("/member/user/index")
    Observable<SelfInfo> getSelfInfo();

    @GET("/member/user/save")
    Observable<BaseResponse> modifyUserInfo(@Query("nickname") String nickname);

    @GET("/member/contract/mycontract")
    Observable<ContractListResponse> getContractList();

    @GET("/member/intention/index")
    Observable<RentIntentListResponse> getIntentList(@Query("page") Integer page);

    @GET("/member/intention/del")
    Observable<BaseResponse> delIntentItem(@Query("id") String id);

    @GET("/district/city")
    Observable<CityResponse> getCityList();

    @GET("/district/region")
    Observable<RegionResponse> getRegionResponse(@Query("cityId") String cityId);

    //发起意向
    @FormUrlEncoded
    @POST("/member/intention/add")
    Observable<StartIntent> startIntention(@Field("district_id") Integer district_id, @Field("outset") Integer outset,
                                           @Field("cutoff") Integer cutoff, @Field("method") Integer method,
                                           @Field("room") String room, @Field("uid") String uid);

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
    Observable<RoomDetailResponse> getRoomDetail(@Query("type") String type, @Query("house_id") String house_id, @Query("room_id") String room_id);

    @FormUrlEncoded
    @POST("/member/collection/add")
    Observable<BaseResponse> addCollection(@Field("uid") String uid, @Field("house_id") String house_id, @Field("room_id") String room_id, @Field("type") String type);

    @GET("/member/collection/index")
    Observable<CollectionListResponse> getCollectList(@Query("page") Integer page);

    //删除收藏
    @GET("/member/collection/del")
    Observable<BaseResponse> delCollectionRecord(@Query("id") String id);


    //用户带看列表
    @GET("/member/visit-house/list")
    Observable<TakeLookListResponse> getTakeLookList(@Query("page") Integer page, @Query("status") Integer status);


    @GET("/member/visit-house/detail")
    Observable<TakeLookDetail> getTakelookDetail(@Query("id") String id);

    @FormUrlEncoded
    @POST("/member/visit-house/success")
    Observable<BaseResponse> confirmTakeLook(@Field("uid") String uid, @Field("id") String id);

    @FormUrlEncoded
    @POST("/member/visit-house/cancel")
    Observable<BaseResponse> cancelTakeLook(@Field("uid") String uid, @Field("id") String id, @Field("name") String name);


    //获取经纪人列表
    @GET("/member/visit-house/index")
    Observable<AgentListResponse> getAgentList(@Query("room_id") String room_id, @Query("is_centra") String is_centra,
                                               @Query("house_id") String house_id, @Query("page") Integer page);

    //预约带看页面
    @FormUrlEncoded
    @POST("/member/visit-house/create")
    Observable<TakeLookInfoResponse> getTakeLookInfo(@Field("room_id") String room_id, @Field("type") String type,
                                                     @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                     @Field("uid") String uid);


    //发起带看
    @FormUrlEncoded
    @POST("/member/visit-house/add")
    Observable<LaunchTakeLookResponse> lanchTakeLookResponse(@Field("room_id") String room_id, @Field("type") String type,
                                                             @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                             @Field("uid") String uid, @Field("name") String name, @Field("expect_time") String expect_time);


    // 推荐房源
    @GET("/member/intention/houses")
    Observable<RecommendHouseListResponse> getRecommendHouse(@Query("id") int id, @Query("page") int page);

    //推荐经纪人
    @GET("/member/intention/members")
    Observable<RecommendAgentsListResponse> getRecommendAgents(@Query("id") int id, @Query("page") int page);

    //抢单数量
    @GET("/member/intention/num")
    Observable<CrabCountResponse> getCrabCount(@Query("id") int id);


    //经纪人详情
    @GET("/member/intention/agent-detail")
    Observable<AgentDetailResponse> getAgentDetail(@Query("id") String id);

    @GET("/member/intention/list")
    Observable<AgentEvaluationResponse> getAgentEvaluation(@Query("id") String id, @Query("page") int page);

    //公司评价

    @FormUrlEncoded
    @POST("/member/company/comment")
    Observable<CompanyEvaluationList> getCompanyEvaluation(@Field("company_id") int company_id);


    @GET("/member/company/house")
    Observable<CompanyHouseListResponse> getCompanyHouse(@Query("company_id") int company_id,
                                                         @Query("rent_start") Integer rent_start,
                                                         @Query("rent_end") Integer rent_end,
                                                         @Query("room") String room,
                                                         @Query("house_name") String house_name,
                                                         @Query("order") String order,
                                                         @Query("sort") String sort,
                                                         @Query("areaid") Integer areaid,
                                                         @Query("level") Integer level,
                                                         @Query("page") Integer page,
                                                         @Query("house_type") String house_type,
                                                         @Query("special") String special);

    @GET("/member/company/index")
    Observable<CompanyHomePageResponse> getCompanyHomePage(@Query("company_id") int company_id);



    @GET("/member/index/house-review")
    Observable<HouseEvaluationRespose> getHouseEvaluation(@Query("house_id") String house_id,@Query("type") String type,@Query("room_id") String room_id);

    @GET("/member/search/hot")
    Observable<HotSearchListResponse> getHotSearch();

    @GET("/member/intention/grab")
    Observable<CrabListResponse> getCrabList(@Query("id") int iid,@Query("page") int page);

    @GET("/member/search/index")
    Observable<SearchResultResponse> getSearchResult(@Query("keyword") String keyword, @Query("page") Integer page);

    @GET("/member/search/more")
    Observable<CompanyListResponse> getSearchMoreCompany(@Query("keyword") String keyword, @Query("more_type") Integer more_type,@Query("page") Integer page);

    @GET("/member/search/more")
    Observable<RecommendHouseListResponse> getSearchMoreHouse(@Query("keyword") String keyword, @Query("more_type") Integer more_type,@Query("page") Integer page);

    @FormUrlEncoded
    @POST("/member/order/confirm")
    Observable<OrderDetailResponse> confirmOrder(@Field("room_id") String room_id, @Field("type") String type,
                                                 @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                 @Field("uid") String uid,@Field("rental") int rental,
                                                 @Field("date") int date,@Field("pay") int pay,
                                                 @Field("pledge") int pledge,@Field("room_number") String room_number );


    //确认订单
    @FormUrlEncoded
    @POST("/member/order/generate")
    Observable<GenerateOrderResponse> generateOrder(@Field("room_id") String room_id, @Field("type") String type,
                                                    @Field("house_id") String house_id, @Field("member_id") String member_id,
                                                    @Field("uid") String uid, @Field("date") int date, @Field("pay") int pay,
                                                    @Field("pledge") int pledge, @Field("real_name") String real_name,
                                                    @Field("phone_number") String phone_number, @Field("user_coupon_id") int user_coupon_id);


    //评价列表
    @GET("/member/review/index")
    Observable<EvaluationListResponse> getEvaluationList(@Query("method") int method,@Query("reviewed") int reviewed,@Query("page") int page);


    //发表评价界面补充信息接口
    @GET("member/review/create")

    /**
     * id : 成交或者带看id
     */
    Observable<MakeEvaluationDetailResponse> getEvaluationInfo(@Query("method") String method,@Query("id") String id);

    //提交评价接口
//    @FormUrlEncoded
//    @POST("/member/review/add")
//    Observable<BaseResponse> makeEvaluation(@Field("uid") String uid, @Field("single_id") String single_id,
//                                            @Field("method") int method, @Field("member_label") String member_label,
//                                            @Field("member_score") String member_score, @Field("member_content") String member_content,
//                                            @Field("house_score") String house_score, @Field("house_label") String house_label,
//                                            @Field("house_content") String house_content, @Field("company_label") String company_label,
//                                            @Field("company_score") String company_score, @Field("company_content") String company_content,
//                                            @Field("hide") int hide, @PartMap Map<String, MultipartBody.Part> map);

    @POST("/member/review/add")
    Observable<AddEvaluationResponse> makeEvaluation(@Body MultipartBody multipartBody);


    @GET("/member/review/detail")
    Observable<EvaluationSuccessDetail> getEvaluationDetail(@Query("method") String method,@Query("id") String id);


    @GET("district/aboutus")
    Observable<AboutUsResponse> getAboutUsResponse();

    @GET("member/contract/life-pay")
    Observable<LifeRentBillResponse> getLifeRentBillResponse(@Query("order_num") String order_num);


    @GET("member/contract/record")
    Observable<LifeBillRecordResponse> getLifeRecordList(@Query("order_num") String order_num);

    @FormUrlEncoded
    @POST("sms/code")
    Observable<MsgCodeResponse> getMsgCodeResponse(@Field("phone_number") String number);


    @FormUrlEncoded
    @POST("member/login")
    Observable<LoginResponse> login(@Field("phone_number") String phone_number, @Field("code") String code);


    @GET("/member/home")
    Observable<AgentLocationResponse> getAgentLocation(@Query("lat") double lng,@Query("lng") double lat);


    @GET("/member/intention/detail")
    Observable<IntentionDetailResponse> getIntentDetail(@Query("id") String iid,@Query("page") int page);

    @GET("/member/contract/select")
    Observable<BillPayImmediately> getPayImmediately(@Query("id") String id);


    @FormUrlEncoded
    @POST("/member/pay/to-pay")
    Observable<PayInfoResponse> getPayInfo(@Field("type") String type,@Field("order_num") String order_num,@Field("method") String method);

    @POST("/member/user/saves")
    Observable<ModifyIconResponse> modifyHeadIcon(@Body MultipartBody multipartBody);


    @GET("/member/order/pay")
    Observable<RentReadyPayResponse> getRentReadyPayResponse(@Query("order_id") String order_id);

    @FormUrlEncoded
    @POST("/member/intention/adds")
    Observable<RestartIntentResponse> restartIntent(@Field("uid")String uid,@Field("id") String iid);


    @GET("member/intention/cancel")
    Observable<BaseResponse> cancleIntention(@Query("id") int id);

    @FormUrlEncoded
    @POST("/agent/chat/add")
    Observable<EmptyResponse> addHXFriend(@Field("member_id") String member_id, @Field("uid") String uid);



}
