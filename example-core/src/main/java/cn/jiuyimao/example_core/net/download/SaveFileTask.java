package cn.jiuyimao.example_core.net.download;

import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import cn.jiuyimao.example_core.net.callback.IError;
import cn.jiuyimao.example_core.net.callback.IFailure;
import cn.jiuyimao.example_core.net.callback.IRequest;
import cn.jiuyimao.example_core.net.callback.ISuccess;
import cn.jiuyimao.example_core.util.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/1/21.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest iRequest;
    private final ISuccess iSuccess;


    public SaveFileTask(IRequest iRequest, ISuccess iSuccess) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir .equals("")){
            downloadDir = "dowo_loads";
        }
        if (extension == null|| extension.equals("")){
            extension = "";
        }

        if (name == null || name .equals("")){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (iSuccess !=null){
            iSuccess.onSuccess(file.getPath());
        }
        if (iRequest !=null){
            iRequest.onRequstEnd();
        }
    }
}
