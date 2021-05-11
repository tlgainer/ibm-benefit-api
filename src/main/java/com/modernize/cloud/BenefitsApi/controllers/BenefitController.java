package com.modernize.cloud.BenefitsApi.controllers;

import com.modernize.cloud.BenefitsApi.model.BenefitRequest;
import com.modernize.cloud.BenefitsApi.model.BenefitResponse;
import com.modernize.cloud.BenefitsApi.services.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/benefit")
public class BenefitController {

    @Autowired
    private BenefitService benefitService;

    @GetMapping
    public ResponseEntity<List<BenefitResponse>> getBenefit() throws Exception {
        return new ResponseEntity<List<BenefitResponse>>(benefitService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<BenefitResponse> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<BenefitResponse>(benefitService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BenefitResponse> save(@RequestBody BenefitRequest benefit) throws Exception {

        BenefitResponse benefitResponse = benefitService.save(benefit);
        return new ResponseEntity<BenefitResponse>(benefitResponse, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<BenefitResponse> update(@PathVariable Long id, @Valid @RequestBody BenefitRequest benefit) throws Exception {
        return new ResponseEntity<BenefitResponse>(benefitService.update(id, benefit), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        benefitService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
