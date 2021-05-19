package com.timsanalytics.sampleproject.controllers;

import com.timsanalytics.sampleproject.beans.KeyValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-check")
@Tag(name = "Health", description = "Health")
public class HealthController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Health Check", description = "Health Check", tags = {"Health"}, security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<KeyValue> getHealthCheck() {
        try {
            return ResponseEntity.ok()
                    .body(new KeyValue("health-check", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
