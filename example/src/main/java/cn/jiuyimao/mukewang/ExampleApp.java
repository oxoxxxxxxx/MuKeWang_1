package cn.jiuyimao.mukewang;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jiuyimao.example_core.app.Latte;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午12:09
 * # 描述：织巢鸟科技
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}

