package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.controller.request.DeviceRequest;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/device")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping (
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Device createDevice (@RequestBody @Validated DeviceRequest deviceRequest) {
        return this.deviceService.createDevice(
                new Device(deviceRequest.getId(),
                        deviceRequest.getSystemName(),
                        deviceRequest.getType(),
                        OperatingSystem.valueOf(deviceRequest.getOperatingSystem())));
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public Device getDevice (@PathVariable String id) {
            return this.deviceService.getDevice(id).orElseThrow();
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice (@PathVariable String id) {
        this.deviceService.deleteDevice(id);
    }
}
