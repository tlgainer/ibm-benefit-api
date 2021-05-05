package com.modernize.cloud.BenefitsApi.controllers;

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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BenefitControllerTest {

    public static final String DESCRIPTION = "A new description";

    @Mock
    BenefitService benefitService;

    @InjectMocks
    BenefitController benefitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(benefitController).build();

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
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/benefit/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("", hasSize(2)));



    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}