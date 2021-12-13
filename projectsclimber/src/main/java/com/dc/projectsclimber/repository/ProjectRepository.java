package com.dc.projectsclimber.repository;

import com.dc.projectsclimber.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPosted(boolean posted);
    @Query("SELECT project FROM Project project join fetch project.tags tag WHERE tag.name LIKE %:tag%")
    List<Project> findByTag(@Param("tag") String tag);
}
