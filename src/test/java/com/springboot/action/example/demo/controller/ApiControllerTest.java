package com.springboot.action.example.demo.controller;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.service.AnimalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApiControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private ApiController apiController;

    @Mock
    private AnimalService animalService;


    @Before
    public void setupMockMvc() throws Exception {
        MockitoAnnotations.initMocks(this);
        apiController = new ApiController(animalService);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        String name = "dog";
        given(animalService.delete(name)).willReturn(true);

        mockMvc.perform(delete("/api/deleteAnimal").param("name", name))
                .andExpect(content().string("delete success"));

    }

    @Test
    public void deleteFailedWhenThisAniamlISNotExist() throws Exception {
        String name = "www";
        given(animalService.delete(name)).willReturn(false);

        mockMvc.perform(delete("/api/deleteAnimal").param("name", name))
                .andExpect(content().string("delete failed."));
    }

    @Test
    public void deleteFailedWhenThisAniamlISNull() throws Exception {
        String name = null;
        given(animalService.delete(name)).willReturn(false);

        mockMvc.perform(delete("/api/deleteAnimal").param("name", name))
                .andExpect(content().string("delete failed."));
    }

}