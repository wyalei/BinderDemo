package com.binder.server;

/**
 * -------------------------------------
 * author      : wangyalei
 * time        : 19/5/10
 * description :
 * history     :
 * -------------------------------------
 */
public class ServerBinder implements IServerBinder {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
