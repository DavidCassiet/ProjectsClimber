package com.dc.projectsclimber.dto;

import com.dc.projectsclimber.entity.EventStatus;

import java.math.BigDecimal;

public class Eventdto {

    private Long id;
    private String details;
    private BigDecimal reward;
    private EventStatus status;

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
}
