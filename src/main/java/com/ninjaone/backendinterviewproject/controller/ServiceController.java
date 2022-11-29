package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.controller.request.ServiceRequest;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.service.OrderService;
import com.ninjaone.backendinterviewproject.service.TechServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private TechServiceService techServiceService;

    @Autowired
    public ServiceController(TechServiceService techServiceService) {
        this.techServiceService = techServiceService;
    }

    @RequestMapping (
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TechService createService (@RequestBody @Validated ServiceRequest serviceRequest) {
        return this.techServiceService.createService(
                new TechService(serviceRequest.getName(), serviceRequest.getPrice(),
                        OperatingSystem.valueOf(serviceRequest.getOperatingSystem()), null),
                serviceRequest.getType());
    }

    @RequestMapping (path="/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public TechService getService (@PathVariable String name) {
        return this.techServiceService.getService(name).orElseThrow();
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService (@PathVariable String id) {
        this.techServiceService.deleteService(id);
    }

}
