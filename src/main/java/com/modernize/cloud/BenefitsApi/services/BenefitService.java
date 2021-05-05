package com.modernize.cloud.BenefitsApi.services;

import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;

import java.util.List;

public interface BenefitService {

    List<BenefitResponse> findAll();
    BenefitResponse findById(Long id);
    BenefitResponse save(BenefitRequest benefit);

    void delete(BenefitRequest benefit);
    void deleteById(Long id);
}
