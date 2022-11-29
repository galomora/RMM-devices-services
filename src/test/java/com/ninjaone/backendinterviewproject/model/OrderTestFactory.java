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

    public ServiceForDevice createServiceOfDeviceWinDeviceService() {
        ServiceForDevice serviceForDevice = new ServiceForDevice(
                createServiceOfTypeDeviceService(), createDeviceWin()
        );
        return serviceForDevice;
    }

    public ServiceForDevice createServiceOfDeviceMacDeviceService() {
        ServiceForDevice serviceForDevice = new ServiceForDevice(
                createServiceOfTypeDeviceService(), createDeviceMac()
        );
        return serviceForDevice;
    }

    public ServiceForDevice createServiceOfDeviceWinAntivirus() {
        ServiceForDevice serviceForDevice = new ServiceForDevice(
                createServiceOfTypeAntivirus(), createDeviceWin()
        );
        return serviceForDevice;
    }

    public ServiceForDevice createServiceOfDeviceMacAntivirus() {
        ServiceForDevice serviceForDevice = new ServiceForDevice(
                createServiceOfTypeAntivirus(), createDeviceMac()
        );
        return serviceForDevice;
    }

    public Order createOrderWinWinMacServices () {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceForDevice serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceForDevice(serviceForDevice);
        serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceForDevice(serviceForDevice);
        serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacDeviceService();
        order.addServiceForDevice(serviceForDevice);
        return order;
    }

    public Order createOrderWinMacTwoServiceTypes () {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceForDevice serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceForDevice(serviceForDevice);
        serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacDeviceService();
        order.addServiceForDevice(serviceForDevice);
        serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceMacAntivirus();
        order.addServiceForDevice(serviceForDevice);
        serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinAntivirus();
        order.addServiceForDevice(serviceForDevice);
        return order;
    }

}
