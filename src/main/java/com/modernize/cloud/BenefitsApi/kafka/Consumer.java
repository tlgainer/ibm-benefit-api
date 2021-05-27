package com.modernize.cloud.BenefitsApi.kafka;

import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import com.modernize.cloud.BenefitsApi.services.BenefitServiceCD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private BenefitServiceCD benefitService;

    @KafkaListener(topics = "topic-benefit", groupId = "benefit", containerFactory = "benefitKafkaListenerContainerFactory")
    public void consumeMessage(BenefitCD benefit) {
        System.out.println("benefit received " + benefit.getDescription());
        benefitService.save(benefit);
    }
}
