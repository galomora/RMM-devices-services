package com.ninjaone.backendinterviewproject.model;

import java.math.BigDecimal;

public class OrderTestFactory {

    private static OrderTestFactory instance;

    public static OrderTestFactory getInstance () {
        if (instance == null) { instance = new OrderTestFactory (); }
        return instance;
    }

    public TechServiceType createTechServiceTypeDeviceService() {
        return new TechServiceType("DEVICE SERVICE", "Device Service");

    }

    public TechService createServiceOfTypeDeviceService() {
        TechService service = new TechService(
                "Device Service", new BigDecimal(4),
                OperatingSystem.ANY, createTechServiceTypeDeviceService()
        );
        return service;
    }

    public TechServiceType createTechServiceTypeAntivirus() {
        return new TechServiceType("ANTIVIRUS", "Antivirus");

    }

    public TechService createServiceOfTypeAntivirus() {
        TechService service = new TechService(
                "Antivirus for Windows", new BigDecimal(5),
                OperatingSystem.WINDOWS, createTechServiceTypeAntivirus()
        );
        return service;
    }

    public Device createDeviceWin() {
        Device device = new Device("DEV1", "SYSDEV1WIN", "WINDOWS SERVER", OperatingSystem.WINDOWS);
        return device;
    }

    public Device createDeviceMac() {
        Device device = new Device("DEV2", "SYSDEV2MAC", "A MAC", OperatingSystem.MAC);
        return device;
    }

    public Order createOrder () {
        Order order = new Order();
        return order;
    }

    public ServiceOnDevice createServiceOfDeviceWinDeviceService() {
        ServiceOnDevice serviceOnDevice = new ServiceOnDevice(
                createServiceOfTypeDeviceService(), createDeviceWin()
        );
        return serviceOnDevice;
    }

    public ServiceOnDevice createServiceOfDeviceMacDeviceService() {
        ServiceOnDevice serviceOnDevice = new ServiceOnDevice(
                createServiceOfTypeDeviceService(), createDeviceMac()
        );
        return serviceOnDevice;
    }

    public ServiceOnDevice createServiceOfDeviceWinAntivirus() {
        ServiceOnDevice serviceOnDevice = new ServiceOnDevice(
                createServiceOfTypeAntivirus(), createDeviceWin()
        );
        return serviceOnDevice;
    }

    public ServiceOnDevice createServiceOfDeviceMacAntivirus() {
        ServiceOnDevice serviceOnDevice = new ServiceOnDevice(
                createServiceOfTypeAntivirus(), createDeviceMac()
        );
        return serviceOnDevice;
    }

    public Order createOrderWinWinMacServices () {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceOnDevice serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        return order;
    }

    public Order createOrderWinMacTwoServiceTypes () {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceOnDevice serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacAntivirus();
        order.addServiceOnDevice(serviceOnDevice);
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinAntivirus();
        order.addServiceOnDevice(serviceOnDevice);
        return order;
    }

}
