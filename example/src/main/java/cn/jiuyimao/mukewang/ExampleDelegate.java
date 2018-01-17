package cn.jiuyimao.mukewang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import cn.jiuyimao.example_core.delegates.LatterDelegate;
import cn.jiuyimao.example_core.net.RestClient;
import cn.jiuyimao.example_core.net.callback.IError;
import cn.jiuyimao.example_core.net.callback.IFailure;
import cn.jiuyimao.example_core.net.callback.ISuccess;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午3:51
 * # 描述：织巢鸟科技
 */

public class ExampleDelegate extends LatterDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        text();
    }


    private void text() {
        RestClient.sBuilder()
                .url("http://news.baidu11.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error(new IError() {
            @Override
            public void OnError(int code, String massage) {

            }
        }).build()
        .get();
    }


}
