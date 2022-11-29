package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.controller.request.ServiceOnDeviceRequest;
import com.ninjaone.backendinterviewproject.model.ServiceOnDevice;
import com.ninjaone.backendinterviewproject.service.OrderService;
import com.ninjaone.backendinterviewproject.service.ServiceOnDeviceService;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/service-on-device")
public class ServiceOnDeviceController {
    private OrderService orderService;
    private ServiceOnDeviceService serviceOnDeviceService;

    @Autowired
    public ServiceOnDeviceController(OrderService orderService, ServiceOnDeviceService serviceOnDeviceService) {
        this.orderService = orderService;
        this.serviceOnDeviceService = serviceOnDeviceService;
    }

    @RequestMapping (
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOnDevice createServiceOnDevice (@RequestBody @Validated ServiceOnDeviceRequest request) {
        try {
            return this.orderService.addServiceOnDeviceToOrder(request.getOrderId(),
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
    public ServiceOnDevice getService (@PathVariable Long id) {
        return this.serviceOnDeviceService.getServiceOnDevice(id).orElseThrow();
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService (@PathVariable Long id) {
        this.serviceOnDeviceService.deleteServiceOnDevice(id);
    }

}
