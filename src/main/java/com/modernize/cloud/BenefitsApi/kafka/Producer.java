package com.modernize.cloud.BenefitsApi.kafka;

import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value(value = "${benefit.topic.name}")
    private String benefitTopic;

    @Autowired
    private KafkaTemplate<String, BenefitCD> kafkaTemplate;

    public void sendMessage(BenefitCD benefit) {
        this.kafkaTemplate.send(benefitTopic, benefit);
    }
}
