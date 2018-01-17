package cn.jiuyimao.example_core.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import cn.jiuyimao.example_core.net.callback.IError;
import cn.jiuyimao.example_core.net.callback.IFailure;
import cn.jiuyimao.example_core.net.callback.IRequest;
import cn.jiuyimao.example_core.net.callback.ISuccess;
import cn.jiuyimao.example_core.net.callback.RequestCallBack;
import cn.jiuyimao.example_core.ui.LatteLoader;
import cn.jiuyimao.example_core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    下午4:19
 * # 描述：织巢鸟科技
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest    REQUST;
    private final ISuccess    SUCCESS;
    private final IError      ERROR;
    private final IFailure    FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context     CONTEXT;
    private final File FILE;


    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest requst,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context,
                      File file) {
        URL = url;
        PARAMS.putAll(params);
        REQUST = requst;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        BODY = body;
        LOADER_STYLE = loaderStyle;
        CONTEXT = context;
        FILE = file;
    }

    public static RestClientBuilder sBuilder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUST != null) {
            REQUST.onRequstStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:

                call = service.get(URL, PARAMS);
                break;
            case POST:

                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:

                call = service.postRaw(URL, BODY);
                break;
            case PUT:

                call = service.put(URL, PARAMS);
                break;

            case PUT_RAW:

                call = service.putRaw(URL, BODY);
                break;
            case DELETE:

                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:

                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody .Part body = MultipartBody.Part.createFormData("file",FILE.getName());
                call = service.upload(URL,body);
                break;

            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBack(SUCCESS, ERROR, FAILURE, REQUST, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
