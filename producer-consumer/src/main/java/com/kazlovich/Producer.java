package com.kazlovich;

public class Producer implements Runnable{
    private Buffer<Integer> buffer;

    public Producer(Buffer<Integer> buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        int value = 0;
            while (true) {
                buffer.produce(value);
                System.out.println("Add value : " + value);
                value++;
            }
    }
}

