package cn.jiuyimao.example_core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/17    上午10:09
 * # 描述：织巢鸟科技
 */

public final class LoaderCreator {

    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();



    static AVLoadingIndicatorView create (String type , Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator (String name){
        if (name == null || name.isEmpty()){
            return null;
        }
        final StringBuffer drawableClassNmae = new StringBuffer();
        if (!name.contains(".")){
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassNmae.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassNmae.append(name);

        try {
            final Class<?> drawableClass = Class.forName(drawableClassNmae.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
