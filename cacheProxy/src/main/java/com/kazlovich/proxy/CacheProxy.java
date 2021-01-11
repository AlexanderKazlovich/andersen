package com.kazlovich.proxy;

import com.kazlovich.Service;
import com.kazlovich.annotations.Cache;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class CacheProxy {

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
