package id.duskclouds.springbatch.configuration.controller;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class JobLaunchingController {

    @Autowired
    private JobOperator jobOperator;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void launch(@RequestParam("name") String name) throws Exception {
        this.jobOperator.start(name, String.format("timestamp=%s", Instant.now()));
    }
}
