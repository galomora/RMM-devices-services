package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.controller.request.ServiceForDeviceRequest;
import com.ninjaone.backendinterviewproject.model.ServiceForDevice;
import com.ninjaone.backendinterviewproject.service.OrderService;
import com.ninjaone.backendinterviewproject.service.ServiceForDeviceService;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/service-for-device")
public class ServiceForDeviceController {
    private OrderService orderService;
    private ServiceForDeviceService serviceForDeviceService;

    @Autowired
    public ServiceForDeviceController(OrderService orderService, ServiceForDeviceService serviceForDeviceService) {
        this.orderService = orderService;
        this.serviceForDeviceService = serviceForDeviceService;
    }

    @RequestMapping (
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceForDevice createServiceForDevice(@RequestBody @Validated ServiceForDeviceRequest request) {
        try {
            return this.orderService.addServiceForDeviceToOrder(request.getOrderId(),
                    request.getDeviceId(), request.getServiceName());
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ServiceForDevice getService (@PathVariable Long id) {
        return this.serviceForDeviceService.getServiceForDevice(id).orElseThrow();
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService (@PathVariable Long id) {
        this.serviceForDeviceService.deleteServiceForDevice(id);
    }

}
