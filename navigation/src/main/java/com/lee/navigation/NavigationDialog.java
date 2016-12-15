package com.lee.navigation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by android on 2016/12/14.
 */
public class NavigationDialog extends Dialog implements View.OnClickListener {

    Activity mActivity;

    TextView baidu;
    TextView gaode;
    TextView google;
    TextView cancel;


    private String startCity;
    private String endCity;

    private String endLat;
    private String endLng;

    private String startLat;
    private String startLng;


    public NavigationDialog(Context context) {
        super(context);
        mActivity = (Activity) context;
    }

    public NavigationDialog(Context context, int themeResId) {
        super(context, themeResId);
        mActivity = (Activity) context;
    }

    protected NavigationDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, true, cancelListener);
        mActivity = (Activity) context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view);
        findView();
        setListener4View();
        upDateUI();
        this.setCanceledOnTouchOutside(true);
    }

    private void setListener4View() {
        baidu.setOnClickListener(this);
        gaode.setOnClickListener(this);
        google.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void findView() {
        baidu = (TextView) findViewById(R.id.baidu);
        gaode = (TextView) findViewById(R.id.gaode);
        google = (TextView) findViewById(R.id.google);
        cancel = (TextView) findViewById(R.id.cancel);
    }


    public void setData(String startLat, String startLng, String endLat, String endLng, String startCity, String endCity) {
        this.startLat = startLat;
        this.startLng = startLng;

        this.endLat = endLat;
        this.endLng = endLng;
        this.startCity = startCity;
        this.endCity = endCity;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.baidu:
                baiduMap();
                dismiss();
                break;
            case R.id.gaode:
                gaodeMap();
                dismiss();
                break;
            case R.id.google:
                googleMap();
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }


    private void baiduMap() {

        Intent intent2 = null;
        try {
            intent2 = Intent.getIntent("intent://map/direction?destination=name:" + endCity + "|latlng:" + endLat + "," + endLng + "&mode=driving&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

      /*  Intent intent2 = null;
        try {
            intent2 = Intent.getIntent("intent://map/direction?origin="
                    + "name:天安门|latlng:39.98871,116.43234&destination=name: |latlng:" + lat + "," + lng + "&mode=driving&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

        //调起百度地图客户端
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:"
                    + endLat + "," + endLat
                    + "|name:我家&destination=大雁塔&mode=driving®ion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            mActivity.startActivity(intent2); //启动调用

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    private void gaodeMap() {
        Intent intent = new Intent("android.intent.action.VIEW",
                android.net.Uri.parse("androidamap://navi?sourceApplication=长隆旅游&lat=" + endLat + "&lon=" + endLng + "&dev=0&style=2"));
        intent.setPackage("com.autonavi.minimap");
        mActivity.startActivity(intent); //启动调用
    }


    private void googleMap() {
        //"http://www.google.cn/maps/search/40.64481947654957,117.26964957483519/data=!4m2!2m1!4b1?hl=zh&nogmmr=1"
        String url = "http://www.google.cn/maps/search/" + endLat + "," + endLng + "/data=!4m2!2m1!4b1?hl=zh&nogmmr=1";
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        getContext().startActivity(intent);
    }


    private void upDateUI() {

        if (isInstallByread("com.baidu.BaiduMap")) {
            baidu.setVisibility(View.VISIBLE);
        } else {
            baidu.setVisibility(View.GONE);
        }

        if (isInstallByread("com.autonavi.minimap")) {
            gaode.setVisibility(View.VISIBLE);
        } else {
            gaode.setVisibility(View.GONE);
        }

    }


    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

}
