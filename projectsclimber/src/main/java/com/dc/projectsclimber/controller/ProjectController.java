package com.dc.projectsclimber.controller;

import com.dc.projectsclimber.entity.Project;
import com.dc.projectsclimber.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/user/{idUser}/project")
    public ResponseEntity<?> createProject(@PathVariable("idUser") Long idUser,
                                           @RequestBody @Valid Project project) {
        if (Objects.nonNull(projectService.createProject(idUser, project))) {
            return new ResponseEntity<>(projectService.createProject(idUser, project), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You must be OWNER to create a project",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project")
    public ResponseEntity<?> getAllProjects(@RequestParam(name = "tag", required = false) String tag,
                                             @RequestParam(name = "posted", required = false) Boolean posted) {
        return new ResponseEntity<>(projectService.getProjects(tag, posted), HttpStatus.OK);
    }

    @DeleteMapping("/project/{idProject}")
    public ResponseEntity<?> deleteProject(@PathVariable("idProject") Long idProject) {
        projectService.deleteProject(idProject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/project/{idProject}")
    public ResponseEntity<?> editProject(@PathVariable("idProject") Long idProject,
                                  @RequestBody @Valid Project project) {
        return new ResponseEntity<>(projectService.editProject(idProject, project), HttpStatus.OK);
    }
}
