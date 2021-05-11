package com.modernize.cloud.BenefitsApi.controllers;

import com.cloudant.client.api.CloudantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CloudantController {

    @Autowired
    private CloudantClient client;

    @RequestMapping("/databases")
    public @ResponseBody
    List<String> data() {
        List<String> allDbs = client.getAllDbs();
        return allDbs;
    }
}