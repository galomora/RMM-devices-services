package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.controller.request.DeviceRequest;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(DeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceControllerTest {

    private static final String DEVICE_ID = "DEV1";
    private static final String TYPE_NAME = "ANTIVIRUS";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    private Device device;
    private DeviceRequest request;

    @BeforeEach
    void init (){
        device = OrderTestFactory.getInstance().createDeviceWin();
        request = new DeviceRequest();
        request.setId(DEVICE_ID);
        request.setType(TYPE_NAME);
        request.setOperatingSystem(OperatingSystem.WINDOWS.toString());
    }

    @Test
    public void postDeviceTest () throws Exception {
        when(deviceService.createDevice(any())).thenReturn(OrderTestFactory.getInstance().createDeviceWin());
        String requestString = objectMapper.writeValueAsString(request);
        String deviceString = objectMapper.writeValueAsString(device);
        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isCreated())
                .andExpect(content().string(deviceString));
    }

    @Test
    void getDeviceTest() throws Exception {
        when(deviceService.getDevice(DEVICE_ID)).thenReturn(Optional.of(device));

        mockMvc.perform(get("/device/" + DEVICE_ID))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(device)));
    }

    @Test
    void getDeviceNoDataTest()  {
        when(deviceService.getDevice(DEVICE_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/device/" + DEVICE_ID))
                        .andExpect(status().isNotFound()));
    }

    @Test
    void deleteDeviceTest() throws Exception {
        doNothing().when(deviceService).deleteDevice(DEVICE_ID);
        mockMvc.perform(delete("/device/" + DEVICE_ID))
                .andExpect(status().isNoContent());
    }
}
