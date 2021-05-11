package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.services.BenefitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class BenefitControllerTest extends AbstractRestControllerTest {

    public static final String DESCRIPTION = "A new description";

    @Mock
    BenefitService benefitService;

    @InjectMocks
    BenefitController benefitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(benefitController)
                .setControllerAdvice(new RestExceptionHandler()).build();

    }

    @Test
    void getAllBenefits() throws Exception {

        //given
        BenefitResponse benefit1 = new BenefitResponse();
        benefit1.setId(1L);
        benefit1.setDescription(DESCRIPTION + " benefit1");

        BenefitResponse benefit2 = new BenefitResponse();
        benefit2.setId(2L);
        benefit2.setDescription(DESCRIPTION + " benefit2");

        List<BenefitResponse> benefitResponseList = Arrays.asList(benefit1, benefit2);

        //when
        when(benefitService.findAll()).thenReturn(benefitResponseList);

        //then
        mockMvc.perform(get("/api/v1/benefit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));



    }

    @Test
    void getById() throws Exception{
        BenefitResponse benefitResponse = new BenefitResponse();
        benefitResponse.setDescription(DESCRIPTION);

        when(benefitService.findById(anyLong())).thenReturn(benefitResponse);

        mockMvc.perform(get("/api/v1/benefit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    void save() throws Exception {
        Long Id = 1L;

        BenefitRequest benefitRequest = new BenefitRequest();
        benefitRequest.setDescription(DESCRIPTION);
        benefitRequest.setEmpId(Id);


        BenefitResponse benefitResponse = new BenefitResponse();
        benefitResponse.setId(Id);
        benefitResponse.setDescription(DESCRIPTION);
        benefitResponse.setEmpId(Id);

        when(benefitService.save(any(BenefitRequest.class))).thenReturn(benefitResponse);

        mockMvc.perform(post("/api/v1/benefit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(benefitRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$.empId", equalTo(1)))
                .andExpect(jsonPath("$.id", equalTo(1)));


    }

    @Test
    void update() throws Exception {
        BenefitRequest benefitRequest = new BenefitRequest();
        benefitRequest.setDescription(DESCRIPTION);

        BenefitResponse benefitResponse = new BenefitResponse();
        benefitResponse.setDescription(benefitRequest.getDescription());

        when(benefitService.update(anyLong(), any(BenefitRequest.class))).thenReturn(benefitResponse);

        mockMvc.perform(put("/api/v1/benefit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(benefitRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/benefit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}