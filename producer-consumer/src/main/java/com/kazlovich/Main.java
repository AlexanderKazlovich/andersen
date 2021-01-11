package com.kazlovich;

public class Main {
    public static void main(String[] args) {
        Buffer<Integer> buffer = new Buffer<>(10);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        Thread prod = new Thread(producer);
        Thread cons = new Thread(consumer);
        prod.start();
        prod.start();
    }
}
