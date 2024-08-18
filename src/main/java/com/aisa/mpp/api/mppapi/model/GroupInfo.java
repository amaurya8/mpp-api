package com.aisa.mpp.api.mppapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class GroupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated if not provided
    private String grpName;
    private String grpInfo;
    private int noOfMember;
    private LocalDate startDate;
    private LocalDate endDate;
    private String venue;
    private int avlPosition;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getGrpInfo() {
        return grpInfo;
    }

    public void setGrpInfo(String grpInfo) {
        this.grpInfo = grpInfo;
    }

    public int getNoOfMember() {
        return noOfMember;
    }

    public void setNoOfMember(int noOfMember) {
        this.noOfMember = noOfMember;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getAvlPosition() {
        return avlPosition;
    }

    public void setAvlPosition(int avlPosition) {
        this.avlPosition = avlPosition;
    }
}