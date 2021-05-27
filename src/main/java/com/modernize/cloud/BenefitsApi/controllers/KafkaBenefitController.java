package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.kafka.Producer;
import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaBenefitController {

    private final Producer producer;

    @Autowired
    public KafkaBenefitController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/kafka/v1/benefit")
    public void benefitToTopic(@RequestBody() BenefitCD benefit) {

        this.producer.sendMessage(benefit);

    }
}
