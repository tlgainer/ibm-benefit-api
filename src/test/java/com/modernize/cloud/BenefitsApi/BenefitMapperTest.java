package com.modernize.cloud.BenefitsApi;

import com.modernize.cloud.BenefitsApi.mapper.BenefitMapper;
import com.modernize.cloud.BenefitsApi.model.Benefit;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenefitMapperTest {

    BenefitMapper benefitMapper = BenefitMapper.INSTANCE;

    public static final long ID = 1L;
    public static final String Description = "A new description.";

    @Test
    public void benefitToBenefitResponse() throws Exception {

        //given
        Benefit benefit = new Benefit();
        benefit.setId(ID);
        benefit.setDescription(Description);

        //when
        BenefitResponse benefitResponse = benefitMapper.benefitToBenefitResponse(benefit);

        //then
        assertEquals(Long.valueOf(ID), benefitResponse.getId());
        assertEquals(Description, benefitResponse.getDescription());
    }

    @Test
    public void benefitRequestToBenefit() throws Exception {

        //given
        BenefitRequest benefitRequest = new BenefitRequest();
        benefitRequest.setId(ID);
        benefitRequest.setDescription(Description);

        //when
        Benefit benefit = BenefitMapper.INSTANCE.benefitRequestToBenefit(benefitRequest);

        //then
        assertEquals(Long.valueOf(ID), benefit.getId());
        assertEquals(Description, benefit.getDescription());
    }
}
