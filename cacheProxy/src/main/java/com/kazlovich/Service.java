package com.kazlovich;

import com.kazlovich.domain.User;

public interface Service {
    User doHardWork(String name);
    User doHardWorkElse(String name);
}
