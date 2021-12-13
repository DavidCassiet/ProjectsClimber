package com.dc.projectsclimber.controller;

import com.dc.projectsclimber.entity.Event;
import com.dc.projectsclimber.repository.EventRepository;
import com.dc.projectsclimber.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "event")
public class EventController {

    private EventService eventService;
    private EventRepository eventRepository;

    @Autowired
    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }



    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody @Valid Event event) {
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
    }

    @PutMapping("/{idEvent}")
    public ResponseEntity<?> editEvent(@PathVariable("idEvent") Long idEvent,
                                      @RequestBody @Valid Event event) {
        return new ResponseEntity<>(eventService.editEvent(idEvent, event), HttpStatus.OK);
    }

    @DeleteMapping("/{idEvent}")
    public ResponseEntity<?> deleteEvent(@PathVariable("idEvent") Long idEvent) {
        eventService.deleteEvent(idEvent);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{idEvent}/project/{idProject}")
    public ResponseEntity<?> addProject(@PathVariable("idEvent") Long idEvent,
                                        @PathVariable("idProject")Long idProject) {
        eventService.addProjectToEvent(idEvent, idProject);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
