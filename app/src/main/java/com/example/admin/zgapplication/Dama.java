package com.example.admin.zgapplication;

/**
 * Created by fushuang on 2018/1/8.
 */

public class Dama {
//    private void showThreeTag(TakeLookListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
//        for (int i = 0; i < 3&&bean.getHouse_label()!=null&&bean.getHouse_label().size()>i; i++) {
//            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
//            childAt.setVisibility(View.VISIBLE);
//            childAt.setText(bean.getHouse_label().get(i));
//        }
//    }


    /**
     *
     Intent intent = new Intent(mActivity, ChatActivity.class);
     Bundle bundle = new Bundle();
     bundle.putString(EaseConstant.CHAT_HX_NAME,bean.getHx_username());
     bundle.putString(EaseConstant.NICK_NAME,bean.getUsername());
     bundle.putString(EaseConstant.HEADIMAGEURL,bean.getAvatar());
     bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
     intent.putExtras(bundle);
     startActivity(intent);
     */
    /**
     Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
     ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
     ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
     ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
     ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental()+"元/月");
     LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
     showThreeTag(bean, ll_tag_container);
     */
}
