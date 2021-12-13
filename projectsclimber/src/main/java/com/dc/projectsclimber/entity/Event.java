package com.dc.projectsclimber.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String details;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @NotBlank
    private String closedData;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private BigDecimal reward;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> subscribers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getClosedData() {
        return closedData;
    }

    public void setClosedData(String closedData) {
        this.closedData = closedData;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public List<Project> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Project> subscribers) {
        this.subscribers = subscribers;
    }

    public void addProject(Project project) {
        subscribers.add(project);
        project.setEvent(this);
    }

    public void removeProject(Project project) {
        subscribers.remove(project);
        project.setEvent(null);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", details='" + details + '\'' +
                ", creationDate=" + creationDate +
                ", closedData='" + closedData + '\'' +
                ", status=" + status +
                ", reward=" + reward +
                '}';
    }
}
