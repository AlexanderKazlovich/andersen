package com.kazlovich.proxy;

import com.kazlovich.annotations.Cache;
import com.kazlovich.domain.User;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ServiceInterceptor {

    Map<String, Object> jvmMemory;
    File file;

    public ServiceInterceptor() {
        this.jvmMemory = new HashMap<>();
    }

    public User intercept(@AllArguments Object[] args,
                          @Origin Method method) throws IOException {
        Cache cache = method.getAnnotation(Cache.class);
        String name = (String) args[0];
        if (cache.type().equals(CacheType.jvmMemory)) {
            User user = (User) jvmMemory.get(name);
            if (user == null){
                User newUser = new User(name);
                jvmMemory.put(name, newUser);
                return newUser;
            }
        } else {
            file = new File("cacheProxy/src/main/java/com/kazlovich/cache/cache");
            User user = readUser(name);
            if (user == null) {
                return writeUser(name);
            }
        }
        return null;
    }

    private User writeUser(String name) throws IOException {
        List<String> lines = Arrays.asList(name);
        Files.write(file.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        return new User(name);
    }

    private User readUser(String name) throws IOException {
        Optional<User> user = Files.lines(Paths.get(file.toURI()))
                        .filter(line -> line.equals(name))
                        .limit(1)
                        .map(line -> new User(name))
                        .findFirst();
        return user.isPresent() ? user.get() : null;
    }
}
