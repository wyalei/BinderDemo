package com.binder.client;

/**
 * -------------------------------------
 * author      : wangyalei
 * time        : 19/5/10
 * description :
 * history     :
 * -------------------------------------
 */
public class ClientBinder implements IClientBinder {
    @Override
    public int plus(int a, int b) {
        return a * b;
    }
}
