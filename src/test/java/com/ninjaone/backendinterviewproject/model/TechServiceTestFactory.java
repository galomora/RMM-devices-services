package com.ninjaone.backendinterviewproject.model;

import java.util.HashMap;
import java.util.Map;

public class TechServiceTestFactory {

    private static TechServiceTestFactory instance;

    public static TechServiceTestFactory getInstance () {
        if (instance == null) { instance = new TechServiceTestFactory (); }
        return instance;
    }

    public Map<String, TechService> createTechServiceMap () {
        Map<String, TechService> map = new HashMap<>();
        TechService service = OrderTestFactory.getInstance().createServiceOfTypeDeviceService();
        TechService service2 = OrderTestFactory.getInstance().createServiceOfTypeAntivirus();
        map.put(service.getName(), service);
        map.put(service2.getName(), service2);
        return map;
    }

    public Map<String, TechServiceType> createTechServiceTypesMap () {
        Map<String, TechServiceType> map = new HashMap<>();
        TechServiceType type1 = OrderTestFactory.getInstance().createTechServiceTypeDeviceService();
        TechServiceType type2 = OrderTestFactory.getInstance().createTechServiceTypeAntivirus();
        map.put(type1.getName(), type1);
        map.put(type2.getName(), type2);
        return map;
    }
}
