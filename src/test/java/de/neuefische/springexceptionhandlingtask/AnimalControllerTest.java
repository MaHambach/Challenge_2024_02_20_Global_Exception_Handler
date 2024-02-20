package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getAnimal_whenNotDog_thenThrow() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "apiPath": "uri=/api/animals/cat",
                            "errorCode": "BAD_REQUEST",
                            "errorMsg": "Only 'dog' is allowed"
                        }
                        """));
    }

    @Test
    void getAnimal_whenDog_thenReturnDog() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/dog"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("dog"));
    }

    @Test
    void getAllAnimals_when_thenThrow() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "apiPath": "uri=/api/animals",
                            "errorCode": "BAD_REQUEST",
                            "errorMsg": "No Animals found"
                        }"""));
    }
}