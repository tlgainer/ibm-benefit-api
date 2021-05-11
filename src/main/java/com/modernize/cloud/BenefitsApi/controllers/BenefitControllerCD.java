package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.services.BenefitService;
import com.modernize.cloud.BenefitsApi.services.BenefitServiceCD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v2/benefit")
public class BenefitControllerCD {

    @Autowired
    private BenefitServiceCD benefitService;

    @GetMapping
    public ResponseEntity<List<BenefitCD>> getBenefit() throws Exception {
        return new ResponseEntity<List<BenefitCD>>(benefitService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<BenefitCD> getById(@PathVariable String id) throws Exception {
        return new ResponseEntity<BenefitCD>(benefitService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BenefitCD> save(@Valid @RequestBody BenefitCD benefit) throws Exception {
        return new ResponseEntity(benefitService.save(benefit), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<BenefitCD> update(@PathVariable String id, @Valid @RequestBody BenefitCD benefit) throws Exception {
        return new ResponseEntity(benefitService.update(id, benefit), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        benefitService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
