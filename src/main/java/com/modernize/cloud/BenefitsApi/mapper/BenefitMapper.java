package com.modernize.cloud.BenefitsApi.mapper;

import com.modernize.cloud.BenefitsApi.model.Benefit;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BenefitMapper {
    BenefitMapper INSTANCE = Mappers.getMapper(BenefitMapper.class);

    @Mapping(source = "id", target = "id")
    BenefitResponse benefitToBenefitResponse(Benefit benefit);

    @Mapping(source = "id", target = "id")
    Benefit benefitRequestToBenefit(BenefitRequest benefitRequest);
}
