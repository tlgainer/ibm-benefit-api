package com.modernize.cloud.BenefitsApi.services;

import com.modernize.cloud.BenefitsApi.mapper.BenefitMapper;
import com.modernize.cloud.BenefitsApi.model.Benefit;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.repositories.BenefitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void findAll() throws Exception{

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

        //given
        Long ID = 1L;

        Benefit savedBenefit = new Benefit();
        savedBenefit.setDescription(DESCRIPTION);
        savedBenefit.setId(ID);

        when(benefitRepository.findById(ID)).thenReturn(Optional.ofNullable(savedBenefit));

        //when
        BenefitResponse benefitResponse = benefitService.findById(1L);

        //then
        assertEquals(savedBenefit.getDescription(), benefitResponse.getDescription());

    }

    @Test
    void save() {

        //given
        BenefitRequest benefitRequest = new BenefitRequest();
        benefitRequest.setDescription(DESCRIPTION);

        Benefit savedBenefit = new Benefit();
        savedBenefit.setDescription(DESCRIPTION);
        savedBenefit.setId(1L);

        when(benefitRepository.save(any(Benefit.class))).thenReturn(savedBenefit);

        //when
        BenefitResponse benefitResponse = benefitService.save(benefitRequest);

        //then
        assertEquals(benefitRequest.getDescription(), savedBenefit.getDescription());
        assertEquals(1, savedBenefit.getId());

    }

    @Test
    void update() {

        //given
        BenefitRequest benefitRequest = new BenefitRequest();
        benefitRequest.setDescription(DESCRIPTION);

        Benefit benefit = new Benefit();
        benefit.setDescription(DESCRIPTION);

        when(benefitRepository.save(any(Benefit.class))).thenReturn(benefit);

        //when
        BenefitResponse benefitResponse = benefitService.update(1L, benefitRequest);

        //then
        assertEquals(benefitRequest.getDescription(), benefitResponse.getDescription());
        assertEquals(1, benefitResponse.getId());

    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
        Long id = 1L;

        benefitService.deleteById(id);

        verify(benefitRepository, times(1)).deleteById(anyLong());
    }
}