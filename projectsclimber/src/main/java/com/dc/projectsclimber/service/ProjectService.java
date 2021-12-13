package com.dc.projectsclimber.service;

import com.dc.projectsclimber.dto.Projectdto;
import com.dc.projectsclimber.entity.Event;
import com.dc.projectsclimber.entity.Project;
import com.dc.projectsclimber.entity.User;
import com.dc.projectsclimber.entity.UserType;
import com.dc.projectsclimber.repository.EventRepository;
import com.dc.projectsclimber.repository.ProjectRepository;
import com.dc.projectsclimber.repository.TagRepository;
import com.dc.projectsclimber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {

    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private TagRepository tagRepository;

    @Autowired
    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository,
                          TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.tagRepository = tagRepository;
    }

    public Projectdto createProject(Long idUser, Project project) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        project.setUser(user);
        if (user.getUserType().equals(UserType.OWNER)) {
            projectRepository.save(project);
            Projectdto projectdto = new Projectdto();
            projectdto.setId(project.getId());
            projectdto.setName(project.getName());
            projectdto.setDescription(project.getDescription());
            projectdto.setObjetive(project.getObjetive());
            projectdto.setPosted(project.isPosted());
            return projectdto;
        } else {
            return null;
        }
    }

    public List<Projectdto> getProjects(String tag, Boolean posted) {
        List<Project> allProjects = new ArrayList<>();
        if (Objects.isNull(tag) && Objects.isNull(posted)) {
            allProjects = projectRepository.findAll();
        } else if (Objects.nonNull(posted)) {
            allProjects = projectRepository.findByPosted(posted);
        } else if (Objects.nonNull(tag)) {
            allProjects = projectRepository.findByTag(tag);
        }
        List<Projectdto> allProjectsDto = new ArrayList<>();
        for (Project project : allProjects) {
            Projectdto projectdto = new Projectdto();
            projectdto.setId(project.getId());
            projectdto.setName(project.getName());
            projectdto.setDescription(project.getDescription());
            projectdto.setObjetive(project.getObjetive());
            projectdto.setPosted(project.isPosted());
            allProjectsDto.add(projectdto);
        }
        return allProjectsDto;
    }

    public Projectdto editProject(Long idProject, Project project) {
        projectRepository.findById(idProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        Project actualProject = projectRepository.findById(idProject).get();
        actualProject.setName(project.getName());
        actualProject.setDescription(project.getDescription());
        actualProject.setContent(project.getContent());
        actualProject.setObjetive(project.getObjetive());
        actualProject.setPosted(project.isPosted());
        actualProject.setUrl(project.getUrl());
        actualProject.setTags(project.getTags());
        projectRepository.save(actualProject);

        Projectdto projectdto = new Projectdto();
        projectdto.setId(actualProject.getId());
        projectdto.setName(actualProject.getName());
        projectdto.setDescription(actualProject.getDescription());
        projectdto.setObjetive(actualProject.getObjetive());
        projectdto.setPosted(actualProject.isPosted());
        return projectdto;
    }

    public void deleteProject(Long idProject) {
        projectRepository.findById(idProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        projectRepository.deleteById(idProject);
    }
}
