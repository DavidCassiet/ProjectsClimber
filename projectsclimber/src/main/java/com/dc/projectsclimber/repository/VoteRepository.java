package com.dc.projectsclimber.repository;

import com.dc.projectsclimber.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByIdUser(Long idUser);
}
