package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getCarBrand_whenNotPorsche_thenThrow() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/audi"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "apiPath": "uri=/api/cars/audi",
                            "errorCode": "BAD_REQUEST",
                            "errorMsg": "Only 'porsche' allowed"
                        }
                        """));
    }

    @Test
    void getCarBrand_whenPorsche_thenReturnPorsche() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("porsche"));
    }

    @Test
    void getAllCars_when_thenThrow() throws Exception {
        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "apiPath": "uri=/api/cars",
                            "errorCode": "BAD_REQUEST",
                            "errorMsg": "No Cars found"
                        }
                        """));
    }
}