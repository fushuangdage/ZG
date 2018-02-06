package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SelectOrderInfoBean;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.fragment.HouseDetailFragment;
import com.example.admin.zgapplication.ui.fragment.HouseEvaluteFragment;
import com.example.admin.zgapplication.ui.view.RoomPickDialog;
import com.hyphenate.easeui.EaseConstant;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;

public class HouseDetailActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.iv_shoppingcar)
    ImageView iv_shoppingcar;

    private HouseEvaluteFragment evaluteFragment;
    private HouseDetailFragment detailFragment;
    private String house_id;
    private String type;
    private String room_id="0";
    private SelectOrderInfoBean roomPickInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_house_detail;
    }

    @Override
    public void initEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "详情":
                        getSupportFragmentManager().beginTransaction().hide(evaluteFragment)
                                .show(detailFragment).commit();
                        break;

                    case "评价":
                        getSupportFragmentManager().beginTransaction().hide(detailFragment)
                                .show(evaluteFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        evaluteFragment = new HouseEvaluteFragment();
        detailFragment = new HouseDetailFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content,detailFragment).add(R.id.fl_content,evaluteFragment)
                .hide(evaluteFragment).commit();
    }

    @Override
    public void initData() {
        house_id = getIntent().getStringExtra("house_id");
        type = getIntent().getStringExtra("type");
        room_id = getIntent().getStringExtra("room_id");
    }

    @OnClick({R.id.tv_write_order,R.id.tv_chat_agent,R.id.tv_collect,R.id.iv_shoppingcar,R.id.iv_share})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_share:
                String imageUrl =detailFragment.data.getShare();
                UMImage image = new UMImage(HouseDetailActivity.this, detailFragment.data.getHouse_photo().get(0));
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
                image.compressFormat = Bitmap.CompressFormat.PNG;//网络图片
                UMWeb  web = new UMWeb(imageUrl);

                web.setTitle(detailFragment.data.getHouse_title());//标题
                web.setThumb(image);  //缩略图
                web.setDescription(detailFragment.data.getHouse_desc()+"|"+detailFragment.data.getRental());//描述
                new ShareAction(this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.ALIPAY)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .open();

                break;

            case R.id.tv_write_order:

                if ("".equals(Constant.uid)){
                    startActivity(LoginActivity.class);
                    break;
                }

                if (roomPickInfo==null){
                    RoomDetailResponse.DataBean data = detailFragment.getData();

                    RoomPickDialog roomPickDialog = new RoomPickDialog(this,R.style.room_pick_dialog);
                    roomPickDialog.setResultCallBack(new RoomPickDialog.ResultCallBack() {
                        @Override
                        public void resultCallBack(SelectOrderInfoBean bean) {
                            roomPickInfo=bean;
                            startChooseAgent();
                        }
                    });
                    roomPickDialog.create();
                    roomPickDialog.setData(data);
                    roomPickDialog.show();
                }else {
                    startChooseAgent();
                }

                break;

            case R.id.iv_left:
                finish();
                break;

            case R.id.tv_chat_agent:
                Bundle bundle = new Bundle();
                bundle.putString("from","HouseDetailActivity");
                bundle.putString("house_id",house_id);
                bundle.putString("type",type);
                bundle.putString("room_id",room_id);

                bundle.putString(EaseConstant.HOUSE_TITLE,detailFragment.data.getHouse_title());
                bundle.putString(EaseConstant.HOUSE_PRISE,detailFragment.data.getRental());
                bundle.putString(EaseConstant.HOUSE_IMG,detailFragment.data.getHouse_photo().get(0));
                startActivity(ChooseAgentActivity.class,bundle);
                break;

            case R.id.tv_collect:
            case R.id.iv_shoppingcar:
                RetrofitHelper.getApi().addCollection(Constant.uid,house_id,room_id==null?"0":room_id,type)
                        .compose(RxScheduler.<BaseResponse>defaultScheduler())
                        .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                            @Override
                            public void error(Throwable e) {

                            }
                            @Override
                            public void next(BaseResponse baseResponse) {
                                Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                if (baseResponse.getCode()==0) {
                                    Drawable drawable = getResources().getDrawable(R.drawable.shoucang_select);
                                    drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                                    tv_collect.setCompoundDrawables(null,drawable,null,null);
                                    iv_shoppingcar.setBackgroundResource(R.drawable.shoucang_select);
                                }
                            }

                            @Override
                            public void complete() {

                            }
                        });
                break;

        }
    }

    private void startChooseAgent() {
        Intent intent = new Intent(mActivity, ChooseAgentActivity.class);
        intent.putExtra("bean",roomPickInfo);
        intent.putExtra("house_id",roomPickInfo.house_id);
        intent.putExtra("room_id",roomPickInfo.room_id);
        intent.putExtra("type",roomPickInfo.type);
        startActivity(intent);
    }

    public void setRoomPickInfo(SelectOrderInfoBean roomPickInfo) {
        this.roomPickInfo = roomPickInfo;
    }
}
