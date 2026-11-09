package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalculatorService service;

    @Test
    void testAddEndpoint() throws Exception {
        when(service.add(2, 3)).thenReturn(5.0);
        mockMvc.perform(get("/api/calculator/add?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void testSubtractEndpoint() throws Exception {
        when(service.subtract(5, 3)).thenReturn(2.0);
        mockMvc.perform(get("/api/calculator/subtract?a=5&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void testMultiplyEndpoint() throws Exception {
        when(service.multiply(2, 3)).thenReturn(6.0);
        mockMvc.perform(get("/api/calculator/multiply?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }

    @Test
    void testDivideEndpoint() throws Exception {
        when(service.divide(6, 3)).thenReturn(2.0);
        mockMvc.perform(get("/api/calculator/divide?a=6&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void testDivideByZero() throws Exception {
        when(service.divide(5, 0)).thenThrow(new IllegalArgumentException("Division par zéro interdite"));
        mockMvc.perform(get("/api/calculator/divide?a=5&b=0"))
                .andExpect(status().isBadRequest());
    }
}