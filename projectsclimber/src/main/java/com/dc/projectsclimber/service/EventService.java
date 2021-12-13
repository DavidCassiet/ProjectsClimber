package com.dc.projectsclimber.service;

import com.dc.projectsclimber.dto.Eventdto;
import com.dc.projectsclimber.entity.Event;
import com.dc.projectsclimber.entity.Project;
import com.dc.projectsclimber.repository.EventRepository;
import com.dc.projectsclimber.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ProjectRepository projectRepository) {
        this.eventRepository = eventRepository;
        this.projectRepository = projectRepository;
    }

    public Eventdto createEvent(Event event) {
        eventRepository.save(event);

        Eventdto eventdto = new Eventdto();
        eventdto.setId(event.getId());
        eventdto.setDetails(event.getDetails());
        eventdto.setReward(event.getReward());
        eventdto.setStatus(event.getStatus());
        return eventdto;
    }

    public Eventdto editEvent(Long idEvent, Event event) {
        Event actualEvent = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        actualEvent.setDetails(event.getDetails());
        actualEvent.setClosedData(event.getClosedData());
        actualEvent.setStatus(event.getStatus());
        actualEvent.setReward(event.getReward());
        eventRepository.save(actualEvent);

        Eventdto eventdto = new Eventdto();
        eventdto.setId(actualEvent.getId());
        eventdto.setDetails(event.getDetails());
        eventdto.setReward(event.getReward());
        eventdto.setStatus(event.getStatus());
        return eventdto;
    }

    public void deleteEvent(Long idEvent) {
        Event event = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        eventRepository.deleteById(idEvent);
    }

    public void addProjectToEvent(Long idEvent, Long idProject) {
        Event event = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Project project = projectRepository.findById(idProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        event.addProject(project);
    }


    /*
    public List<Projectdto> ranking(Long idEvent) {
        Event event = eventRepository.findById(idEvent).orElseThrow();
        List<Project> subscribers = event.getSubscribers();
        subscribers.
    }
    */
}
