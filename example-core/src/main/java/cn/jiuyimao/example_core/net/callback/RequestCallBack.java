package cn.jiuyimao.example_core.net.callback;

import cn.jiuyimao.example_core.ui.LatteLoader;
import cn.jiuyimao.example_core.ui.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * # 作者：王宏伟
 * # 时间：2018/1/16    下午5:37
 * # 描述：织巢鸟科技
 */

public class RequestCallBack implements Callback<String> {
    private final ISuccess SUCCESS;
    private final IError   ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUST;
    private final LoaderStyle LOADER_STYPE;


    public RequestCallBack(ISuccess success, IError error, IFailure failure, IRequest request,LoaderStyle style) {
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        REQUST = request;
        LOADER_STYPE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.OnError(response.code(), response.message());
            }
        }
      stopLoading();

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUST != null){
            REQUST.onRequstEnd();
        }
        stopLoading();
    }

    private void stopLoading (){
        if (LOADER_STYPE != null){
            LatteLoader.stopLoading();
        }
    }
}
