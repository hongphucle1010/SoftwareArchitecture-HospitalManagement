package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.common.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
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
