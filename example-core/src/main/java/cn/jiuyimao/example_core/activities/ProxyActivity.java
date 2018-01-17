package cn.jiuyimao.example_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import cn.jiuyimao.example_core.R;
import cn.jiuyimao.example_core.delegates.LatterDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午2:51
 * # 描述：织巢鸟科技
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatterDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
