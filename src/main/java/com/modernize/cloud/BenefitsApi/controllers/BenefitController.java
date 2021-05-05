package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.services.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benefit/")
public class BenefitController {

    @Autowired
    private BenefitService benefitService;

    @GetMapping
    public ResponseEntity<List<BenefitResponse>> getBenefit() {
        return new ResponseEntity<List<BenefitResponse>>(benefitService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BenefitResponse> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<BenefitResponse>(benefitService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BenefitResponse> save(@RequestBody BenefitRequest benefit) {
        return new ResponseEntity<BenefitResponse>(benefitService.save(benefit), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BenefitResponse> update(@RequestBody BenefitRequest benefit) {
        return new ResponseEntity<BenefitResponse>(benefitService.save(benefit), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        benefitService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
