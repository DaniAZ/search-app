package com.edu.mum.candidatesearchapp.controller;

import com.edu.mum.candidatesearchapp.domain.Candidate;
import com.edu.mum.candidatesearchapp.domain.CandidateList;
import com.edu.mum.candidatesearchapp.service.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@Api(value="Candidate Searching REST API")
public class RestHandler {

    @Autowired
    CandidateService candidateService;

    @PostMapping(path = "/add", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    @ApiOperation(value="add new candidate", notes = "${RestHandler.addCandidate.notes}")
    @CrossOrigin(origins = {"http://localhost:4200"},
            maxAge = 4800, allowCredentials = "false")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate addCandidate(
            @ApiParam(value = "${RestHandler.addCandidate.candidate}", required = true)
            @RequestBody Candidate candidate) {

        candidateService.addCandidate(candidate);
        return candidate;
    }

    @PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
    @ApiOperation(value="add new candidate", notes = "${RestHandler.getCandidate.notes}")
    @CrossOrigin(origins = {"http://localhost:4200"},
            maxAge = 4800, allowCredentials = "false")
    public CandidateList getCandidates(
            @ApiParam(value = "${RestHandler.getCandidate.skills}", required = true)
            @RequestBody String[] skills){


        return candidateService.searchCandidate(skills);

    }
}
