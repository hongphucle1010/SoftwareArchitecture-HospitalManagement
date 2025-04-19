package com.hcmutnightowls.testingservice.controller;

import com.hcmutnightowls.testingservice.dto.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing")
@RequiredArgsConstructor
public class TestingController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<String> test() {
        // Hello world
        return ResponseObject.<String>builder()
                .status(HttpStatus.OK.value())
                .message("successfully")
                .data("Hello world")
                .build();
    }
}
