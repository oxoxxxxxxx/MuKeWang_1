package cn.jiuyimao.example_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * # 作者：王宏伟
 * # 时间：2017/11/29    上午11:21
 * # 描述：织巢鸟科技
 */

public class Configrator {

    private static final HashMap<String , Object> LATTE_CONFIGS = new HashMap<>();

//    字体图标
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();


    private Configrator(){

        LATTE_CONFIGS.put(ConfigTyep.CONFIG_READY.name(),false);

    }

    public static Configrator getInstince(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    private static class Holder{
        private static final Configrator INSTANCE = new Configrator();

    }

    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigTyep.CONFIG_READY.name(),true);
    }

    public final Configrator withIcon (IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;

    }

    public final Configrator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigTyep.API_HOST.name(),host);
        return this;
    }

    private void initIcons (){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1 ; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }

    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigTyep.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready");
        }
    }

    @SuppressWarnings("unchecked")
    final  <T> T getConfiguration(Enum<ConfigTyep> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());

    }

}
