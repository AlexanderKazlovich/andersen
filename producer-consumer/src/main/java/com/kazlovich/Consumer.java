package com.kazlovich;

public class Consumer implements Runnable{

    private Buffer<Integer> buffer;

    public Consumer(Buffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Get value: " + buffer.consume());
        }
    }
}
