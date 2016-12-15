package com.lee.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    NavigationDialog navigationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDialog = new NavigationDialog(this, R.style.NDialog);
        navigationDialog.setData("39.988717778778", "116.43234999998", "34.264642646862", "108.95108518068", "北京", "西安");
    }

    public void navigation(View view) {


        navigationDialog.show();


//        Intent intent;
//
//        //调起百度地图客户端
//        try {
//            intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//            if (isInstallByread("com.baidu.BaiduMap")) {
//                startActivity(intent); //启动调用
//                Log.e("GasStation", "百度地图客户端已经安装");
//            } else {
//                Log.e("GasStation", "没有安装百度地图客户端");
//            }
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
    }


    public void navigation2(View view) {


        Intent intent = new Intent("android.intent.action.VIEW",
                android.net.Uri.parse("androidamap://navi?sourceApplication=税源地图&lat=" + "34.264642646862" + "&lon=" + "108.95108518068" + "&dev=0&style=2"));
        intent.setPackage("com.autonavi.minimap");

        if (isInstallByread("com.autonavi.minimap")) {
            startActivity(intent); //启动调用
        } else {

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
