package cn.jiuyimao.example_core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtils {


    public static final String TOKEN = "TOKEN";//TOKEN
    public static final String Boot = "Boot";//是否第一次打开 是 需要引导页不是  跳过
    public static final String USERID = "USERID";//用户手机号
    public static final String RCODE = "RCODE";//注册时发送的验证码
    public static final String UCODE = "UCODE";//修改密码时发送的验证码
    public static final String ROYZ = "ORYZ";//倒计时 如果没到  关闭重新打开页面  重新倒计时
    public static final String USERIMG = "USERIMG";//用户头像的URL

    private SharedPreferences sp;
    private Editor editor;
    private static SharedPreferencesUtils spUtil;

    private SharedPreferencesUtils(Context context) {

        if (sp == null) {
            sp = context.getSharedPreferences("info", context.MODE_PRIVATE);
            editor = sp.edit();
        }
    }

    /**
     * 公有的得到实例的方法
     */
    public static SharedPreferencesUtils getInstance(Context context) {
        if (spUtil == null)
            spUtil = new SharedPreferencesUtils(context);
        return spUtil;
    }


    /**
     * 存储String类型的数值
     */
    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 取出String类型的数据
     */
    public String getString(String key) {
        return sp.getString(key, null);
    }

    /**
     * 取出boolean类型的数值
     */
    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 存储boolean类型的数值
     */
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
