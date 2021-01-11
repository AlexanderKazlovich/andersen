package com.kazlovich;

import com.kazlovich.domain.User;
import com.kazlovich.proxy.CacheProxy;
import com.kazlovich.proxy.ServiceInterceptor;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceImpl());
        User user = service.doHardWorkElse("kolya");
        User user1 = service.doHardWork("sasha");
        User user2 = service.doHardWorkElse("petya");
        User user3 = service.doHardWork("vasya");



    }
}
