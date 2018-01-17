package cn.jiuyimao.example_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.jiuyimao.example_core.R;
import cn.jiuyimao.example_core.util.DimenUtil;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/17    上午10:31
 * # 描述：织巢鸟科技
 */

public class LatteLoader {

    private static final int LOADER_SEZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 20;

    private static final ArrayList <AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading (Context context ,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context, String type){
        final AppCompatDialog dialog = new AppCompatDialog(context,R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SEZE_SCALE;
            lp.height = deviceHeight/LOADER_SEZE_SCALE;
            lp.height = lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        dialog.setCanceledOnTouchOutside(false);
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context ){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
