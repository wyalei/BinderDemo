package com.binder.client;

/**
 * -------------------------------------
 * author      : wangyalei
 * time        : 19/5/10
 * description :
 * history     :
 * -------------------------------------
 */
public class ClientBinderProxy implements IClientBinder {

    private ClientBinder mBinder = new ClientBinder();

    @Override
    public int plus(int a, int b) {
        return mBinder.plus(a, b);
    }
}
