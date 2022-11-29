package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.billing.Billing;
import com.ninjaone.backendinterviewproject.service.BillingService;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private BillingService billingService;

    @Autowired
    public BillingController (BillingService billingService) {
        this.billingService = billingService;
    }

    @RequestMapping (path="/{orderId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public Billing getBilling (@PathVariable Long orderId) {
        try {
            return this.billingService.getBillingFromOrder(orderId, Boolean.TRUE);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
