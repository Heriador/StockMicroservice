package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryPersistencePort categoryPersistencePort;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L,"laptos","para entrar a internet");


    }

    @Test
    public  void saveCategory() throws  Exception{
        Category postCategory = new Category(1L,"laptos","para entrar a internet");

        Mockito.when(categoryPersistencePort.saveCategory(postCategory)).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON)
                .content("\n"+
                        "\"name\":\"laptos\",\n"+
                        "\"description\":\"para entrar a internet\",\n"+
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}