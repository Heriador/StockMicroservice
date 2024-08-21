package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.handler.ICategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    private AddCategory addCategory;
    private CategoryResponse categoryResponse;

    @BeforeEach
    void setup() {
        addCategory = AddCategory.builder()
                .name("computadoras")
                .description("para entrar a internet")
                .build();

        categoryResponse = CategoryResponse.builder()
                .id(1L)
                .name("computadoras")
                .description("para entrar a internet")
                .build();
    }

    @Test
    void createCategory() throws Exception {
        Mockito.doNothing().when(categoryHandler).createCategory(addCategory);

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"computadoras\",\"description\":\"para entrar a internet\"}"))
                .andExpect(status().isCreated());
    }

//    @Test
//    void getAllCategories() throws Exception {
//        List<CategoryResponse> categories = Arrays.asList(categoryResponse);
//        when(categoryHandler.getAllcategories()).thenReturn(categories);
//
//        mockMvc.perform(get("/category/"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"id\":1,\"name\":\"computadoras\",\"description\":\"para entrar a internet\"}]"));
//    }

    @Test
    void getCategory() throws Exception {
        when(categoryHandler.getCategory("computadoras")).thenReturn(categoryResponse);

        mockMvc.perform(get("/category/computadoras"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"computadoras\",\"description\":\"para entrar a internet\"}"));
    }
}