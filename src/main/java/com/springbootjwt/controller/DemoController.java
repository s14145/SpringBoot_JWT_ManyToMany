package com.springbootjwt.controller;

import com.springbootjwt.service.AuthenticationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    private Logger log = LogManager.getLogger(DemoController.class);

    @GetMapping

    public ResponseEntity<String> demo(){
        log.info("DemoController:demo method entered");
        String message = "Welcome to the REST API";
        log.info("DemoController:demo method exited");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}