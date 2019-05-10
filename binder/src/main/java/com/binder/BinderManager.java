package com.binder;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * -------------------------------------
 * author      : wangyalei
 * time        : 19/5/10
 * description :
 * history     :
 * -------------------------------------
 */
public class BinderManager {

    public static final String TAG = "binder2";

    private static BinderManager mInstance;
    private HashMap<String, IBinder> mBinderMap = new HashMap<>();

    private BinderManager() {
    }

    public static BinderManager getInstance(){
        if(mInstance == null){
            synchronized (BinderManager.class){
                if(mInstance == null){
                    mInstance = new BinderManager();
                }
            }
        }
        return mInstance;
    }

    public <T extends IBinder> T bind(Class iBinderClazz){
        if(iBinderClazz == null){
            return null;
        }
        String iBinderPath = iBinderClazz.getName();
        String binderPath = getBinderProxyPath(iBinderPath);
        if(binderPath == null){
            return null;
        }

        T binderInstance = (T)mBinderMap.get(binderPath);
        if(binderInstance != null){
            return binderInstance;
        }

        try{
            Class binderClazz = Class.forName(binderPath);
            binderInstance = (T)binderClazz.newInstance();
            mBinderMap.put(binderPath, binderInstance);
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (InstantiationException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }

        return binderInstance;
    }

    public void unbind(Class iBinderClazz){
        String iBinderPath = iBinderClazz.getName();
        String binderPath = getBinderProxyPath(iBinderPath);
        if(binderPath == null){
            return;
        }

        mBinderMap.remove(binderPath);
    }

    private String getBinderProxyPath(String iBinderPath){
        int lastDotIndex = iBinderPath.lastIndexOf(".");
        String packagePath = iBinderPath.substring(0, lastDotIndex);
        String iBinderName = iBinderPath.substring(lastDotIndex + 1);

        if(!TextUtils.equals(iBinderName.substring(0, 1), "I")){
//            throw new RuntimeException("binder 命名不规范");
            Log.e(TAG, "binder 命名不规范");
            return null;
        }

        String binderName = iBinderName.substring(1);
        String binderPath = packagePath + "." + binderName + "Proxy";
        return binderPath;
    }
}
