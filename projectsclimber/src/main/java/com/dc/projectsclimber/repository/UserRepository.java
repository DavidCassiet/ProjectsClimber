package com.dc.projectsclimber.repository;

import com.dc.projectsclimber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCity(String city);
    List<User> findByCreationDateAfter(LocalDateTime dateTime);
}
