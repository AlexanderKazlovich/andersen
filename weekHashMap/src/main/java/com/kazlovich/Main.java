package com.kazlovich;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomWeakHashMap<String, List> customWeakHashMap = new CustomWeakHashMap<>();

        List first = Arrays.asList(new Object[]{1,3,5});
        List second = Arrays.asList(new Object[]{2,4,6});


        customWeakHashMap.put ("first", first);
        customWeakHashMap.put ("second", second);


        System.out.println (customWeakHashMap);
        first = null;

        customWeakHashMap.gc(10, 10);

        System.out.println (customWeakHashMap);

        customWeakHashMap.stop();

    }
}
