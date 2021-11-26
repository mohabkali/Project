package com.qaproject.Cookbook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.entity.Recipe;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:recipe-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class RecipeControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper jsonifier;

    @Test
    public void testAddRecipe() throws Exception {
        Recipe testRecipe = new Recipe(2,"Lasagne","Italian","Main","Beef,sauce and Lasagne","Bake for 45 mins","JamieOliver");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/newRecipe");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(this.jsonifier.writeValueAsString(testRecipe));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
        ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testRecipe));

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

    }

    @Test
    public void testUpdateRecipe() throws Exception {
        Recipe testRecipe = new Recipe(1,"Bolognese","Italian","Main","Beef, sauce and pasta","Boil pasta and heat sauce","JamieOliver");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/updateRecipe?id=1");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(this.jsonifier.writeValueAsString(testRecipe));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testRecipe));

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
    }

    @Test
    public void testDeleteChef() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/deleteRecipe/1");

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

        this.mock.perform(mockRequest).andExpect(matchStatus);
    }

    @Test
    public void testGetAllChef() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getRecipes");

        ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

        this.mock.perform(mockRequest).andExpect(matchStatus);
    }
}
