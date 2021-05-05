package com.modernize.cloud.BenefitsApi.services;

import com.modernize.cloud.BenefitsApi.mapper.BenefitMapper;
import com.modernize.cloud.BenefitsApi.model.Benefit;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.repositories.BenefitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitServiceImpl implements BenefitService {

    private BenefitRepository benefitRepository;
    private BenefitMapper benefitMapper;

    public BenefitServiceImpl(BenefitRepository benefitRepository, BenefitMapper benefitMapper) {
        this.benefitRepository = benefitRepository;
        this.benefitMapper = benefitMapper;
    }

    @Override
    public List<BenefitResponse> findAll() {

        List<BenefitResponse> benefits = benefitRepository
                .findAll()
                .stream()
                .map(benefitMapper::benefitToBenefitResponse)
                .collect(Collectors.toList());
        return benefits;
    }

    @Override
    public BenefitResponse findById(Long aLong) {
        return benefitMapper.benefitToBenefitResponse(benefitRepository.findById(aLong).orElse(null));
    }

    @Override
    public BenefitResponse save(BenefitRequest benefit) {
        Benefit savedBenefit = benefitRepository.save(benefitMapper.benefitRequestToBenefit(benefit));
        return benefitMapper.benefitToBenefitResponse(savedBenefit);
    }

    @Override
    public void delete(BenefitRequest benefit) {
        benefitRepository.delete(benefitMapper.benefitRequestToBenefit(benefit));
    }

    @Override
    public void deleteById(Long aLong) {
        benefitRepository.deleteById(aLong);
    }
}
