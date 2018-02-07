package com.example.admin.zgapplication.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.admin.zgapplication.Constant;

/**
 * Created by fushuang on 2017/12/20.
 */

public class ZgUtil {

    public static String uriToUrl(Uri uri, Activity activity) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, proj, null,
                null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public static String getHelloText(String info){
        return "我是租客"+ Constant.username+",我要租 "+info+" ,请问有可推荐的房源么?";
    }

}
