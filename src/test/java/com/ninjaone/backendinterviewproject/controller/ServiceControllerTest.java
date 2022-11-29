package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.controller.request.ServiceRequest;
import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.service.TechServiceService;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(ServiceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ServiceControllerTest {
    private static final String SERVICE_ID = "Antivirus for Windows";
    private static final String SERVICE_TYPE_ID = "ANTIVIRUS";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private TechServiceService techServiceService;

    private TechService techService;
    private ServiceRequest request;

    @BeforeEach
    void init () {
        techService = OrderTestFactory.getInstance().createServiceOfTypeAntivirus();
        request = new ServiceRequest();
        request.setName(SERVICE_ID);
        request.setPrice(BigDecimal.ONE);
        request.setOperatingSystem(OperatingSystem.WINDOWS.toString());
        request.setType(SERVICE_TYPE_ID);
    }

    @Test
    void getServiceTest() throws Exception {
        when(techServiceService.getService(SERVICE_ID)).thenReturn(Optional.of(techService));
        mockMvc.perform(get("/service/" + SERVICE_ID))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(techService)));
    }

    @Test
    public void postServiceTest () throws Exception {
        when(techServiceService.createService(techService, SERVICE_TYPE_ID)).thenReturn(techService);
        String serviceEntityString = objectMapper.writeValueAsString(techService);
        String requestString = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isCreated())
                .andExpect(content().string(serviceEntityString));
    }

    @Test
    public void postServiceNotValidRequestTest () throws Exception {
        when(techServiceService.createService(techService, SERVICE_TYPE_ID)).thenReturn(techService);
        request.setType(null);
        String serviceEntityString = objectMapper.writeValueAsString(techService);
        String requestString = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteServiceTest() throws Exception {
        doNothing().when(techServiceService).deleteService(SERVICE_ID);
        mockMvc.perform(delete("/service/" + SERVICE_ID))
                .andExpect(status().isNoContent());
    }
}
