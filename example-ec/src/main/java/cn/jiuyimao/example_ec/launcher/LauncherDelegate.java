package cn.jiuyimao.example_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jiuyimao.example.ec.R;
import cn.jiuyimao.example.ec.R2;
import cn.jiuyimao.example_core.delegates.LatterDelegate;
import cn.jiuyimao.example_core.util.timer.BaseTimerTask;
import cn.jiuyimao.example_core.util.timer.ITimerListener;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/22    下午2:39
 * # 描述：织巢鸟科技
 */

public class LauncherDelegate extends LatterDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView lancherTimerTv = null;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){

    }

    private Timer mTimer = null;
    private int mCount = 5;

    private void initTimer (){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (lancherTimerTv!=null){
                    lancherTimerTv.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer= null;
                        }
                    }
                }
            }
        });
    }
}
