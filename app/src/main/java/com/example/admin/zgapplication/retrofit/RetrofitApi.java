package com.example.admin.zgapplication.retrofit;

import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.mvp.module.HouseResourseListBean;
import com.example.admin.zgapplication.mvp.module.OrderList;

import io.reactivex.Observable;
import retrofit2.http.GET;
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

    @GET("/coupon/index")
    Observable<DiscountListResponse> getDiscountList(@Query("status") int status);

}
