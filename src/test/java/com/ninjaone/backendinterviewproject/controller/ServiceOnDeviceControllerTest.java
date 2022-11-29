package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.controller.request.ServiceOnDeviceRequest;
import com.ninjaone.backendinterviewproject.model.*;
import com.ninjaone.backendinterviewproject.service.OrderService;
import com.ninjaone.backendinterviewproject.service.ServiceOnDeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(ServiceOnDeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ServiceOnDeviceControllerTest {
    private static final String SERVICE_ID = "Antivirus for Windows";
    private static final String DEVICE_ID = "DEV1";
    private static final Long ORDER_ID = 1L;
    private static final Long SERVICE_ON_DEVICE_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;
    @MockBean
    private ServiceOnDeviceService serviceOnDeviceService;

    private Order order;
    private ServiceOnDeviceRequest request;
    private ServiceOnDevice serviceOnDevice;

    @BeforeEach
    void init () {
        order = OrderTestFactory.getInstance().createOrder();
        serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinAntivirus();
        request = new ServiceOnDeviceRequest();
        request.setDeviceId(DEVICE_ID);
        request.setServiceName(SERVICE_ID);
        request.setOrderId(ORDER_ID);
    }

    @Test
    void getServiceOnDeviceTest() throws Exception {
        when(serviceOnDeviceService.getServiceOnDevice(SERVICE_ON_DEVICE_ID)).thenReturn(Optional.of(serviceOnDevice));
        mockMvc.perform(get("/service-on-device/" + SERVICE_ON_DEVICE_ID))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(serviceOnDevice)));
    }

    @Test
    public void postServiceOnDeviceTest () throws Exception {
        when(orderService.addServiceOnDeviceToOrder(request.getOrderId(),
                request.getDeviceId(), request.getServiceName())).thenReturn(serviceOnDevice);
        String serviceEntityString = objectMapper.writeValueAsString(serviceOnDevice);
        String requestString = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/service-on-device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isCreated())
                .andExpect(content().string(serviceEntityString));
    }

    @Test
    public void postServiceNotValidRequestTest () throws Exception {
        when(orderService.addServiceOnDeviceToOrder(request.getOrderId(),
                request.getDeviceId(), request.getServiceName())).thenReturn(serviceOnDevice);
        request.setOrderId(-1L);
        String serviceEntityString = objectMapper.writeValueAsString(serviceOnDevice);
        String requestString = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/service-on-device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteServiceTest() throws Exception {
        doNothing().when(serviceOnDeviceService).deleteServiceOnDevice(SERVICE_ON_DEVICE_ID);
        mockMvc.perform(delete("/service-on-device/" + SERVICE_ON_DEVICE_ID))
                .andExpect(status().isNoContent());
    }
}
