package com.modernize.cloud.BenefitsApi.services;

import com.modernize.cloud.BenefitsApi.mapper.BenefitMapper;
import com.modernize.cloud.BenefitsApi.model.Benefit;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.repositories.BenefitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

class BenefitServiceImplTest {

    public static final Long ID = 2L;
    public static final String DESCRIPTION = "A new description.";

    BenefitService benefitService;

    @Mock
    BenefitRepository benefitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        benefitService = new BenefitServiceImpl(benefitRepository, BenefitMapper.INSTANCE);
    }

    @Test
    void findAll() {

        //given
        List<Benefit> benefitList = Arrays.asList(new Benefit(), new Benefit(), new Benefit());
        when(benefitRepository.findAll()).thenReturn(benefitList);

        //when
        List<BenefitResponse> benefitResponseList = benefitService.findAll();

        //then
        assertEquals(3, benefitResponseList.size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}