package com.edu.mum.candidatesearchapp;

import com.edu.mum.candidatesearchapp.controller.RestHandler;
import com.edu.mum.candidatesearchapp.domain.Candidate;
import com.edu.mum.candidatesearchapp.service.CandidateService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateSearchAppApplicationTests {

    private MockMvc mockMvc;

    @InjectMocks
    private RestHandler restHandler;

    @MockBean
    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(restHandler)
                .build();

        /*
            The init() method runs MockitoAnnotations.initMocks(this) using this instance as the argument.
            This sets up our mocks before each test. Passing this instance will make Mockito acknowledge the
            @InjectMocks and the @Mocks annotations and that they should be pushed together.
         */
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTopTrackTest()throws Exception {

        String[] skills = new String[5];
        skills[0]="sql";
        skills[1]="swagger";
        skills[2]="angular";
        skills[3]="spring";
        skills[4]="jquery";

        Candidate candidate = new Candidate("dawit","interview",2012,skills);
        Gson gson = new Gson();


        mockMvc.perform(post("/api/candidate/add")
                .content( gson.toJson(candidate))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is("dawit")))
                .andExpect(jsonPath("$.project", Matchers.is("interview")))
                .andExpect(jsonPath("$.year", Matchers.is(2012)));

    }

    @Test
    public void contextLoads() {
    }

}
