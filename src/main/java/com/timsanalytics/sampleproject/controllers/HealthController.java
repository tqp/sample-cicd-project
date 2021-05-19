package com.timsanalytics.sampleproject.controllers;

import com.timsanalytics.sampleproject.beans.KeyValue;
import com.timsanalytics.sampleproject.services.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/health-check")
@Tag(name = "Health", description = "Health")
public class HealthController {
    private final HealthService healthService;

    @Autowired
    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Health Check", description = "Health Check", tags = {"Health"}, security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<KeyValue> getHealthCheck() {
        try {
            return ResponseEntity.ok()
                    .body(new KeyValue("health-check", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/build-timestamp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Build Timestamp", description = "Build Timestamp", tags = {"Health"}, security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<KeyValue> getBuildTimestamp() {
        try {
            return ResponseEntity.ok()
                    .body(new KeyValue("buildTimestamp", this.healthService.getBuildTimestamp()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
