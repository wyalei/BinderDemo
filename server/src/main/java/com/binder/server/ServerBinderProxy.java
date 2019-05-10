package com.binder.server;

/**
 * -------------------------------------
 * author      : wangyalei
 * time        : 19/5/10
 * description :
 * history     :
 * -------------------------------------
 */
public class ServerBinderProxy implements IServerBinder {

    private ServerBinder mBinder = new ServerBinder();

    @Override
    public int add(int a, int b) {
        return mBinder.add(a, b);
    }
}
