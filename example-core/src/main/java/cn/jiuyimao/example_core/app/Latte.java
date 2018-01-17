package cn.jiuyimao.example_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    上午11:20
 * # 描述：织巢鸟科技
 */

public final class Latte {

    public static Configrator init(Context context){

        getConfigurations().put(ConfigTyep.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configrator.getInstince();
    }

    public static HashMap<String , Object> getConfigurations(){
        return Configrator.getInstince().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigTyep.APPLICATION_CONTEXT.name());
    }

}
