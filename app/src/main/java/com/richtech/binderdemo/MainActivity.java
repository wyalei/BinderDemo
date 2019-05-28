package com.richtech.binderdemo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.binder.BinderManager;
import com.binder.server.IServerBinder;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String name = this.getClass().getName();
//        String canonicalName = this.getClass().getCanonicalName();
//        String simpleName = this.getClass().getSimpleName();
//        Log.d("binder2", "name: " + name);
//        Log.d("binder2", "canonicalName: " + canonicalName);
//        Log.d("binder2", "simpleName: " + simpleName);

        IServerBinder serverBinder = BinderManager.getInstance().bind(IServerBinder.class);
        Log.d("binder2", "simpleName: " + serverBinder.add(100, 2));

        mInfoTv = findViewById(R.id.info_tv);
        mInfoTv.setText(String.valueOf(getVersionCode()));
    }

    private int getVersionCode(){
        int code = 1;
        try {
            code = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        }catch (PackageManager.NameNotFoundException ex){
            ex.printStackTrace();
        }

        return code;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BinderManager.getInstance().unbind(IServerBinder.class);
    }
}
