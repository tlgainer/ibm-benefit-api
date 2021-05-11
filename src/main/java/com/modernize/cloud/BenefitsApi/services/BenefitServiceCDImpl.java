package com.modernize.cloud.BenefitsApi.services;


import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BenefitServiceCDImpl implements BenefitServiceCD {


    private Database benefitRepository;

    public BenefitServiceCDImpl(Database benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @Override
    public List<BenefitCD> findAll() throws Exception {

        List<BenefitCD> benefits = benefitRepository.getAllDocsRequestBuilder()
                .includeDocs(true)
                .build()
                .getResponse()
                .getDocsAs(BenefitCD.class);

        return benefits;

    }

    @Override
    public BenefitCD findById(String id) {
        return  benefitRepository.find(BenefitCD.class, id);
    }

    @Override
    public void save(BenefitCD benefit) {
        Response response = benefitRepository.post(benefit);
    }

    @Override
    public void update(String id, BenefitCD benefit) {
        benefit.set_id(id);
        Response response = benefitRepository.update(benefit);
    }

    @Override
    public void delete(BenefitCD benefit) {

    }

    @Override
    public void deleteById(String id) {
        BenefitCD benefitCD = benefitRepository.find(BenefitCD.class, id);
        Response remove = benefitRepository.remove(benefitCD.get_id(),benefitCD.get_rev());
    }
}
