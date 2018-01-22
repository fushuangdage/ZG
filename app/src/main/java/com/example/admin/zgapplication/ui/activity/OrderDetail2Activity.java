package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.view.PriceDetailDialog;

import butterknife.OnClick;

public class OrderDetail2Activity extends BaseActivity {
    private int order_id;

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    private CommonAdapter<String> adapter;

    @OnClick({R.id.tv_cancel,R.id.tv_price_detail,R.id.tv_pay,R.id.tv_friend_pay, R.id.iv_left})
  public void onClick(View view){
      switch (view.getId()) {

          case R.id.tv_cancel:
              Intent intent = new Intent();
              intent.putExtra("canclePay",true);
              setResult(1,intent);
              finish();
              break;
          case R.id.tv_price_detail:
              PriceDetailDialog dialog = new PriceDetailDialog(this);
              dialog.show();
              break;
          case R.id.tv_friend_pay:
              break;
          case R.id.tv_pay:
              //// TODO: 2017/10/20  调用支付宝支付
              startActivity(FinishOrderActivity.class);
              break;

          case R.id.iv_left:
              finish();
              break;

      }
  }

    @Override
    public int setLayout() {
        return R.layout.activity_order_detail2;
    }

    @Override
    public void initEvent() {
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            strings.add("");
//        }
        
//        if (strings.size()<=3){
//            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
//            params.height= LinearLayout.LayoutParams.WRAP_CONTENT;
//            recyclerView.setLayoutParams(params);
//        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new CommonAdapter<String>(this, R.layout.item_price_already_pay, strings) {
//            @Override
//            protected void convert(ViewHolder holder, String o, int position) {
//
//            }
//        };
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        order_id = getIntent().getIntExtra("order_id", 0);
    }


}
