package cn.jiuyimao.example_core.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import cn.jiuyimao.example_core.app.ConfigTyep;
import cn.jiuyimao.example_core.app.Latte;
import cn.jiuyimao.example_core.net.rx.RxRestService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午4:58
 * # 描述：织巢鸟科技
 */

public class RestCreator {

    public static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String   BASE_URL        = (String) Latte.getConfigurations().get(ConfigTyep.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OKHttpHolder {
        private static final int          TIME_OUT       = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
