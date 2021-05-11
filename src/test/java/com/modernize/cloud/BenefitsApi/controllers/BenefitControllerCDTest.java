package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.services.BenefitService;
import com.modernize.cloud.BenefitsApi.services.BenefitServiceCD;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BenefitControllerCDTest extends AbstractRestControllerTest {

    public static final String DESCRIPTION = "A new description";

    @Mock
    BenefitServiceCD benefitService;

    @InjectMocks
    BenefitControllerCD benefitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(benefitController)
                .setControllerAdvice(new RestExceptionHandler()).build();

    }

    @Test
    void getAllBenefits() throws Exception {

        String ID1 = "myId1";
        String ID2 = "myId2";

        //given
        BenefitCD benefit1 = new BenefitCD();
        benefit1.set_id(ID1);
        benefit1.setDescription(DESCRIPTION + " benefit1");

        BenefitCD benefit2 = new BenefitCD();
        benefit2.set_id(ID2);
        benefit2.setDescription(DESCRIPTION + " benefit2");

        List<BenefitCD> benefitResponseList = Arrays.asList(benefit1, benefit2);

        //when
        when(benefitService.findAll()).thenReturn(benefitResponseList);

        //then
        mockMvc.perform(get("/api/v2/benefit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void getById() throws Exception {
        BenefitCD benefitResponse = new BenefitCD();
        benefitResponse.setDescription(DESCRIPTION);

        when(benefitService.findById(anyString())).thenReturn(benefitResponse);

        mockMvc.perform(get("/api/v2/benefit/myId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    void save() throws Exception  {
        String Id = "myId";
        Long empId = 1L;

        BenefitCD benefitRequest = new BenefitCD();
        benefitRequest.setDescription(DESCRIPTION);
        benefitRequest.setEmpId(empId);


        BenefitCD benefitResponse = new BenefitCD();
        benefitResponse.set_id(Id);
        benefitResponse.setDescription(DESCRIPTION);
        benefitResponse.setEmpId(empId);

        mockMvc.perform(post("/api/v2/benefit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(benefitRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception  {
        String Id = "myId";
        Long empId = 1L;

        BenefitCD benefitRequest = new BenefitCD();
        benefitRequest.setDescription(DESCRIPTION);
        benefitRequest.setEmpId(empId);


        BenefitCD benefitResponse = new BenefitCD();
        benefitResponse.set_id(Id);
        benefitResponse.setDescription(DESCRIPTION);
        benefitResponse.setEmpId(empId);

        mockMvc.perform(put("/api/v2/benefit/myId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(benefitRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void delete()  throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/benefit/myId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}