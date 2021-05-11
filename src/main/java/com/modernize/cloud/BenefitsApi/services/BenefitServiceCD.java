package com.modernize.cloud.BenefitsApi.services;

import com.cloudant.client.api.model.Response;
import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;

import java.util.List;

public interface BenefitServiceCD {

    List<BenefitCD> findAll() throws Exception;
    BenefitCD findById(String id);
    void save(BenefitCD benefit);
    void update(String id, BenefitCD benefit);

    void delete(BenefitCD benefit);
    void deleteById(String id);

}
