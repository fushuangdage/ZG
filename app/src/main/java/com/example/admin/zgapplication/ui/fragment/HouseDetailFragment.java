package com.example.admin.zgapplication.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SelectOrderInfoBean;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.ChooseAgentActivity;
import com.example.admin.zgapplication.ui.activity.CompanyDetailActivity;
import com.example.admin.zgapplication.ui.activity.HouseDetailActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.RoomPickDialog;
import com.example.admin.zgapplication.utils.img.BannerGlideLoader;
import com.example.admin.zgapplication.utils.system.ScreenUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HouseDetailFragment extends BaseSupportFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.house_title)
    TextView house_title;
    @BindView(R.id.house_info)
    TextView house_info;
    @BindView(R.id.rent_money)
    TextView rent_money;
    @BindView(R.id.rent_info)
    TextView rent_info;
    @BindView(R.id.ll_tag_container)
    LinearLayout ll_tag_container;
    @BindView(R.id.tv_seven_follow)
    TextView tv_seven_follow;
    @BindView(R.id.tv_total_follow)
    TextView tv_total_follow;
    @BindView(R.id.tv_add_shoppingcar)
    TextView tv_add_shoppingcar;
    @BindView(R.id.house_code)
    TextView house_code;
    @BindView(R.id.house_type)
    TextView house_type;
    @BindView(R.id.space_area)
    TextView space_area;
    @BindView(R.id.floor)
    TextView floor;

    @BindView(R.id.tv_public_config)
    TextView tv_public_config;
    @BindView(R.id.ll_public_config)
    LinearLayout ll_public_config;

    @BindView(R.id.ll_house_config)
    LinearLayout ll_house_config;

    @BindView(R.id.tv_house_desc)
    TextView tv_house_desc;

    @BindView(R.id.tv_location)
    TextView tv_location;

    @BindView(R.id.iv_company_icon)
    ImageView iv_company_icon;

    @BindView(R.id.tv_company_name)
    TextView tv_company_name;

    @BindView(R.id.tv_evaluation)
    TextView tv_evaluation;
    @BindView(R.id.tv_onrent)
    TextView tv_onrent;
    @BindView(R.id.tv_count_order)
    TextView tv_count_order;
    @BindView(R.id.tv_agent_sum)
    TextView tv_agent_sun;

    @BindView(R.id.recyclerView)
    RecyclerView sameRecyclerView;

    @BindView(R.id.tv_pick_room_info)
    TextView tv_pick_room_info;


    public List<RoomDetailResponse.DataBean.SameBean> sameList=new ArrayList<>();

    private Integer[] roomIconOnList={R.drawable.chuang_on,R.drawable.wifi_on,R.drawable.kongtiao_on,
            R.drawable.shuzhuo_on,R.drawable.weishengjian_on,R.drawable.yigui_on,R.drawable.shafa_on,R.drawable.dianshi_on,
            R.drawable.xiyiji_on,R.drawable.bingxiang_on,R.drawable.reshuiqi_on,R.drawable.diancilu_on,R.drawable.weibolu_on,R.drawable.ranqizao_on,R.drawable.youyanji_on,R.drawable.yangtai_on};
    
    private Integer[] roomIconOffList={R.drawable.chuang_off,R.drawable.wifi_off,R.drawable.kongtiao_off,
            R.drawable.shuzhuo_off,R.drawable.weishengjian_off,R.drawable.yigui_off,R.drawable.shafa_off,R.drawable.dianshi_off,
            R.drawable.xiyiji_off,R.drawable.bingxiang_off,R.drawable.reshuiqi_off,R.drawable.diancilu_off,R.drawable.weibolu_off,R.drawable.ranqizao_off,R.drawable.youyanji_off,R.drawable.yangtai_off};
    private CommonAdapter<RoomDetailResponse.DataBean.SameBean> sameAdapter;
    public RoomDetailResponse.DataBean data;


    @Override
    protected int setLayout() {
        return R.layout.fragment_house_detail;
    }

    @Override
    protected void init() {
        Intent intent = getActivity().getIntent();
        String house_id = intent.getStringExtra("house_id");
        String type = intent.getStringExtra("type");
        String room_id = intent.getStringExtra("room_id");
        sameRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
        sameAdapter = new CommonAdapter<RoomDetailResponse.DataBean.SameBean>(mActivity, R.layout.item_same_house_recommend, sameList) {

            @Override
            protected void convert(ViewHolder holder, RoomDetailResponse.DataBean.SameBean sameBean, int position) {
                Glide.with(mActivity).load(sameBean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_info)).setText(sameBean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_rent)).setText(sameBean.getRental()+"元/月");
            }
        };
        sameAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                RoomDetailResponse.DataBean.SameBean sameBean = sameList.get(position);
                Intent intent = new Intent(mActivity, HouseDetailActivity.class);
                intent.putExtra("house_id",sameBean.getHouse_id());
                intent.putExtra("room_id",sameBean.getRoom_id());
                intent.putExtra("type",sameBean.getType());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        sameRecyclerView.setAdapter(sameAdapter);

        RetrofitHelper.getApiWithUid().getRoomDetail(type, house_id, room_id)
                .compose(RxScheduler.<RoomDetailResponse>defaultScheduler())
                .subscribe(new BaseObserver<RoomDetailResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RoomDetailResponse roomDetailResponse) {
                        data = roomDetailResponse.getData();
                        int totalWidth = ScreenUtil.getScreenWidth(mActivity)-ScreenUtil.dp2px(mActivity,20);

                        List<String> rooms = data.getRoom();
                        for (int i = 0; i < ll_house_config.getChildCount(); i++) {

                            ViewGroup childAt = (ViewGroup) ll_house_config.getChildAt(i);
                            int itemWidth = totalWidth / 6;
                            int line = (i+1) / 6;
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                            layoutParams.width=itemWidth;
                            layoutParams.setMargins((i+1)>1&&(i+1)%6==1?-totalWidth:0,i/6*layoutParams.height,0,0);
                            childAt.setLayoutParams(layoutParams);
                            String s = rooms.get(i);
                            ImageView childIv = (ImageView) childAt.getChildAt(0);
                            TextView childTv = (TextView) childAt.getChildAt(1);
                            if (s.equals("1")) {
                                childIv.setBackgroundResource(roomIconOnList[i]);
                                childTv.setTextColor(getResources().getColor(R.color.black_deep));
                            }else {
                                childIv.setBackgroundResource(roomIconOffList[i]);
                                childTv.setTextColor(getResources().getColor(R.color.gray666));
                            }
                        }

                        if (data.getType().equals("1")) {
                            //集中式


                        }else {
                            //分散式
                            tv_public_config.setVisibility(View.GONE);
                            ll_public_config.setVisibility(View.GONE);
                        }

                        banner.setImages(data.getHouse_photo());
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                        house_title.setText(data.getHouse_title());
                        house_info.setText(data.getHouse_kind());
                        rent_money.setText(data.getRental());
                        rent_info.setText(String.format("押金%s元、服务费%s、中介费%s", data.getRental(), data.getService_charge(), data.getIntermediary_fee()));
                        showThreeTag(data.getLabels(),ll_tag_container);
                        tv_seven_follow.setText(data.getSeven());
                        tv_total_follow.setText(data.getAll());
                        tv_add_shoppingcar.setText(data.getCollect());

                        house_code.setText(data.getHouse_code());
                        house_type.setText(data.getType());
                        space_area.setText(data.getSpace_area());
                        floor.setText(data.getFloor());
                        List<String> room = data.getRoom();

                        for (int i = 0; i < ll_house_config.getChildCount(); i++) {

                            ViewGroup childAt = (ViewGroup) ll_house_config.getChildAt(i);
                            String s = room.get(i);
                            ImageView childIv = (ImageView) childAt.getChildAt(0);
                            TextView childTv = (TextView) childAt.getChildAt(1);
                            if (s.equals("1")) {
                                childIv.setBackgroundResource(roomIconOnList[i]);
                                childTv.setTextColor(getResources().getColor(R.color.black_deep));
                            }else {
                                childIv.setBackgroundResource(roomIconOffList[i]);
                                childTv.setTextColor(getResources().getColor(R.color.gray666));
                            }
                        }

                        tv_house_desc.setText(data.getHouse_desc());
                        tv_location.setText(data.getHouse_address());
                        Glide.with(mActivity).load(data.getLogo()).into(iv_company_icon);
                        tv_company_name.setText(data.getCompany_name());

                        tv_evaluation.setText(data.getAgent_sum());
                        tv_onrent.setText(data.getOn_rent()+"");
                        tv_agent_sun.setText(data.getAgent_sum());
                        tv_count_order.setText(data.getCount_order());
                        sameList.clear();
                        sameList.addAll(data.getSame());
                        sameAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void complete() {

                    }
                });

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

    }

    public RoomDetailResponse.DataBean getData() {
        return data;
    }

    @OnClick({R.id.ll_choose_room, R.id.ll_goto_company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choose_room:
                RoomPickDialog roomPickDialog = new RoomPickDialog(getActivity(),R.style.room_pick_dialog);
                roomPickDialog.setResultCallBack(new RoomPickDialog.ResultCallBack() {
                    @Override
                    public void resultCallBack(SelectOrderInfoBean bean) {
                        ((HouseDetailActivity) getActivity()).setRoomPickInfo(bean);
                        tv_pick_room_info.setText(String.format("已选    :押%d付%d,%s",bean.pledge,bean.pay,bean.room_number));
                    }
                });
                roomPickDialog.create();
                roomPickDialog.setData(data);
                roomPickDialog.show();
                break;
            case R.id.ll_goto_company:
                Intent intent = new Intent(mActivity, CompanyDetailActivity.class);
                intent.putExtra("company_id",Integer.parseInt(data.getCompany_id()));
                startActivity(intent);
                break;

        }
    }

    private void showThreeTag(List<String> list, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && list != null && list.size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(list.get(i));
        }
    }
}
