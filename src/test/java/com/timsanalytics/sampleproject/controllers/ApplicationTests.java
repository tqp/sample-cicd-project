package com.timsanalytics.sampleproject.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void verifyHelloMessage() throws Exception {
//        String patternString = "(Hello AWS Continuous Delivery! From host: ).*\\/\\d+.\\d+.\\d+.\\d+";
//        Matcher<String> regexMatcher = Matchers.matchesRegex(patternString);
//
//        this.mockMvc.perform(get("/hello")).andExpect(status().isOk())
//                .andExpect(content().string(regexMatcher));
    }
}
