package com.galo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {

    private static Map<String, String> map;

    public void init () {
        map = new ConcurrentHashMap<>();

    }

    public void  updateData () {
        map.clear();
        map.put("uno", "uno");
        map.put("dos", "dos");
        map.put("tres", "tres");
        map.put("cuatro", "cuatro");
    }
}
