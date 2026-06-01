package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Test
//    void testHelloDefault() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello, World! v6"));
//    }

    @Test
    void testHelloWithName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "张三"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 张三! v1112"));
    }

    @Test
    void testHealth() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}
