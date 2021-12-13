package com.dc.projectsclimber.dto;

import java.math.BigDecimal;

public class Projectdto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal objetive;
    private boolean posted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getObjetive() {
        return objetive;
    }

    public void setObjetive(BigDecimal objetive) {
        this.objetive = objetive;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }
}
