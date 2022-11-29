package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.TechServiceType;
import com.ninjaone.backendinterviewproject.service.OrderService;
import com.ninjaone.backendinterviewproject.service.TechServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-type")
public class ServiceTypeController {
    private TechServiceService techServiceService;

    @Autowired
    public ServiceTypeController(TechServiceService techServiceService) {
        this.techServiceService = techServiceService;
    }

    @RequestMapping (path="/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public List<TechServiceType> getAllTypes () {
        return this.techServiceService.getAllTypes();
    }
}
