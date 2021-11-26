package com.qaproject.Cookbook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.Cookbook.entity.Chef;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:chef-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ChefControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper jsonifier;

    @Test
    public void testAddChef() throws Exception {
        Chef testChef = new Chef(2,"Mohab","All");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/newChef");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(this.jsonifier.writeValueAsString(testChef));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
        ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testChef));

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
    }

    @Test
    public void testUpdateChef() throws Exception {
        Chef testChef = new Chef(1,"Gordon","All");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/updateChef?id=1");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(this.jsonifier.writeValueAsString(testChef));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testChef));

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
    }

    @Test
    public void testDeleteChef() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/deleteChef/1");

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

        this.mock.perform(mockRequest).andExpect(matchStatus);
    }


    @Test
    public void testGetAllChef() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getChefs");

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

        this.mock.perform(mockRequest).andExpect(matchStatus);
    }

}
