package cn.jiuyimao.example_core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import cn.jiuyimao.example_core.net.RestClient;
import cn.jiuyimao.example_core.net.RestCreator;
import cn.jiuyimao.example_core.net.callback.IError;
import cn.jiuyimao.example_core.net.callback.IFailure;
import cn.jiuyimao.example_core.net.callback.IRequest;
import cn.jiuyimao.example_core.net.callback.ISuccess;
import cn.jiuyimao.example_core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午5:16
 * # 描述：织巢鸟科技
 */

public class RxRestClientBuilder {

    private              String                      mUrl         = null;
    private static final WeakHashMap<String, Object> PARAMS       = RestCreator.getParams();
    private              RequestBody                 mBody        = null;
    private              LoaderStyle                 mLoaderStyle = null;
    private              Context                     mContext     = null;
    private File mFile = null;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);

        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader (Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder loader (Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody,mLoaderStyle,mContext,mFile);
    }

}
