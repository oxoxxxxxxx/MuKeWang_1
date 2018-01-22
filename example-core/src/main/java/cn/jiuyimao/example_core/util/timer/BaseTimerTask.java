package cn.jiuyimao.example_core.util.timer;

import java.util.TimerTask;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/22    下午2:42
 * # 描述：织巢鸟科技
 */

public class BaseTimerTask extends TimerTask {

    public ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
