package com.kazlovich.proxy;


import com.kazlovich.Service;
import com.kazlovich.annotations.Cache;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy {
    private File file;
    private Map<String, Object> jvmMemory;

    public CacheProxy(File file) {
        this.file = file;
        jvmMemory = new HashMap<>();
    }
    public CacheProxy(){
        jvmMemory = new HashMap<>();
    }

    public Service cache(Service service){
        try {
            return new ByteBuddy()
                    .subclass(service.getClass())
                    .method(ElementMatchers.isAnnotatedWith(Cache.class))
                    .intercept(MethodDelegation.to(new ServiceInterceptor()))
                    .make()
                    .load(this.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
