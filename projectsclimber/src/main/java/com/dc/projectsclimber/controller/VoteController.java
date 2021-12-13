package com.dc.projectsclimber.controller;

import com.dc.projectsclimber.entity.Vote;
import com.dc.projectsclimber.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<?> operationVote(@RequestBody @Valid Vote vote) {
        if (Objects.nonNull(voteService.operationVote(vote))) {
            return new ResponseEntity<>(voteService.operationVote(vote), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllVotes(@RequestParam(name= "idUser", required = false) Long idUser) {
        return new ResponseEntity<>(voteService.getVotes(idUser), HttpStatus.OK);
    }
}
