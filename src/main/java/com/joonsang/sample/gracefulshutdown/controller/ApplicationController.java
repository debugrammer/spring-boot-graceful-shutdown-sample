package com.joonsang.sample.gracefulshutdown.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Application Controller
 * @author debugrammer
 * @version 1.0
 * @since 2019-11-17
 */
@RestController
@RequestMapping({"/"})
public class ApplicationController {

    @GetMapping({"/"})
    public ResponseEntity<?> mysteriousSlowLogic() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);

        return new ResponseEntity<>("MYSTERIOUS SLOW LOGIC PROCESS FINISHED.", HttpStatus.OK);
    }
}
