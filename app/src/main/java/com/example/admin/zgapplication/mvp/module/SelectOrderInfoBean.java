package com.example.admin.zgapplication.mvp.module;

import java.io.Serializable;

/**
 * Created by fushuang on 2018/1/18.
 */

public class SelectOrderInfoBean implements Serializable{

    public String member_id;//经纪人id
    public String house_id ;//房源id
    public String room_id;  //房间id
    public String type;     //1分散式 2 集中式
    public int rental;      // 租金
    public int date;     //租赁期限
    public int pay;  //付几
    public int pledge;  //押几
    public String room_number;  //房间号


    public SelectOrderInfoBean(String house_id, String room_id, String type, int rental, int date, int pay, int pledge, String room_number) {
        this.house_id = house_id;
        this.room_id = room_id;
        this.type = type;
        this.rental = rental;
        this.date = date;
        this.pay = pay;
        this.pledge = pledge;
        this.room_number = room_number;
    }



}
