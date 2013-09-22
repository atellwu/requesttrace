package com.dianping.requestcontext;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalContext {

    private static ThreadLocal<Map<String, Object>> map            = new ThreadLocal<Map<String, Object>>() {
                                                                       protected synchronized Map<String, Object> initialValue() {
                                                                           return new HashMap<String, Object>();
                                                                       }
                                                                   };

    private static ThreadLocal<Map<String, Object>> inheritableMap = new InheritableThreadLocal<Map<String, Object>>() {
                                                                       protected synchronized Map<String, Object> initialValue() {
                                                                           return new HashMap<String, Object>();
                                                                       }
                                                                   };

    public static Object get(String key) {
        return get(key, false);
    }

    public static void set(String key, Object value) {
        set(key, value, false);
    }

    public static Object get(String key, boolean inheritable) {
        if (inheritable) {
            return inheritableMap.get().get(key);
        } else {
            return map.get().get(key);
        }
    }

    public static void set(String key, Object value, boolean inheritable) {
        if (inheritable) {
            inheritableMap.get().put(key, value);
        } else {
            map.get().put(key, value);
        }
    }

}
