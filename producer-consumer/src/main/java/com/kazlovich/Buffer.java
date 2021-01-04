package com.kazlovich;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Buffer<T> {
    private List<T> buffer = new ArrayList<>();
    private int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(T t){
        synchronized (this) {
            while (buffer.size() >= capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer.add(t);
            notify();
        }
    }
    public T consume() {
        synchronized (this) {
            while (buffer.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T value = buffer.remove(0);
            notify();
            return value;
        }
    }

}
