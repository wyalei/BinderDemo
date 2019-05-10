package com.richtech.binderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.binder.BinderManager;
import com.binder.server.IServerBinder;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BinderManager.getInstance().unbind(IServerBinder.class);
    }
}
