package com.kazlovich;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

public class CustomWeakHashMap <K, V> {

    ReferenceQueue<V> queue = null;
    Map<K,WeakReference<V>> values = null;
    Map<WeakReference<V>,K> keys = null;
    Thread cleanup = null;

    CustomWeakHashMap () {
            queue  = new ReferenceQueue<V>();
            keys   = Collections.synchronizedMap (new HashMap<WeakReference<V>,K>());
            values = Collections.synchronizedMap (new HashMap<K,WeakReference<V>>());
            cleanup = new Thread() {
                public void run() {
                    try {
                        for (;;) {
                            WeakReference<V> ref = (WeakReference<V>)queue.remove();
                            K key = keys.get(ref);
                            keys.remove(ref);
                            values.remove(key);
                        }
                    }
                    catch (InterruptedException e) {}
                }
            };
            cleanup.start();
        }

        void stop () {
            cleanup.interrupt();
        }

        V get (K key) {
            return values.get(key).get();
        }

        void put (K key, V value) {
            WeakReference<V> ref = new WeakReference<V>(value, queue);
            keys.put (ref, key);
            values.put (key, ref);
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append ("{");
            boolean first = true;
            for (Map.Entry<K,WeakReference<V>> entry : values.entrySet()) {
                if (first)
                    first = false;
                else
                    str.append (", ");
                str.append (entry.getKey());
                str.append (": ");
                str.append (entry.getValue().get());
            }
            str.append ("}");
            return str.toString();
        }

        public void gc (int loop, int delay) throws Exception
        {
            for (int n = loop; n > 0; n--) {
                Thread.sleep(delay);
                System.gc();
            }
        }

}
