package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/29.
 */

public class PayInfoResponse {


    /**
     * msg : 操作成功
     * code : 0
     * data : {"id":"ch_yr9uvPznnTOOSePCaP88env1","object":"charge","created":1517039260,"livemode":false,"paid":false,"refunded":false,"reversed":false,"app":"app_yjvXH4WHy9KGvH4y","channel":"alipay","order_no":"life201801271509263696675014","client_ip":"127.0.0.1","amount":46000,"amount_settle":46000,"currency":"cny","subject":"扎根费用","body":"扎根租房费用相关","extra":[],"time_paid":null,"time_expire":1517125660,"time_settle":null,"transaction_no":null,"refunds":{"object":"list","url":"/v1/charges/ch_yr9uvPznnTOOSePCaP88env1/refunds","has_more":false,"data":[]},"amount_refunded":0,"failure_code":null,"failure_msg":null,"metadata":[],"credential":{"object":"credential","alipay":{"orderInfo":"_input_charset=\"utf-8\"&body=\"扎根租房费用相关\"&it_b_pay=\"2018-01-28 15:47:40\"&notify_url=\"https%3A%2F%2Fnotify.pingxx.com%2Fnotify%2Fcharges%2Fch_yr9uvPznnTOOSePCaP88env1\"&out_trade_no=\"life201801271509263696675014\"&partner=\"2008823541695218\"&payment_type=\"1\"&seller_id=\"2008823541695218\"&service=\"mobile.securitypay.pay\"&subject=\"扎根费用\"&total_fee=\"460.00\"&sign=\"dTVxdjlTS3V2ZmpURzhtUHVUS0tlSEtP\"&sign_type=\"RSA\""}},"description":"2"}
     */

    private String msg;
    private int code;
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
