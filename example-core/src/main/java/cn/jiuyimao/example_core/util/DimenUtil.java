package cn.jiuyimao.example_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import cn.jiuyimao.example_core.app.Latte;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/17    上午10:46
 * # 描述：计算工具类
 */

public class DimenUtil {

    //    获取屏幕宽度
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    //    获取屏幕宽度
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
