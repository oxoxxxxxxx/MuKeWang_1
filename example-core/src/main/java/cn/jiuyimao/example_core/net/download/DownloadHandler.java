package cn.jiuyimao.example_core.net.download;

import android.os.AsyncTask;
import android.support.v4.app.NavUtils;

import java.util.WeakHashMap;


import cn.jiuyimao.example_core.net.RestCreator;
import cn.jiuyimao.example_core.net.callback.IError;
import cn.jiuyimao.example_core.net.callback.IFailure;
import cn.jiuyimao.example_core.net.callback.IRequest;
import cn.jiuyimao.example_core.net.callback.ISuccess;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2018/1/21.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           IRequest requst,
                           ISuccess success,
                           IError error,
                           IFailure failure,
                           String download_dir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUST = requst;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handlerDownload(){

        if (REQUST != null){
            REQUST.onRequstStart();
        }

        RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUST,SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);
                    if (task.isCancelled()){
                        if (REQUST !=null){
                            REQUST.onRequstEnd();
                        }
                    }
                }else {
                    if (ERROR != null){
                        ERROR.OnError(response.code(),response.message());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null){
                    FAILURE.onFailure();
                }
            }
        });

    }
}
