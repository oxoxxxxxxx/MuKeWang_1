package cn.jiuyimao.mukewang;


import cn.jiuyimao.example_core.activities.ProxyActivity;
import cn.jiuyimao.example_core.delegates.LatterDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatterDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
