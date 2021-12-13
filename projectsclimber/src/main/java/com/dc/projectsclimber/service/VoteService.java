package com.dc.projectsclimber.service;

import com.dc.projectsclimber.dto.Votedto;
import com.dc.projectsclimber.entity.Project;
import com.dc.projectsclimber.entity.User;
import com.dc.projectsclimber.entity.UserType;
import com.dc.projectsclimber.entity.Vote;
import com.dc.projectsclimber.repository.ProjectRepository;
import com.dc.projectsclimber.repository.UserRepository;
import com.dc.projectsclimber.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VoteService {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public Votedto operationVote(Vote vote) {
        User actualUser = userRepository.findById(vote.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Project actualProject = projectRepository.findById(vote.getIdProject())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        if (Objects.nonNull(actualUser) && Objects.nonNull(actualProject)) {
            if (actualUser.getUserType().equals(UserType.USER)) {
                voteRepository.save(vote);
                Votedto votedto = new Votedto();
                votedto.setIdUser(vote.getIdUser());
                votedto.setIdProject(vote.getIdProject());
                votedto.setGeneratedFrom(vote.getGeneratedFrom());
                return votedto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Votedto> getVotes(Long idUser) {
        List<Vote> allVotes = new ArrayList<>();
        if (Objects.isNull(idUser)) {
            allVotes = voteRepository.findAll();
        } else {
            userRepository.findById(idUser)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            allVotes = voteRepository.findByIdUser(idUser);
        }
        List<Votedto> allVotesDto = new ArrayList<>();
        for (Vote vote : allVotes) {
            Votedto votedto = new Votedto();
            votedto.setIdUser(vote.getIdUser());
            votedto.setIdProject(vote.getIdProject());
            votedto.setGeneratedFrom(vote.getGeneratedFrom());
            allVotesDto.add(votedto);
        }
        return allVotesDto;
    }
    /*
    public List<Vote> ranking() {
        List<Vote> allVotes = voteRepository.findAll();
        allVotes.stream().count();
        for (Vote vote : allVotes) {
            vote.getIdProject();
        }
    }
    */
}
