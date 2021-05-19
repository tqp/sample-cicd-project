package com.timsanalytics.sampleproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1/hello")
@Tag(name = "Hello", description = "Hello")
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Health Check", description = "Health Check", tags = {"Health"}, security = @SecurityRequirement(name = "bearerAuth"))
    public String hello() {
        String message = "Hello AWS Continuous Delivery!";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message += " From host: " + ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message;
    }

}
